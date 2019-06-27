package example.micronaut.controller

import example.micronaut.Post
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.Put

@Controller("/posts")
class PostsController {

    @Get("/")
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllPosts(): String {
        return "{}"
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getOnePost(id: String): Post {
        return Post(
            title = "New Post",
            content = "Start writing...",
            author = "You",
            id = id,
            createdAt = "2019-06-27"
        )
    }

    @Put("/{id}")
    fun putAPost(id: String, @Body post: Post): HttpStatus {
        println(post)
        //TODO: save post
        return HttpStatus.CREATED
    }
}