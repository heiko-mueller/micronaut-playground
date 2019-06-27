package example.micronaut

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object PostsControllerSpec : Spek({
    val id = "123456789"
    val allPosts = "{}"
    val postForId = "{}"

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
                val response: String = client.toBlocking().retrieve("/posts/$id")

                assertEquals(postForId, response)
            }
        }

        describe("PUT @ /posts/:id") {
            it("should respond with 201") {
                val response: HttpResponse<String> = client
                    .toBlocking()
                    .exchange(
                        HttpRequest.PUT(
                            "/posts/$id",
                            ""
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