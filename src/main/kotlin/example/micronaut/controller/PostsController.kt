package example.micronaut.controller

import example.micronaut.Post
import example.micronaut.store.Posts
import io.micronaut.http.HttpResponse
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
    fun getOnePost(id: String): HttpResponse<Post> {
        Posts.getPost(id)?.let {
            return HttpResponse.ok(it)
        }

        return HttpResponse.notFound()
    }

    @Put("/{id}")
    fun putAPost(id: String, @Body post: Post): HttpStatus {
        if (Posts.exists(id)) {
            return HttpStatus.CONFLICT
        }

        try {
            post.id = id
            post.createdAt = "2019-06-27"
            
            Posts.createPost(post)
        } catch (e: Exception) {
            return HttpStatus.INTERNAL_SERVER_ERROR
        }

        return HttpStatus.CREATED
    }
}