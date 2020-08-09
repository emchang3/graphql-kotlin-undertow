# hello-world-undertow-kotlin-gradle

A simple server with a single REST endpoint.

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

```shell script
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