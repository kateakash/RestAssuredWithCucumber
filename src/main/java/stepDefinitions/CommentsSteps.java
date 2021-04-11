package stepDefinitions;

import apiEngine.cucumber.TestContext;
import apiEngine.enums.Context;
import apiEngine.model.Comment;
import apiEngine.model.Post;
import apiEngine.response.Comments;
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

public class CommentsSteps extends BaseStep {

    public CommentsSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("^A list of comments that are available$")
    public void listOfCommentsAreAvailable() {
        IRestResponse<Comments> commentsResponse = getEndPoints().getComments();

        try {
            ObjectMapper mapper = new ObjectMapper();
            StringBuilder sb = new StringBuilder();
            sb.append(  "{\n" +
                    "  \"comments\": ").append(commentsResponse.getContent()).append("}");
            Comments comments = mapper.readValue(sb.toString(), Comments.class);
            getScenarioContext().setContext(Context.COMMENTS, comments.comments);
            getScenarioContext().setContext(Context.COMMMENT_RESPONSE, commentsResponse.getResponse());
        }catch (Exception e){
            System.out.println("Exception while converting into pojo");
        }
    }

    @Given("^I open comment with mentioned commentId$")
    public void commentwithId(DataTable commentNumber) {
        List<List<String>> data = commentNumber.asLists(String.class);
        IRestResponse<Comment> commentsResponse = getEndPoints().getComment(data.get(0).get(0));
        Comment comment = commentsResponse.getBody();
        getScenarioContext().setContext(Context.COMMMENT_RESPONSE, commentsResponse.getResponse());
        getScenarioContext().setContext(Context.COMMENT, comment);
    }

    @When("^I open given comment from list$")
    public void commentwithIdFromScenarioContext(DataTable postNumber) {
        List<List<String>> data = postNumber.asLists(String.class);
        ArrayList<Comment> comments =  (ArrayList<Comment>) getScenarioContext().getContext(Context.COMMENTS);
        for (Comment comment:comments) {
            if(comment.id==Integer.parseInt(data.get(0).get(0)))
                getScenarioContext().setContext(Context.COMMENT, comment);
            break;
        }
    }

    @Then("^I open all comments from user for given post and validate total comments$")
    public void getAllCommentsForUserAndValidateCount(DataTable postNumber) {
        List<List<String>> data = postNumber.asLists(String.class);
        IRestResponse<Comments> postsResponse = getEndPoints().getCommentsByPostId(data.get(1).get(0));
        getScenarioContext().setContext(Context.COMMMENT_RESPONSE, postsResponse.getResponse());
        try {
            ObjectMapper mapper = new ObjectMapper();
            StringBuilder sb = new StringBuilder();
            sb.append(  "{\n" +
                    "  \"comments\": ").append(postsResponse.getContent()).append("}");
            Comments comments = mapper.readValue(sb.toString(), Comments.class);
            Assert.assertEquals(Integer.parseInt(data.get(1).get(1)),comments.comments.size());
        }catch (Exception e){
            System.out.println("Exception while converting into pojo");
        }
    }

    @When("^I post comment on above post$")
    public void doPostComment(List<Comment> comments) {
        for (Comment comment : comments) {
            IRestResponse<Comment> postsResponse = getEndPoints().doComment(comment);
            getScenarioContext().setContext(Context.COMMMENT_RESPONSE, postsResponse.getResponse());
            getScenarioContext().setContext(Context.COMMENT, comment);
        }
    }

    @DataTableType
    public Comment doPostComment(Map<String, String> entry) {
        return new Comment(Integer.parseInt(entry.get("postId")),
                Integer.parseInt(entry.get("id")),
                entry.get("name"),
                entry.get("email"),
                entry.get("body"));
    }

}