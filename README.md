Spring Boot + REST API + Security + OAuth2 + JPA + Hibernate
==========================================
This is a example integrating the above technologies. It is under active development, and more features are in progress.

**Index**:
[Purpose](#purpose) &bull;
[What you'll need](#what you'll need) &bull;
[Summary](#summary) &bull;
[Run example](#Run example) &bull;
[rest](#rest) &bull;

## Purpose
This example is designed to make it easy for beginner to learn the relevent knowledge about [Maven](http://maven.apache.org/download.cgi), [Spring boot](https://projects.spring.io/spring-boot/), [Spring REST API](https://spring.io/understanding/REST), [Spring JPA](https://projects.spring.io/spring-data-jpa/), [Spring OAuth2](https://spring.io/understanding/oauth) and [Spring Hibernate](http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html).  

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

## Installation
You need include the WPSerive.
```mvn package
```mvn install

## Run the relevent modules
```cd [rest|security|oauth2]
```mvn spring-boot:run

## rest

following is in progress
* **pages**
* **types**
* **media**


