[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/vaXpkLzu)
## Requirements for Group Project
[Read the instruction](https://github.com/STIW3054-A232/class-activity-stiw3054/blob/main/GroupProject.md)

## Group Info:
| MATRIC NUMBER | NAME                           | PHONE NUMBER |
   |---------------|--------------------------------|--------------|
   | 286941        | FARA AYEESHA BINTI AHMAD YUSNI | +60 14-276 2634   |
   | 284172        | HASYA AULIA FITRI              | +60 14-742 4635 |
   | 290267        | SALSABILA SHAFA KHAIRUNISA     | +60 11-2340 6446 |
   | 285884        | MUHAMMAD HAFIZ BIN ABD RAHIM   | +60 11-1490 1703  |
   | 290911        | MUHAMMAD RIZKI FATRA     | +60 16-250 8620  |

1. Mention who is the leader.

   LEADER: FARA AYEESHA BINTI AHMAD YUSNI
1. Other related info (if any)

## Title of your application (a unique title)

   MovieBaze
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
      
   
## User manual for installing your application on AWS (Bonus 5%)
## Result/Output (Screenshot of the output)
<img src="images\outputtele.jpeg" width="100%" height="100%">

## References (Not less than 20)

   Adji, L. S. A., & Mailoa, E. (2024). Pembuatan REST API manajemen data karyawan berbasis website menggunakan Spring Boot. Jurnal Indonesia, 5(2), 1543–1552. https://doi.org/10.35870/jimik.v5i2.713

   Apache SparkTM - Unified Engine for large-scale data analytics. (n.d.). https://spark.apache.org/
   
   Apache NiFi. (n.d.). Apache NiFi. https://nifi.apache.org/

   Apache Cassandra | Apache Cassandra Documentation. (n.d.). Apache Cassandra. https://cassandra.apache.org/_/index.html

   Beridzeg, G. (2023). All movies on IMDb. Kaggle. https://www.kaggle.com/datasets/beridzeg45/all-movies-on-imdb/data
   
   Chintapalli, S., Dagit, D., Evans, B., Farivar, R., Graves, T., Holderbaugh, M., ... & Zhang, Z. (2016, December). Benchmarking streaming computation engines: Storm, Flink and Spark Streaming. In 2016 IEEE international parallel and distributed processing symposium workshops (IPDPSW) (pp. 1789-1792). IEEE.
   
   Dean, J., & Ghemawat, S. (2008). MapReduce: simplified data processing on large clusters. Communications of the ACM, 51(1), 107-113.
   
   Grolinger, K., L'Heureux, A., Capretz, M. A., & Seewald, L. (2016). Energy forecasting for event venues: Big data and prediction accuracy. Energy and Buildings, 112, 222-233.
   
   Kreps, J., Narkhede, N., & Rao, J. (2011, June). Kafka: A distributed messaging system for log processing. In Proceedings of the NetDB (Vol. 11, pp. 1-7).

   Kozak, M. (2023). Analysis of the Spring Boot and Spring Cloud in developing Java cloud applications. Journal of Computer Sciences Institute, 27, 112–120. https://doi.org/10.35784/jcsi.3130

   Khaerunnisa, A., Rosadi, D., Supriatna, H., & Latif, D. (2024). Fullstack implementation using Angular Framework and SpringBoot in the Participant Admission Information System New Education (PPDB) (RA DAARUN Case Study - NISAA). Bisnis Dan Iptek/Bisnis Dan Iptek, 17(1), 97–105. https://doi.org/10.55208/bistek.v17i1.580
   
   Maarala, A. I., Rautiainen, M., Salmi, A., Bassett, G., Jokela, J., & Ahtiainen, A. (2017). Robust detection of periodic and anomalous patterns in building energy consumption data. Energy and Buildings, 149, 1-13.

   MySQL :: MySQL Documentation. (n.d.). https://dev.mysql.com/doc/
   
   Maiwald, S., Mann, L., Garcia, S., & Heitkam, T. (2024). Evolving together: Cassandra Retrotransposons gradually mirror promoter mutations of the 5S RRNA genes. Molecular Biology and Evolution, 41(2). https://doi.org/10.1093/molbev/msae010

   Nufusula, R., & Susanto, A. (2018). Rancang bangun chat bot pada server pulsa mengunakan Telegram Bot API. DOAJ (DOAJ: Directory of Open Access Journals). https://doaj.org/article/af617cfea00743f4bc99e6a002ce6f27

   Pandya, A., Kostakos, P., Mehmood, H., Cortes, M., Gilman, E., Oussalah, M., & Pirttikangas, S. (2019). Privacy preserving sentiment analysis on multiple edge data streams with Apache NiFi. (EISIC). https://doi.org/10.1109/eisic49498.2019.9108851

   Septipalan, M. L., Widiartha, I. B. K., Zubaidi, A., & Taufik, M. (2024). Integrated notification system for smart parking security using Bot Telegram. Jurnal Penelitian Pendidikan IPA, 10(5), 2679–2686. https://doi.org/10.29303/jppipa.v10i5.7447

   Setiaji, H., & Paputungan, I. V. (2018). Design of Telegram Bots for campus information sharing. IOP Conference Series. Materials Science and Engineering, 325, 012005. https://doi.org/10.1088/1757-899x/325/1/012005

   Wnęk, K., & Boryło, P. (2023). A data processing and distribution system based on Apache NIFI. Photonics, 10(2), 210. https://doi.org/10.3390/photonics10020210

   Wang, G., Koshy, J., Subramanian, S., Paramasivam, K., Zadeh, M., Narkhede, N., Rao, J., Kreps, J., & Stein, J. (2015). Building a replicated logging system with Apache Kafka. Proceedings of the VLDB Endowment, 8(12), 1654–1655. https://doi.org/10.14778/2824032.2824063

   Zaharia, M., Chowdhury, M., Franklin, M. J., Shenker, S., & Stoica, I. (2010, April). Spark: Cluster computing with working sets. In HotCloud (Vol. 10, No. 10-10, p. 95).


## Youtube Presentation (10%)
https://youtu.be/LN8FHcOBl-Q

YOUTUBE LINK BY SECTION/MINUTES :

- Docker : https://youtu.be/LN8FHcOBl-Q?si=TAmkq3fRI6-6kTRG&t=51

- Angular : https://youtu.be/LN8FHcOBl-Q?si=KtAsJbyxa5Y8U5BR&t=368

- MySQL : https://youtu.be/LN8FHcOBl-Q?si=Eukpr8rBnz2XrXad&t=560

- Springboot : https://youtu.be/LN8FHcOBl-Q?si=U6wPIpsCe_KjmHYT&t=644

- Nifi : https://youtu.be/LN8FHcOBl-Q?si=Shb5pjXpzgw3vi_C&t=797

- Kafka : https://youtu.be/LN8FHcOBl-Q?si=OV43x10Ps2UqnQFp&t=1078

- Spark Cassandra : https://youtu.be/LN8FHcOBl-Q?si=pH0CNqnBTjLfD5Ew&t=1117

- Telegram : https://youtu.be/LN8FHcOBl-Q?si=0ffppGeWDa_FX4NX&t=1302

- Demonstration : https://youtu.be/LN8FHcOBl-Q?si=JvnVcomcX7V05Soq&t=1458


## Link for the Dataset
https://drive.google.com/drive/folders/1fEWkzcUKRF5fJXXmur6JMKe4anwdJIv0?usp=sharing

