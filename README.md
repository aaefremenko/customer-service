# Customer Service Demo Project
## Run Customer Service Application
First of all, we have to build our application. To do this, we go to the root dir and run the following Maven command
```
mvn clean install
```
After this, to run our built application with all the dependencies such as PostgreSQL, Zookeeper and Kafka we have to run the next command
```
docker-compose up -d
```
As a result, the application will be run on port 8090.
To play with this application, you can use swagger
```
http://localhost:8090/swagger-ui/index.html
```
or Postman.
