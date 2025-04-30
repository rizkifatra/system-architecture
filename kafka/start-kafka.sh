#!/bin/bash

if [ ! -f ${KAFKA_HOME}/config/kraft/server.properties ]; then
    ${KAFKA_HOME}/bin/kafka-storage.sh format -t ${KAFKA_CLUSTER_ID} -c ${KAFKA_HOME}/config/server.properties
fi

exec ${KAFKA_HOME}/bin/kafka-server-start.sh ${KAFKA_HOME}/config/server.properties
