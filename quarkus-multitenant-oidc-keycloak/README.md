# quarkus-multitenant-oidc-keycloak project

Quarkus multi-tenancy authenticated by oidc server as keycloak.

## Usage
1. Set up keycloak server. You can find docker-compose file in ./document. Start it with command `
docker-compose up -d`
2. Create realm by import file ./document/realm-initialize.json
3. Get user access token by calling :
```
curl --location --request POST 'http://127.0.0.1:8088/auth/realms/quarkus/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=quarkus' \
--data-urlencode 'username=anonymous' \
--data-urlencode 'password=1234' \
--data-urlencode 'grant_type=password'
```
4. Copy access_token in the response
5. Start quarkus application with command `./gradlew quarkusDev`
6. Call `localhost:8080/tenant` to check to result, authenticate with Bearer token is the token you copied above.
 
```
curl --location --request GET 'localhost:8080/tenant' \
--header 'tenantId: quarkus' \
--header 'Authorization: Bearer your-keycloak-access-token
```

Note: header tenantId is your realm name.