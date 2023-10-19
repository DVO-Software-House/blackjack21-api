# blackjack-21

## Requirements

For building and running the application you need:

- [JDK 17](https://www.azul.com/downloads/#downloads-table-zulu)
- [Maven 3.6.3 or later](https://maven.apache.org)
- [Docker desktop](https://www.docker.com/products/docker-desktop/)
- [Docker compose](https://docs.docker.com/compose/) - Included in docker desktop

## Running the application locally

First, make sure to start the database by running
```
make start-db
```

Then you can start the api by using the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

You can also start both api and database by running
You can also start both database and api with docker by running
```
make start-app
```