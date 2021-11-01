How to run the application:
1. In command line run a command: `mvn clean install -DskipTests=true` 
2. Next run: `docker-compose up`

If you want to run the application in a local server, in a file `src/main/resources/application.properties`, change `postgresqldb:5432` to `localhost:5432`