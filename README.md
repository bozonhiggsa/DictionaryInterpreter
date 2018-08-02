# Vocabulary Tester

[![Build Status](https://travis-ci.org/bozonhiggsa/DictionaryInterpreter.svg?branch=master)](https://travis-ci.org/bozonhiggsa/DictionaryInterpreter)

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
- MySQL or PostgreSQL (separately in two branches);
- Logging: Log4J, SLF4J;

Features:
- Web application permits to download a personal dictionary from TXT-file (any native language);
- Used MySQL or PostgreSQL database and MANY-TO-MANY association for persisting a dictionary;
- Words and phrases extract randomly from database;
- It is possible to repeat the words that already done;
- It is possible to take a prompt;
- It is possible to reset a counter and to start from the beginning.

Prerequisites:
- Maven 3;
- Better use JDK 1.8_x, and Tomcat 8.x/9.x.

# License

This project is licensed under the terms of the MIT license.
