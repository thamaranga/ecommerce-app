In this application we have implemented below concepts related to
micro service architecture. 

1. discovery server
2. api-gateway (Added keycloak also for doing oAuth2 based authentication/authorization)
3. circuit breaker
4. distributed log tracing


This is a maven multi module micro service application.

This application contains below micro services/modules.

inventory-service - This uses a mysql db
order-service - This uses a mysql db. This is a kafka producer.
product-service - This uses mongo db. This is a isolated service.
notification-service - This is a kafka consumer
api-gateway
discovery-server
keycloak - sping up through docker 
kafka - spin up through docker
mongo db - sping up through kafka

In this application for doing user authenticationa and
authorization keycloak has been used.
In local env we can spin up a keycloak doker container using below 
command.
docker run -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:20.0.3 start-dev
In web browser I can access above keycloak instance using
http://localhost:8081/ url.
After starting the keycloak server instance, need to create a realm
and client.
Then for generating an access token in postman we need to configure 
access token url (token endpoint of open id endpoint) , client id and
client secret.

Inside api-gateway micro service  relevant authentication/authorization
is happened using keyclaok.
Further relevant routing also happening inside api-gateway 
micro service.

For implementing distributed log tracing zipkin has been used.
In local environment , using below command zipkin docker instance can be spin up.
docker run -d -p 9411:9411 openzipkin/zipkin
Then above zipkin dashboard can be accesed in the browser using below url.
http://localhost:9411/
After adding zipkin to our project, it adds two ids as trace id and span id
into our log messages.
trace id is unique for all the micro services.
span id is unique for within one micro service.

We can start a kafka server using dokcer containers as mentioned in this article.
https://developer.confluent.io/quickstart/kafka-docker/
We just need to create a docker-compose file inside our root project.
Using that docker compose file we can spin up two docker containers (zoo-keeper and  kafka server)








