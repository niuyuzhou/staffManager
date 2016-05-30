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
```javascript
mvn package
mvn install
```

### Run the relevent modules
```javascript
cd [rest|security|oauth2]
mvn spring-boot:run
```

## rest
* You need to ensure that you've installed the module of model before you compile and run rest because rest depends on model.
* It provides REST endpoints including create, find one, find All and update.
###Get All departments
```javascript
curl -X GET -H "Cache-Control: no-cache" -H "Postman-Token: f7944f38-4358-d880-a22b-e1185f05403e" "http://localhost:8080/dept"
```

```javascript
[{"dept":{"deptId":1,"deptName":"HR"},"emps":[{"empId":1,"empName":"Tom","salary":5000.0}]},{"dept":{"deptId":2,"deptName":"IT"},"emps":[{"empId":2,"empName":"John","salary":6000.0}]},{"dept":{"deptId":3,"deptName":"Marketing"},"emps":[]},{"dept":{"deptId":4,"deptName":"IT"},"emps":[]},{"dept":{"deptId":5,"deptName":"IT"},"emps":[]}]
```
###Get one department by Id
```javascript
curl -X GET -H "Cache-Control: no-cache" -H "Postman-Token: a8e98c5a-4483-45bf-9b0d-604b6dd1bb8b" "http://localhost:8080/dept/1"
```
```javascript
{"dept":{"deptId":1,"deptName":"HR"},"emps":[{"empId":1,"empName":"Tom","salary":5000.0}]}
```
###Create a new department
```javascript
curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache" -H "Postman-Token: ed5b3db5-24aa-30bb-a70a-9512d411c222" -d '{"dept":{"deptName":"IT"},"emps":[{"empId":2,"empName":"John","salary":6000.0}]}' "http://localhost:8080/dept"
```