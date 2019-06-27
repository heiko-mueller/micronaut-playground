package example.micronaut

import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object PostsControllerSpec : Spek({
    val allPosts = "{}"
    val post = Post(title = "My new post", id = "my-new-post")

    describe("PostsController Suite") {
        val embeddedServer: EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        val client: HttpClient = HttpClient.create(embeddedServer.url)

        describe("GET @ /posts") {
            it("should respond with all posts") {
                val response: String = client.toBlocking().retrieve("/posts")

                assertEquals(allPosts, response)
            }
        }

        describe("GET @ /posts/:id") {
            it("should respond with the expected post") {
                val response: HttpResponse<Post> = client
                    .toBlocking()
                    .exchange(
                        HttpRequest.GET<Post>("/posts/${post.id}"),
                        Argument.of(Post::class.java)
                    )

                assertEquals(post.id, response.body()!!.id)
            }
        }

        describe("PUT @ /posts/:id") {
            it("should respond with 201") {
                val response: HttpResponse<String> = client
                    .toBlocking()
                    .exchange(
                        HttpRequest.PUT(
                            "/posts/${post.id}",
                            post
                        )
                    )

                assertEquals(201, response.code())
            }
        }

        afterGroup {
            client.close()
            embeddedServer.close()
        }
    }
})
