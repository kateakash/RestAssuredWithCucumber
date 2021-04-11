package apiEngine.cucumber;

import apiEngine.utils.EndPoints;
import config.ConfigReader;

public class TestContext {

    private EndPoints endPoints = new EndPoints(ConfigReader.getInstance().getBaseUrl());
    private ScenarioContext scenarioContext;

    public TestContext() {
        scenarioContext = new ScenarioContext();
//        scenarioContext.setContext(Context.USER, ConfigReader.getInstance().getUserID());
    }

    public EndPoints getEndPoints() {
        return endPoints;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }

}