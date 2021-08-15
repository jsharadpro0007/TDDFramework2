package tests;

import apiresources.GooglePayload;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class restAPITest {


    @Test
    public void addPlace()
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        RequestSpecification req = new RequestSpecBuilder().setContentType(ContentType.JSON)
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key","quclick123").build();
        ResponseSpecification resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
                .expectStatusCode(200).build();

        RequestSpecification res = given().spec(req).body(GooglePayload.addPlacePayload());
        Response response = res.when().post()
                .then().spec(resSpec).extract().response();


        String resp = given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(GooglePayload.addPlacePayload())
               .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
        .header("server","Apache/2.4.18 (Ubuntu)").extract().body().asString();

        System.out.println(resp);
        JsonPath jp = new JsonPath(resp);
        String placeId = jp.getString("place_id");

        System.out.println(placeId);

    }

@Test
    public void updatePlace()
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body(GooglePayload.updatePlacePayload("fbe7f3a463c8bec9c65f234dcbc5ddcc"))
                .when().put("/maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));

    }

    @Test
    public void getPlace()
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String resp = given().log().all().queryParam("key","qaclick123").queryParam("place_id","fbe7f3a463c8bec9c65f234dcbc5ddcc")
                .when().get("/maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200)
                .body("address",equalTo("95 winter walk, USA")).extract().asString();

        JsonPath jp = new JsonPath(resp);
        String addressNm = jp.getString("address");

        System.out.println(addressNm);
        Assert.assertEquals(addressNm,"95 winter walk, USA");
    }


    @Test
    public void demo()
    {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        SessionFilter filter = new SessionFilter();


        given().header("Content-Type","application/json").body("")
                .log().all().filter(filter).when().post("")
                .then().extract().response().asString();

        given().pathParam("IssueKey","10101").log().all().header("Content-Type","application/json")
                .body("").filter(filter)
                .when().post("/rest/api/2/issue/{IssueKey}/comment")
                .then().log().all().assertThat().statusCode(201);

        given().pathParam("IssueKey","10101").log().all()
                .when().get("/rest/api/2/issue/{IssueKey}/get")
                .then().log().all().assertThat().statusCode(200);
    }

}
