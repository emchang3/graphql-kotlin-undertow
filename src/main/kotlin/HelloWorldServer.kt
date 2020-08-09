import com.expediagroup.graphql.SchemaGeneratorConfig
import com.expediagroup.graphql.TopLevelObject
import com.expediagroup.graphql.toSchema
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import graphql.GraphQL
import io.undertow.Undertow
import routing.router
import services.GreetingService

object HelloWorldServer {
    private val objectMapper = ObjectMapper().registerModule(KotlinModule())
    private val graphql: GraphQL

    init {
        val config = SchemaGeneratorConfig(supportedPackages = listOf("dto"))
        val queries = listOf(TopLevelObject(GreetingService()))
        val mutations: List<TopLevelObject> = emptyList()
        val schema = toSchema(config, queries, mutations)

        graphql = GraphQL.newGraphQL(schema).build()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val listener = Undertow.ListenerBuilder()
            .setType(Undertow.ListenerType.HTTP)
            .setHost("localhost")
            .setPort(8080)
        val server = Undertow.builder()
            .addListener(listener)
            .setHandler(router(objectMapper = objectMapper, graphql = graphql))
            .build()

        server.start()

        println("Started server at http://localhost:8080  Hit ^C to stop")
    }
}
