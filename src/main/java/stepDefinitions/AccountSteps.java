package stepDefinitions;

import apiEngine.cucumber.TestContext;
import apiEngine.requests.AuthorizationRequest;
import io.cucumber.java.en.Given;

//This class is not used . If we need authentication then we can use this class
@Deprecated
public class AccountSteps extends BaseStep {
    String userName = "akash";
    String password = "Akash@123";

    public AccountSteps(TestContext testContext) {
        super(testContext);
    }

    @Given("^I am an authorized user$")
    public void iAmAnAuthorizedUser() {
        AuthorizationRequest authRequest = new AuthorizationRequest(userName, password);
        getEndPoints().authenticateUser(authRequest);
    }

}
