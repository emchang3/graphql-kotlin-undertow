package routes

import com.fasterxml.jackson.databind.ObjectMapper
import graphql.ExecutionInput
import graphql.GraphQL
import graphql.QueryPostBody
import io.undertow.server.HttpServerExchange
import io.undertow.util.Headers

fun graph(mapper: ObjectMapper, graphql: GraphQL) = { exchange: HttpServerExchange ->
    exchange.requestReceiver.receiveFullBytes { _, message ->
        val body = mapper.readValue(message, QueryPostBody::class.java)

        val executionInput = ExecutionInput.newExecutionInput()
            .operationName(body.operationName)
            .query(body.query)
            .variables(body.variables ?: emptyMap())
            .build()

        exchange.responseHeaders.put(Headers.CONTENT_TYPE, "application/json;charset=utf-8")
        exchange.responseSender.send(mapper.writeValueAsString(graphql.execute(executionInput)))
    }
}
