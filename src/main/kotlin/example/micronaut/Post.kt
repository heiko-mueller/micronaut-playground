package example.micronaut

data class Post(
    var title: String = "New Post",
    var content: String = "",
    var author: String = "",
    var id: String? = null,
    var createdAt: String? = null
)