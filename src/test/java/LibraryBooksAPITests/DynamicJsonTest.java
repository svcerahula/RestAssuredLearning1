package LibraryBooksAPITests;

import LibraryBooksAPITests.payload.LibraryBookPayloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJsonTest {

    @Test
    public void postAddBook() {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().contentType("application/json").body(LibraryBookPayloads.addBookBody(
                "Java Selenium Automation","GFDK244","7th","Gosling James"
        ))
                .when().post("/Library/Addbook.php")
                .then().extract().response().asString();

        JsonPath js = new JsonPath(response);
        System.out.println(js.get("ID").toString());
    }
}
