# quarkus-multitenant-oidc-keycloak project

Quarkus multi-tenancy authenticated by oidc server as keycloak.

## Usage
1. Set up keycloak server. You can find docker-compose file in ./document. Start it with command `
docker-compose up -d`
2. Create a user with Anonymous roles. 
3. Get user token by calling, and copy access token 
```
curl --location --request POST 'http://127.0.0.1:8088/auth/realms/master/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=master' \
--data-urlencode 'username=anonymous' \
--data-urlencode 'password=your user password' \
--data-urlencode 'grant_type=password'
```
4. Start quarkus application with command ./gradlew quarkusDev
5. Call `localhost:8080/tenant` to check to result, authenticated with Bearer token is the token you copied above.
 
```
curl --location --request GET 'localhost:8080/tenant' \
--header 'tenantId: master' \
--header 'Authorization: Bearer your-keycloak-access-token
```

Note: header tenantId is your realm name.