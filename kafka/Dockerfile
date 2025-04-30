FROM openjdk:11-jre-slim

ENV KAFKA_VERSION=3.5.1
ENV SCALA_VERSION=2.13
ENV KAFKA_HOME=/opt/kafka

RUN apt-get update && \
    apt-get install -y wget && \
    wget https://archive.apache.org/dist/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz -O /tmp/kafka.tgz && \
    tar xfz /tmp/kafka.tgz -C /opt && \
    ln -s /opt/kafka_${SCALA_VERSION}-${KAFKA_VERSION} ${KAFKA_HOME} && \
    rm /tmp/kafka.tgz && \
    mkdir -p /tmp/kraft-combined-logs

COPY server.properties ${KAFKA_HOME}/config/kraft/
COPY start-kafka-with-consumer.sh /usr/bin/

RUN chmod +x /usr/bin/start-kafka-with-consumer.sh

CMD ["/usr/bin/start-kafka-with-consumer.sh"]
