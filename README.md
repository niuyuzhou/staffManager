Spring Boot + REST API + Security + OAuth2 + JPA
==========================================
This is a example integrating the above technologies. It is under active development, and more features are in progress.

**Index**:
[Purpose](#purpose) &bull;
[What you'll need](#what you'll need) &bull;
[Summary](#summary) &bull;
[Run example](#Run example) &bull;
[rest](#rest) &bull;

## Purpose
This example is designed to make it easy for beginner to learn the relevent knowledge about [Maven](http://maven.apache.org/download.cgi), [Spring boot](https://projects.spring.io/spring-boot/), [Spring REST API](https://spring.io/understanding/REST), [Spring JPA](https://projects.spring.io/spring-data-jpa/), and [Spring OAuth2](https://spring.io/understanding/oauth)
## What you'll need
* JDK 1.8 or later
* Maven 3.0+
* MySQL 5.6 or later

## Summary
staffManager has the following modules:
* `model`: Implementing of One-to-Many relationship database with JPA
* `rest`: REST endpoints and web interface
* `security`: REST endpoints and web interface with Basic Authentication
* `oauth2`: REST endpoints and web interface with OAuth2 Authentication

## Run example

### Installation
You need include the WPSerive.
```javascript
mvn package
mvn install

### Run the relevent modules
```javascript
cd [rest|security|oauth2]
mvn spring-boot:run

## rest
###Get All departments
```javascript
curl -X GET -H "Cache-Control: no-cache" -H "Postman-Token: f7944f38-4358-d880-a22b-e1185f05403e" "http://localhost:8080/dept"

[{"dept":{"deptId":1,"deptName":"HR"},"emps":[{"empId":1,"empName":"Tom","salary":5000.0}]},{"dept":{"deptId":2,"deptName":"IT"},"emps":[{"empId":2,"empName":"John","salary":6000.0}]},{"dept":{"deptId":3,"deptName":"Marketing"},"emps":[]},{"dept":{"deptId":4,"deptName":"IT"},"emps":[]},{"dept":{"deptId":5,"deptName":"IT"},"emps":[]}]




