Spring Boot + REST API + Security + OAuth2 + JPA
==========================================
This is a example integrating the above technologies. It is under active development, and more features are in progress.

**Index**:
[Purpose](#purpose) &bull;
[What you'll need](#what you'll need) &bull;
[Summary](#summary) &bull;
[Run example](#Run example) &bull;
[rest](#rest) &bull;
[security](#security) &bull;
[oauth2](#oauth2) &bull;

## Purpose
This example is designed to make it easy for beginner to learn the relevent knowledge about [Maven](http://maven.apache.org/download.cgi), [Spring boot](https://projects.spring.io/spring-boot/), [Spring REST API](https://spring.io/understanding/REST), [Spring JPA](https://projects.spring.io/spring-data-jpa/), [One-to-Many](https://en.wikibooks.org/wiki/Java_Persistence/OneToMany), and [Spring OAuth2](https://spring.io/understanding/oauth)
## What you'll need
* JDK 1.8 or later
* Maven 3.0+
* MySQL 5.6 or later
```javascript
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.3.5.RELEASE)
```

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
```javascript
[INFO] staffmanager ...................................... SUCCESS [  0.218 s]
[INFO] model ............................................. SUCCESS [  5.016 s]
[INFO] rest .............................................. SUCCESS [  0.109 s]
[INFO] security .......................................... SUCCESS [  0.078 s]
[INFO] oauth2 ............................................ SUCCESS [  0.766 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

### Run the relevent modules
```javascript
cd [rest|security|oauth2]
mvn spring-boot:run
```

## rest
* You can learn how to depend to other model, how to deploy REST with spring-boot-starter-web.
* You need to ensure that you've installed the module of model before you compile and run rest because rest depends on model.
* It provides REST endpoints including create, find one, find All and update.

###Core sources
Abbreviation

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

following is in progress
* **delete**
* **update**

## security
* You can learn how to authenticate the client with Basic Authentication
* Precompile requirement is same as rest

###Core sources
* You can change username and password as you like
```java
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication()
				.withUser("root").password("root").roles("USER");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        http.httpBasic();
        http.csrf().disable();
    }
```

###Get one department by Id with Basic Authentication
```javascript
curl -X GET -H "Content-Type: application/json" -H "Authorization: Basic cm9vdDpyb290" -H "Cache-Control: no-cache" -H "Postman-Token: d6f03df9-2261-7981-d7cd-151b4e908753" "http://localhost:8080/dept"
```

## oauth2
* You can learn how to authenticate the client with oauth2 (password mode)
* Precompile requirement is same as rest

###Core sources
* You can change username and password as you like
```java
	public void init(AuthenticationManagerBuilder auth) throws Exception {
      auth.inMemoryAuthentication()
      .withUser("admin").password("123456").roles("USER");
	}

	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(applicationName) // applicationName="staffmanager"
			.authorizedGrantTypes("password", "authorization_code", "refresh_token")
			.authorities("ROLE_USER").scopes("write").resourceIds(applicationName)
			.secret("888888");
	}
```

###Get access token
```javascript
curl -X POST -vu staffmanager:888888 http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=123456&username=admin&grant_type=password&scope=write&client_secret=888888&client_id=staffmanager"
```
```javascript
{"access_token":"7fd3f22b-2bae-41ad-9f88-77c778b5a546","token_type":"bearer","refresh_token":"5c376a65-5bce-48c8-9fe8-95e41401b11e","xpires_in":43199,"scope":"write"}
```
###Get One Department by Id with the access token obtained.
```javascript
curl -X GET -H "Authorization: Bearer 7fd3f22b-2bae-41ad-9f88-77c778b5a546" -H "Cache-Control: no-cache" -H "Postman-Token: cb8ec0b9-ac7d-ddfc-481b-1e1b0664a912" "http://localhost:8080/dept/1"
```
```javascript
{"dept":{"deptId":1,"deptName":"HR"},"emps":[{"empId":1,"empName":"Tom","salary":5000.0}]}
```