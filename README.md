# Kairos Auth

### Prerequisites
- JDK 17 (https://openjdk.java.net)
- Git
- PostgresSQL with correct database scheme

To run the project in local environment, add your postgresSQL credentials in the application.properties file.
```
spring.datasource.url = jdbc:postgresql://${PG_HOST}:${PG_PORT}/${PG_DB_NAME}
spring.datasource.username = ${PG_USER}
spring.datasource.password = ${PG_PASSWORD}
```

In order to sign-in and use the token with AdonisJS (resource server), use the same JWT secret.
```
kairos.app.jwtSecret=${JWT_CONFIG_KEY}
```


#### How to build 
```bash
$ mvn clean package
```

#### How to run
```bash
$ java -jar <path_to_jar_file>
```
