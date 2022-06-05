![](https://img.shields.io/badge/spring%20boot%202-compatible-green.svg)

# Word Counter Microservice

<h1>Instructions for running application:</h1>

<h3> Launching with containers  </h3>

1. Build the project with `mvn clean install`
2. In terminal execute command `docker compose up --force-recreate --build`
    
   <h6>--build -> Build images before starting containers.<h6>
   <h6>--force-recreate -> Recreate containers even if their configuration and image haven't changed.<h6>
   
3. Application will be available on http://localhost:8080/
4. Swagger will be available on  http://localhost:8080/word-counter/swagger-ui/

<h3> Launching via intellij idea: </h3>

1. In configuration set active profile: `dev` or in application.yml should be set active profile: 'dev'
2. Run main method from WordCounterApplication.java
3. Application will be available on http://localhost:8081/

<h3> Debug connection to container: </h3>

Use `10005` port for connection to container in debug mode.


### Requirements

- Latest openJDK 11

> https://developers.redhat.com/products/openjdk/download

- SonarLint

> https://www.sonarlint.org/

# Lombok
Go to `C:\Users\<myusername>\.m2\repository\org\projectlombok\lombok\<version>`, 
e.g. `C:\Users\<myusername>\.m2\repository\org\projectlombok\lombok\1.18.16` 
and run lombok-1.18.16.jar with double click to install the plugin. After installing the plugin, we need to restart the IDE.

> https://www.baeldung.com/lombok-ide

### Nexus

Set up JAVA_HOME environment variable:

> https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html

Execute `mvn clean install`.

### Usage

Run as Spring Boot App:

1. word-counter service

Go to:

> http://localhost:8080/word-counter/swagger-ui/

> http://localhost:8080/word-counter/actuator/health

> http://localhost:8080/word-counter/actuator/info

> POST http://localhost:8080/word-counter/actuator/refresh

Service configuration url example:

```
http://localhost:8080/<spring.application.name>/<profile>
```

Go to:

> http://localhost:8888/word-counter/dev

### Test

Execute:

```
mvn clean test
```

### Set Up Git

```
git remote add origin <url>
```

