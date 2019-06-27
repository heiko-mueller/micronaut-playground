package example.micronaut

data class Post(
    val title: String = "New Post",
    val content: String = "",
    val author: String = "",
    val id: String? = null,
    val createdAt: String? = null
)