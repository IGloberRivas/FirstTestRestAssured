import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
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
        RestAssured.given().get(resources).then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schema));
    }

    @Test(dataProvider = "compareNodesByID")
    public void get_20_50_100(int mID, String resource, int mUserID, String mTitle, String mBody){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.given().pathParam("id", mID).expect().statusCode(200)
                .body("userId", equalTo(mUserID))
                .body("id", equalTo(mID))
                .body("title", equalTo(mTitle))
                .body("body", equalTo(mBody))
                .when().get(resource);
    }

    @Test(dataProvider = "resourceBySpecificID")
    public void callToSpecificResource(int mID, String resource){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        System.out.println(RestAssured.given().pathParam("id", mID).get(resource).body().asString());
    }
}
