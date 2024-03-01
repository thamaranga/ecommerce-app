In this application we have implemented below concepts related to
micro service architecture. 

1. discovery server
2. api-gateway (Added keycloak also for doing oAuth2 based authentication/authorization)
3. circuit breaker
4. distributed log tracing


This is a maven multi module micro service application.

This application contains below micro services/modules.

1. inventory-service - This uses a mysql db
2. order-service - This uses a mysql db. This is a kafka producer. This service calls 
   to inventory-service for checking the product availability.
3. product-service - This uses mongo db. This is a isolated service.
4. notification-service - This is a kafka consumer
5. api-gateway
6. discovery-server
7. keycloak - spin up through docker 
8. kafka - spin up through docker
9. mongo db - spin up through docker

Here for service to service communication web client is used. (This is included inside webflux dependency)

In this application for doing user authenticationa and
authorization keycloak has been used.
In local env we can spin up a keycloak doker container using below 
command.

docker run -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:20.0.3 start-dev

In web browser I can access above keycloak instance using
http://localhost:8081/ url.
After starting the keycloak server instance, need to create a realm
and client.
realm manages a set of users, credentials , roles and group. It is like a seperate namespace
for managing set of users.
client is like a single application. Client can request to keycloak to 
authenticate a user.
While creating the client , client authentication need to set as on.
Then for generating an access token in postman we need to configure 
access token url (token endpoint of open id endpoint) , client id and
client secret.
Above open id endpoint can be found in Realm settings -> General -> Open ID
endpoint configuration -> token_endpoint

When sending requests through postman Authirization header need to send as below.
Type : OAuth 2.0
Header Prefix : Bearer
Grant Type : Client Credentials
Access token url :() Realm settings -> General -> Open ID
endpoint configuration -> token_endpoint )
Client ID , Client secret is returned while creating Keycloak client
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

We can start a kafka server using dokcer containers. as mentioned in this article.
We just need to create a docker-compose file inside our root project.
Using that docker compose file we can spin up two docker containers (zoo-keeper and  kafka server)


For monitoring these micro services prometheus and grafana are used.
Prometheus is like a in memory db which fetches actuator details from our micro services.
Grafana is  used for visualize the details. So basically gpafana fetches data from
prometheus and showing in dashboards.

After starting grafana dashboard go there and create a datasource.
Select datasource type as prometheus and give the prometheus running url.

Then need to create a dashboard selecting 

Still data loading into grafana dash board part is not implemented.








