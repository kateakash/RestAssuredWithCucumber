package apiEngine.utils;

import apiEngine.model.Comment;
import apiEngine.model.Post;
import apiEngine.model.User;
import apiEngine.requests.AuthorizationRequest;
import apiEngine.response.Comments;
import apiEngine.response.Posts;
import apiEngine.response.Users;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndPoints {
    private final RequestSpecification request;

    public EndPoints(String baseUrl) {
        RestAssured.baseURI = baseUrl;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
    }

    public void authenticateUser(AuthorizationRequest authRequest) {
      /*  Response response = request.body("{ \"userName\":\"" + authRequest.username + "\", \"password\":\"" + authRequest.password + "\"}").post(Route.generateToken());
        if (response.statusCode() != HttpStatus.SC_OK)
            throw new RuntimeException("Authentication Failed. Content of failed Response: " + response.toString() + " , Status Code : " + response.statusCode());
        Token tokenResponse = response.body().jsonPath().getObject("$", Token.class);
        request.header("Authorization", "Bearer " + tokenResponse.token);*/
    }

    public IRestResponse<Posts> getPosts() {
        Response response = request.get(Route.posts());
        return new RestResponse(Posts.class, response);
    }

    public IRestResponse<Post> getPost(String id) {
        Response response = request.get(Route.post(id));
        return new RestResponse(Post.class, response);
    }

    public IRestResponse<Posts> getPostByUser(String id) {
        Response response = request.get(Route.postByUser(id));
        return new RestResponse(Post.class, response);
    }

    public IRestResponse<Comments> getCommentsByPostId(String id) {
        Response response = request.get(Route.commentsByPost(id));
        return new RestResponse(Post.class, response);
    }

    public IRestResponse<Comments> getComments() {
        Response response = request.get(Route.comments());
        return new RestResponse(Comments.class, response);
    }

    public IRestResponse<Comment> getComment(String id) {
        Response response = request.get(Route.comment(id));
        return new RestResponse(Comment.class, response);
    }

    public IRestResponse<Users> getUsers() {
        Response response = request.get(Route.users());
        return new RestResponse(Users.class, response);
    }

    public IRestResponse<User> getUser(String id) {
        Response response = request.get(Route.user(id));
        return new RestResponse(User.class, response);
    }

    public IRestResponse<Post> doPost(Post post) {
        Response response = request.body(post).post(Route.posts());
        return new RestResponse(Post.class, response);
    }

    public IRestResponse<Comment> doComment(Comment comment) {
        Response response = request.body(comment).post(Route.comments());
        return new RestResponse(Post.class, response);
    }
}