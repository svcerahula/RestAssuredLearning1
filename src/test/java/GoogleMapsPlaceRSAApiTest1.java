import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payload.location;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class GoogleMapsPlaceRSAApiTest1 {

    @Test
    public void validateAddPlaceTest()  {
        // Given - input all details
        // When  - submit the request
        // Then  - validate the response
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        //Response resp =
        String response =         given().log().all().queryParam("key","qaclick175").contentType("application/json").body(
                        location.addPlace()
                        ).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope",equalTo("APP")).header("Server","Apache/2.4.18 (Ubuntu)")
                .extract().response().asString();

        System.out.println("Response as String for POST command : "+response);

        JsonPath js = new JsonPath(response); //for parsing json

        System.out.println("New Place added with Place ID : "+js.getString("place_id"));
        String placeId = js.getString("place_id");

        String bodyForUpdateAPICall = "{\n" +
                "\t\"place_id\" : \""+placeId+"\",\n" +
                "\t\"address\" :  \"70 Summer Walk , USA\",\n" +
                "\t\"key\" : \"qaclick175\"\n" +
                "}";

        System.out.println("Update the above created place id with new address using a PUT call");
        given().log().all().queryParam("key","qaclick175").contentType("application/json").body(bodyForUpdateAPICall)
                .when().put("/maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));


        System.out.println("Perform GET Rest call");

        String getPlaceResp = given().log().all().queryParam("key","qaclick175").queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200)
                .extract().response().asString();

        System.out.println("Output of Get Response : "+ getPlaceResp);
        JsonPath js1 = new JsonPath(getPlaceResp);

        String newAddress = js1.getString("address");

        System.out.println("new addr : "+ newAddress);
        //System.out.println("response body : "+ resp.getBody().prettyPrint());
        //System.out.println("Status code is : "+resp.getStatusCode());

    }
}
