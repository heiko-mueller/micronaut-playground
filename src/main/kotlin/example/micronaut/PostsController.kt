package example.micronaut

import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
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
    fun getOnePost(id: Int): String {
        return "{}"
    }

    @Put("/{id}")
    fun putAPost(id: Int): HttpStatus {
        //Save the post
        return HttpStatus.CREATED
    }
}