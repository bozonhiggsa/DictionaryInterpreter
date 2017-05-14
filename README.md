# DictionaryInterpreter

DictionaryInterpreter is an application for testing your writing english skills.
The application downloads your personal dictionary from TXT-file into MySQL database and then permits to extract randomly the words and phrases.
The application extracts next random word or phrase if you wrote a proper translation of previous word in english.
It permits to verify your writing english skills.

Technologies:
- Spring MVC
- Spring ORM
- Hibernate
- MySQL
- Logging: log4j, slf4j

Features:
- Application permits to download your personal dictionary from TXT-file (any native language)
- Used MySQL database for persisting your dictionary and MANY-TO-MANY association.
- The words and phrases extract randomly from database.
- It is possible to repeat the words that already done.
- It is possible to take a prompt.
- It is possible to reset a counter and to start from the beginning.

Prerequisites:
- Maven 3
- Better use the newest version of IntelliJ IDEA, JDK 1.8_x, and Tomcat 8.x.
