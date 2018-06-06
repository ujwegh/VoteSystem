## Implementation of REST API using Maven/Spring/Security/JPA(Hibernate)/REST(Jackson)
Stack technology Spring MVC, Spring Data-Jpa, Spring Security, Hibernate, Logback, 
Json Jackson, Apache Tomcat, Ehcache, JUnit, Hamcrest, slf4j

The task is:

#### Build a voting system for deciding where to have lunch.
* 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.
   
To deploy you need Maven, Java JDK8, Tomcat 8.5+, Intellij IDEA, POSTMAN

#### Manual
1. Clone or download this repo, checkout to branch master
2. Open as new project in IDE
3. Add JPA facets in the project structure and hibernate as provider
4. Add hsqldb(local) database. Chose "in-memory", set new name of database and test connection. 
5. Add new configuration as tomcat server-local.
6. Run tomcat server from IDE.
7. Done.
8. Use POSTMAN or smth else to test it.
