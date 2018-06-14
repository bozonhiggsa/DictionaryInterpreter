# Vocabulary Tester

Vocabulary Tester is a web application for testing the writing english skills.
The application downloads a personal dictionary from TXT-file into a database and then
permits to extract randomly the words and phrases from database.
The application extracts a next random word/phrase if you have done a proper
translation in english for a previous word.
It permits to verify the writing english skills.

Technologies:
- Spring MVC;
- Spring ORM;
- Hibernate;
- MySQL or PostgreSQL (separately in 2 branches);
- Logging: log4j, slf4j;

Features:
- Web application permits to download a personal dictionary from TXT-file (any native language);
- Used MySQL or Postgresql database and MANY-TO-MANY association for persisting a dictionary;
- Words and phrases extract randomly from database;
- It is possible to repeat the words that already done;
- It is possible to take a prompt;
- It is possible to reset a counter and to start from the beginning.

Prerequisites:
- Maven 3;
- Better use the newest version of IntelliJ IDEA, JDK 1.8_x, and Tomcat 8.x/9.x.