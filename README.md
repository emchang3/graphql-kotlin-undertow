# graphql-kotlin-undertow

A simple server with a single GraphQL endpoint, perfect for pet projects and proofs of concept. This project utilizes Undertow, a powerful HTTP server that comes with non-blocking IO out of the box, but none of the cruft of Spring Boot.

This project utilizes `graphql-kotlin-schema-generator` from [`graphql-kotlin`][gql-kt], an open-source library developed by Expedia Group. It relies on the query execution mechanism of [`graphql-java`][gql-java], which is a dependency of `graphql-kotlin`, but it does not utilize the Spring Boot framework from either of those projects.

[gql-kt]: https://github.com/ExpediaGroup/graphql-kotlin
[gql-java]: https://github.com/graphql-java/graphql-java

## Instructions

Build:

```
./gradlew clean build
```

Run:

```
./gradlew run
```

To debug, attach debugger to port 5000.

### Query

```graphql
query GreetingQuery {
  greeting {
    message
  }
}
```

```shell
curl "http://localhost:8080/graphql" \
  -X POST \
  -d "{\"operationName\":\"GreetingQuery\",\"query\":\"query GreetingQuery{greeting{message}}\"}" \
  -H "Content-Type: application/json" 
```

### Response

```json
{
  "errors": [],
  "data": {
    "greeting": {
      "message": "你好，世界！"
    }
  },
  "extensions": null,
  "dataPresent": true
}
```
