package apiTests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

//This class is not used . It is only for debugging purpose
@Deprecated
public class E2E_Tests {

    public static void main(String[] args) {
//        String userID = "4bbf0658-bc9d-44eb-be71-d99b44303507";
//        String userName = "akash";
//        String password = "Akash@123";
        String baseUrl = "https://jsonplaceholder.typicode.com";

        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();


        //Step - 1 TOKEN API I did not see in example
        //Test will start from generating Token for Authorization
        request.header("Content-Type", "application/json");

//        Response response = request.body("{ \"userName\":\"" + userName + "\", \"password\":\"" + password + "\"}")
//                .post("/Account/v1/GenerateToken");

//        Assert.assertEquals(response.getStatusCode(), 200);

//        String jsonString = response.asString();
//        Assert.assertTrue(jsonString.contains("token"));

        //This token will be used in later requests
//        String token = JsonPath.from(jsonString).get("token");

        //Step - 2
        // Get posts - No Auth is required for this.
        Response response = request.get("/posts");

        Assert.assertEquals(response.getStatusCode(), 200);

        String jsonString = response.asString();
        List<Map<String, String>> posts = JsonPath.from(jsonString).get("id");
        Assert.assertTrue(posts.size() > 0);

        //This post will be used in later requests, to add the book with respective isbn
//        Map<String,String> postId = posts.get(0);

        //Step - 3
        // Add a post - with no Auth
        //The token we had saved in the variable before from response in Step 1,
        //we will be passing in the headers for each of the succeeding request
        request.header("Content-Type", "application/json");
//        header("Authorization", "Bearer " + token)
        response = request.body("{\n" +
                "\"Id\": 1,\n" +
                "\"Title\": \"Akash\",\n" +
                "\"Body\": \"Akash\",\n" +
                "\"UserId\": 1\n" +
                "}").post("/posts/");

        Assert.assertEquals(201, response.getStatusCode());


        //Step - 4
        // Delete a book - with Auth
        request.header("Content-Type", "application/json");

        response = request.body("")
                .delete("/posts/" + 1);

        Assert.assertEquals(200, response.getStatusCode());

        //Step - 5
        // Get post of particular user
        request.header("Content-Type", "application/json");

        response = request.get("/posts?userId=" + 1);
        Assert.assertEquals(200, response.getStatusCode());

        jsonString = response.asString();
        List<Map<String, String>> postOfUser = JsonPath.from(jsonString).get("id");
        Assert.assertEquals(10, postOfUser.size());
    }
}
