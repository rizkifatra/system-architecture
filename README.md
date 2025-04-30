


## MovieBaze – Real-Time Movie Data Processing System

## Abstract (in 300 words)
   1. Background

      Real-time data processing systems are becoming increasingly important for businesses to gain insights and respond quickly to changing conditions. However, building such systems requires integrating multiple technologies and ensuring end-to-end data consistency and performance.
   2. Problem Statement (from articles or newspaper or social media)

      There is a need to develop a real-time data processing system that can ingest data from various sources, perform complex analysis, and deliver actionable insights to users in a timely manner.
   3. Main objective

      The main objective of this project is to design and implement a real-time data processing system that can import data from a CSV file into a MySQL database, provide a microservice interface for data manipulation, stream data between services using Apache NiFi and Apache Kafka, analyze streamed data using Apache Spark, store results in Apache Cassandra, and notify users about data updates via a Telegram Bot, all while ensuring real-time data updates and notifications in less than 5 seconds.
   4. Methodology

      The project will be divided into four parts: 
         1) Data import and microservice setup, 
         2) Streaming and data analysis, 
         3) Notification system and performance requirements, and 
         4) System architecture and deployment. 
      
      Each part will involve setting up and configuring various technologies, writing code for data processing and analysis, and ensuring end-to-end data consistency and performance.

   5. Result

      The project will result in a fully functional real-time data processing system that can handle large volumes of data and deliver insights to users in real-time. The system will be containerized using Docker for easy deployment and scalability.
   6. Conclusion

      This project demonstrates the feasibility of building a real-time data processing system using a combination of technologies such as MySQL, Spring Boot, Angular, Apache NiFi, Apache Kafka, Apache Spark, Apache Cassandra, and Telegram Bot. The project also highlights the importance of careful system design and integration to ensure end-to-end data consistency and performance.

## System Architecture (MUST be included in your presentation)


![RTP-Page-3 drawio (1)](https://github.com/user-attachments/assets/6424bf17-5c2f-4ced-b1e4-f3ad674ee10c)


In our real-time data processing system, we have multiple components interacting to ensure efficient data flow, processing, and user notifications. Here is a detailed breakdown of the flow

1. Data Import from CSV to MySQL

   A script runs to import data from a CSV file into the MySQL database. This is the initial step where raw data is stored in MySQL.

2. Spring Boot API for Data Manipulation

   Spring Boot Application:
      A Spring Boot application provides a REST API that allows users to add, update, and retrieve records in MySQL. For Example, When a user adds a new movies via the Angular frontend, the Spring Boot API handles the request and updates the MySQL database.
   
3. Periodic Data Fetching with Apache NiFi:

   NiFi is configured to periodically fetch data from the Spring Boot API. This data includes updates made to the MySQL database. For Example, NiFi polls the Spring Boot API every minute to check for new or updated records.

4. Streaming Data to Apache Kafka

   Once NiFi fetches the data from the Spring Boot API, it sends this data to a Kafka topic for streaming.For Example, NiFi sends a message to the Kafka topic movies containing the new or updated record details.

5. Real-time Data Processing with Apache Spark

   Apache Spark subscribes to the Kafka topic movies and receives the streamed data. For Example, Spark processes the incoming data as required.

6. Storing Processed Data in Cassandra

   After processing the data, Spark stores the analyzed results in Apache Cassandra. For Example, Spark stores the processed data in a Cassandra table named movies in moviesdb keyspace.

7. Telegram Bot for User Notifications

   The Telegram Bot retrieves processed data from Cassandra to notify users about updates. For Example, The bot establish connection to Cassandra to check for new processed data and prepares a notification message.

8. User Interaction with Telegram Bot

   The Telegram Bot is connected with user. For Example, When a user sends a message /start to the Telegram Bot, the Java application receives it and store the chat id, processes the request, and sends a response for registered chatId for any update notifications.
   
## UML Class Diagram
<img src="images\image.png" width="100%" height="100%">

## User manual/guideline for system configuration
   1. download docker on your machine to compose the docker compose file
   2. go to folder that you downloaded on the terminal
   3. compose file using the command
   4. drag and put the nifi.json that already downloaded
   5. remove unnecessary configuration on invokeHTTPS on nifi configuration
   6. start nifi (you have to enable controller to start the processor )
      
## User manual/guideline for testing the system
   1. go direct to your downloaded path on your terminal
   2. compose the docker compose file using docker command
      - docker compose up --build
      
   
## Result/Output (Screenshot of the output)
<img src="images\outputtele.jpeg" width="100%" height="100%">

## Link for the Dataset
https://drive.google.com/drive/folders/1fEWkzcUKRF5fJXXmur6JMKe4anwdJIv0?usp=sharing

