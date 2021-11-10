import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestScenario {
    static String access_token;
    @Test
    public void Auth(){
        Response responsePost = given().when().header("Authorization","Basic dXBza2lsbHNfcmVzdF9hZG1pbl9vYXV0aF9jbGllbnQ6dXBza2lsbHNfcmVzdF9hZG1pbl9vYXV0aF9zZWNyZXQ=")
                .post("http://rest-api.upskills.in/api/rest_admin/oauth2/token/client_credentials");
        responsePost.prettyPrint();
        Assert.assertEquals(responsePost.getStatusCode(),200);
        access_token= responsePost.getBody().path("access_token");
    }
    @Test
    public void Login(){
        String payload = "{\n" + "    \"username\" : \"upskills_admin\",\n" + "    \"password\" : \"Talent4$$\"\n"
                + "}";
        Response response = RestAssured.given().auth().oauth2("access_token").header("Content-Type","application/json").body(payload)
                .when().post("http://rest-api.upskills.in/api/rest_admin/login");
        response.prettyPrint();
    }
    @Test
    public void Test1(){
        Response responseCat= given().when().header("Content-Type","application-json").when().get("http://rest-api.upskills.in/api/rest_admin/categories");
        responseCat.prettyPrint();
        Assert.assertEquals(responseCat.getStatusCode(),200);
    }
    @Test
    public void Test2(){
        Response responseCat1= given().when().header("Content-Type","application-json").when().get("http://rest-api.upskills.in/api/rest_admin/categories/parent/0");
        responseCat1.prettyPrint();
        Assert.assertEquals(responseCat1.getStatusCode(),200);
    }
    @Test
    public void Test3(){
        Response responseCat2= given().when().header("Content-Type","application-json").when().get("http://rest-api.upskills.in/api/rest_admin/categories/level/2");
        responseCat2.prettyPrint();
        Assert.assertEquals(responseCat2.getStatusCode(),200);

    }


}
