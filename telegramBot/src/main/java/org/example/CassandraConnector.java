package org.example;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.BoundStatement;

import java.net.InetSocketAddress;

public class CassandraConnector {

    private CqlSession session;

    // Establish connection to Cassandra
    public void connect(String node, int port, String keyspace) {
        try {
            if (this.session == null) {
                System.out.println("Attempting to connect to Cassandra...");
                this.session = CqlSession.builder()
                        .addContactPoint(new InetSocketAddress(node, port))
                        .withLocalDatacenter("datacenter1") // Ensure this matches your datacenter name
                        .withKeyspace(keyspace)
                        .build();
                System.out.println("Connected to Cassandra cluster: " + session.getMetadata().getClusterName());
            } else {
                System.out.println("Session already connected.");
            }
        } catch (Exception e) {
            System.err.println("Error connecting to Cassandra: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Disconnect from Cassandra
    public void close() {
        if (session != null) {
            session.close();
            System.out.println("Disconnected from Cassandra.");
        }
    }

    // Execute a query (for DML operations, if needed)
    public void executeQuery(String query) {
        try {
            session.execute(query);
        } catch (Exception e) {
            System.err.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Fetch data with a query
    public ResultSet fetchData(String query) {
        try {
            return session.execute(query);
        } catch (Exception e) {
            System.err.println("Error fetching data: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Execute a parameterized query
    public ResultSet execute(String query, Object... parameters) {
        try {
            PreparedStatement preparedStatement = session.prepare(query);
            BoundStatement boundStatement = preparedStatement.bind(parameters);
            return session.execute(boundStatement);
        } catch (Exception e) {
            System.err.println("Error executing parameterized query: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
