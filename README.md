# Micronaut Playground

## API to submit and fetch posts

* PUT @ /posts/:id
  * Body: JSON
    * title: String
    * content: String
    * author: String
* GET @ /posts
  * Response: JSON
    * posts: Array of Objects
      * title: String
      * author: String
      * id: String
      * createdAt: String
* GET @ /posts/:id
  * Response: JSON
    * title: String
    * content: String
    * author: String
    * id: String
    * createdAt: String