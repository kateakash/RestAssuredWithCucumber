package stepDefinitions;

import apiEngine.cucumber.TestContext;
import apiEngine.enums.Context;
import apiEngine.model.Comment;
import apiEngine.model.User;
import apiEngine.response.Comments;
import apiEngine.response.Users;
import apiEngine.utils.IRestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class UsersSteps extends BaseStep {

    public UsersSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("^A list of users that are available$")
    public void listOfUsersAreAvailable() {
        IRestResponse<Users> usersResponse = getEndPoints().getUsers();
    }

    @When("^I open user with mentioed userId$")
    public void userwithId(DataTable userNumber) {
        List<List<String>> data = userNumber.asLists(String.class);
        IRestResponse<User> userResponse = getEndPoints().getUser(data.get(0).get(0));
        User post = userResponse.getBody();
        getScenarioContext().setContext(Context.USER_RESPONSE, post);
        getScenarioContext().setContext(Context.USER, post);
    }

}