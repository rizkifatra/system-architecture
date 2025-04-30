import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.types._
import com.datastax.spark.connector._
import com.datastax.spark.connector.cql.CassandraConnector
import org.apache.spark.sql.cassandra._

object SparkKafkaCassandra {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("SparkKafkaCassandra")
      .config("spark.master", "local[*]")
      .config("spark.cassandra.connection.host", "cassandra")
      .getOrCreate()

    import spark.implicits._

    // Create Cassandra keyspace and table
    CassandraConnector(spark.sparkContext.getConf).withSessionDo { session =>
      session.execute("CREATE KEYSPACE IF NOT EXISTS moviesdb WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 }")
      session.execute("""
        CREATE TABLE IF NOT EXISTS moviesdb.movies (
          id BIGINT PRIMARY KEY,
          title TEXT,
          releaseyear INT,
          genres TEXT,
          imdbrating FLOAT,
          lengthinmin INT,
          poster TEXT,
          updatedat TIMESTAMP
        )
      """)
    }

    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "kafka:9092")
      .option("subscribe", "movies")
      .load()

    val movieData = df.selectExpr("CAST(value AS STRING)")
      .select(from_json($"value", schema).alias("data"))
      .select("data.*")

    val query = movieData
      .writeStream
      .foreachBatch { (batchDF: org.apache.spark.sql.DataFrame, batchId: Long) =>
        batchDF.select(
            col("id"),
            col("title"),
            col("release_year").as("releaseyear"),
            col("genres"),
            col("imdb_rating").as("imdbrating"),
            col("length_in_min").as("lengthinmin"),
            col("poster"),
            col("updated_at").as("updatedat")
          )
          .write
          .cassandraFormat("movies", "moviesdb")
          .mode("append")
          .save()
      }
      .option("checkpointLocation", "/tmp/checkpoint")
      .start()

    query.awaitTermination()
  }

  val schema = new StructType()
    .add("id", LongType)
    .add("title", StringType)
    .add("release_year", IntegerType)
    .add("genres", StringType)
    .add("imdb_rating", FloatType)
    .add("length_in_min", IntegerType)
    .add("poster", StringType)
    .add("updated_at", TimestampType)
}
