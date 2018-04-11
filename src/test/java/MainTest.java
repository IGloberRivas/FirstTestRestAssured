import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class MainTest extends ConfigurationTest{

    @Test(dataProvider = "resources")
    public void getResourcesStatusCode(String resource){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        int statusCode = RestAssured.given().get(resource).getStatusCode();
        System.out.println(resource + " " +statusCode);
        Assert.assertEquals(statusCode, 200);
    }

    @Test(dataProvider = "resources")
    public void getResourcesBody(String resource){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        System.out.println(resource + " --------------------------------------------------");
        System.out.println(" --------------------------------------------------------------");
        System.out.println(RestAssured.given().get(resource).getBody().asString());
    }

    @Test(dataProvider = "resourcesSchema")
    public void getValidationSchema(String resources, String schema){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = RestAssured.given().get(resources);
        Assert.assertTrue(JsonSchemaValidator.matchesJsonSchemaInClasspath(schema).matches(response.asString()));
    }

    @Test(dataProvider = "compareNodesByID")
    public void get_20_50_100(Integer mID, String resource, Integer mUserID, String mTitle, String mBody){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = RestAssured.given().pathParam("id", mID).expect().statusCode(200).when().get(resource);

        Assert.assertEquals(response.path("userId"), mUserID);
        Assert.assertEquals(response.path("id"), mID);
        Assert.assertEquals(response.path("title"), mTitle);
        Assert.assertEquals(response.path("body"), mBody);

    }

    @Test(dataProvider = "compareNodesByID")
    public void get_20_50_100_Version2(int mID, String resource, int mUserID, String mTitle, String mBody){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Post postResponse = RestAssured.given().pathParam("id", mID).expect().statusCode(200).when().get(resource).as(Post.class);
        Post postExpected = new Post(mUserID, mID, mTitle, mBody);

        Assert.assertEquals(postResponse.userId, postExpected.userId);
        Assert.assertEquals(postResponse.id, postExpected.id);
        Assert.assertEquals(postResponse.title, postExpected.title);
        Assert.assertEquals(postResponse.body, postExpected.body);
    }

    @Test(dataProvider = "resourceBySpecificID")
    public void callToSpecificResource(int mID, String resource){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        System.out.println(RestAssured.given().pathParam("id", mID).get(resource).body().asString());
    }
}
