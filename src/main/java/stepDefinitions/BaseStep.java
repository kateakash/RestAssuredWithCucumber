package stepDefinitions;

import apiEngine.cucumber.ScenarioContext;
import apiEngine.cucumber.TestContext;
import apiEngine.utils.EndPoints;

public class BaseStep {

    private EndPoints endPoints;
    private ScenarioContext scenarioContext;

    public BaseStep(TestContext testContext) {
        endPoints = testContext.getEndPoints();
        scenarioContext = testContext.getScenarioContext();
    }

    public EndPoints getEndPoints() {
        return endPoints;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}