package example.micronaut.store

import example.micronaut.Post

object Posts {
    private val posts = mutableMapOf<String, Post>()

    fun createPost(post: Post) {
        post.id?.let { posts[it] = post }
    }

    fun getPost(id: String): Post? {
        return posts[id]
    }

    fun deletePost(id: String) {
        posts.remove(id)
    }

    fun exists(id: String): Boolean {
        return posts.containsKey(id)
    }
}