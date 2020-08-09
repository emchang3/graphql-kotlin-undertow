package routing

import com.fasterxml.jackson.databind.ObjectMapper
import graphql.GraphQL
import io.undertow.Handlers
import io.undertow.server.RoutingHandler
import io.undertow.util.Headers
import io.undertow.util.Methods
import routes.graph

fun router(objectMapper: ObjectMapper, graphql: GraphQL): RoutingHandler {
    val router = Handlers.routing()

    router.add(Methods.POST, "/graphql", graph(objectMapper, graphql))

    router.setFallbackHandler { exchange ->
        exchange.responseHeaders.put(Headers.CONTENT_TYPE, "text/plain;charset=utf-8")
        exchange.statusCode = 404
        exchange.responseSender.send("無")
    }

    return router
}
