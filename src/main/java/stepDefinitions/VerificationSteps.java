package stepDefinitions;

import apiEngine.cucumber.TestContext;
import apiEngine.enums.Context;
import apiEngine.model.Comment;
import apiEngine.model.Post;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class VerificationSteps extends BaseStep {

    public VerificationSteps(TestContext testContext) {
        super(testContext);
    }

    @Then("^I validate the contents of that post$")
    public void postVerification(List<Post> posts) {
        Post post = (Post) getScenarioContext().getContext(Context.POST);
        Response postIRestResponse = (Response) getScenarioContext().getContext(Context.POST_RESPONSE);

        int[] ints = {200,201};
        List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
        Assert.assertTrue(list.contains(postIRestResponse.getStatusCode()));
        Assert.assertEquals(posts.get(0).id, post.id);
    }

    @Then("^I validate the contents of that comment$")
    public void commentVerification(List<Comment> comments) {
        Comment comment = (Comment) getScenarioContext().getContext(Context.COMMENT);
        Response commentIRestResponse = (Response) getScenarioContext().getContext(Context.COMMMENT_RESPONSE);

        int[] ints = {200,201};
        List<Integer> list = Arrays.stream(ints).boxed().collect(Collectors.toList());
        Assert.assertTrue(list.contains(commentIRestResponse.getStatusCode()));
        Assert.assertEquals(comments.get(0).id, comment.id);
    }

}
