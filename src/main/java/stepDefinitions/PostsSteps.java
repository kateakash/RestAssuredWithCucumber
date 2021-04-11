package stepDefinitions;

import apiEngine.cucumber.TestContext;
import apiEngine.enums.Context;
import apiEngine.model.Post;
import apiEngine.response.Posts;
import apiEngine.utils.IRestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostsSteps extends BaseStep {

    public PostsSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("^A list of posts that are available$")
    public void listOfPostsAreAvailable() {
        IRestResponse<Posts> postsResponse = getEndPoints().getPosts();

        try {
            ObjectMapper mapper = new ObjectMapper();
            StringBuilder sb = new StringBuilder();
            sb.append(  "{\n" +
                    "  \"posts\": ").append(postsResponse.getContent()).append("}");
            Posts posts = mapper.readValue(sb.toString(), Posts.class);
            getScenarioContext().setContext(Context.POSTS, posts.posts);
            getScenarioContext().setContext(Context.POST_RESPONSE, postsResponse.getResponse());
        }catch (Exception e){
            System.out.println("Exception while converting into pojo");
        }
    }

    @Given("^I open post with mentioned postId$")
    public void postwithId(DataTable postNumber) {
        List<List<String>> data = postNumber.asLists(String.class);
        IRestResponse<Post> postsResponse = getEndPoints().getPost(data.get(0).get(0));
        Post post = postsResponse.getBody();
        getScenarioContext().setContext(Context.POST_RESPONSE, postsResponse.getResponse());
        getScenarioContext().setContext(Context.POST, post);
    }

    @When("^I open given post from list$")
    public void postwithIdFromScenarioContext(DataTable postNumber) {
        List<List<String>> data = postNumber.asLists(String.class);
        ArrayList<Post> posts =  (ArrayList<Post>) getScenarioContext().getContext(Context.POSTS);
        for (Post post:posts) {
            if(post.id==Integer.parseInt(data.get(0).get(0)))
                getScenarioContext().setContext(Context.POST, post);
            break;
        }
    }


    @Then("^I open all post from user and validate total posts$")
    public void getAllPostForUserAndValidateCount(DataTable postNumber) {
        List<List<String>> data = postNumber.asLists(String.class);
        IRestResponse<Posts> postsResponse = getEndPoints().getPostByUser(data.get(1).get(0));
        getScenarioContext().setContext(Context.POST_RESPONSE, postsResponse.getResponse());
        try {
            ObjectMapper mapper = new ObjectMapper();
            StringBuilder sb = new StringBuilder();
            sb.append(  "{\n" +
                    "  \"posts\": ").append(postsResponse.getContent()).append("}");
            Posts posts = mapper.readValue(sb.toString(), Posts.class);
            Assert.assertEquals(Integer.parseInt(data.get(1).get(1)),posts.posts.size());
        }catch (Exception e){
            System.out.println("Exception while converting into pojo");
        }
    }

    @Given("^I do new post$")
    public void doPost(List<Post> posts) {
        for (Post post : posts) {
            IRestResponse<Post> postsResponse = getEndPoints().doPost(post);
            getScenarioContext().setContext(Context.POST_RESPONSE, postsResponse.getResponse());
            getScenarioContext().setContext(Context.POST, post);
        }
    }

    @DataTableType
    public Post doPost(Map<String, String> entry) {
        return new Post(Integer.parseInt(entry.get("userId")),
                Integer.parseInt(entry.get("id")),
                entry.get("title"),
                entry.get("body"));
    }
}