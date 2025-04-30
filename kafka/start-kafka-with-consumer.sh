#!/bin/bash

# Initialize Kafka if not already done
if [ ! -f /tmp/kraft-combined-logs/meta.properties ]; then
    KAFKA_CLUSTER_ID=$(${KAFKA_HOME}/bin/kafka-storage.sh random-uuid)
    ${KAFKA_HOME}/bin/kafka-storage.sh format -t $KAFKA_CLUSTER_ID -c ${KAFKA_HOME}/config/kraft/server.properties
fi

# Start Kafka in the background
${KAFKA_HOME}/bin/kafka-server-start.sh ${KAFKA_HOME}/config/kraft/server.properties &

# Wait for Kafka to be ready
sleep 30

# Create the 'movies' topic if it doesn't exist
${KAFKA_HOME}/bin/kafka-topics.sh --create --if-not-exists --bootstrap-server kafka:9092 --replication-factor 1 --partitions 1 --topic movies

# Start the console consumer
${KAFKA_HOME}/bin/kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic movies --from-beginning
