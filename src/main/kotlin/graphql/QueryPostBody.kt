package graphql

data class QueryPostBody(
    val operationName: String,
    val query: String,
    val variables: Map<String, Any>?
)
