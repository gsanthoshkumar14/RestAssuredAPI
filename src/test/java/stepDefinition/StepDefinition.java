package stepDefinition;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GMap;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {

	RequestSpecification res;
	ResponseSpecification resp;
	Response response;
	static String placeId;
	TestDataBuild tdb = new TestDataBuild();

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String address, String language) throws IOException {
		
		res = given().spec(requestSpecification()).body(tdb.addPlacePayLoad(name,address,language));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpRequest)  {
		APIResources resourceAPI = APIResources.valueOf(resource);
		resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(httpRequest.equals("POST"))
		response = res.when().post(resourceAPI.getResource()).then().spec(resp).extract().response();
		else if(httpRequest.equals("GET"))
			response = res.when().get(resourceAPI.getResource()).then().spec(resp).extract().response();
		
	}

	@Then("the API call is successful with statuscode {int}")
	public void the_api_call_is_successful_with_statuscode(Integer int1) {
		assertEquals(response.statusCode(), 200);// manually added import static junit class since it is static not
													// populating automatically in eclipe
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		
		assertEquals(getJsonPath(response, key), value);
	}
	
	@Then("verify {string} created maps to {string} using {string}")
	public void verify_created_maps_to_using(String key, String name, String resource) throws IOException {

		placeId = getJsonPath(response, key);
		res = given().spec(requestSpecification()).queryParam(key, placeId);
		user_calls_with_http_request(resource, "GET");
		assertEquals(getJsonPath(response, "name"), name);
	}
	
	@Given("Deletle place payload")
	public void deletle_place_payload() throws IOException {
		res = given().spec(requestSpecification()).body(tdb.deletePlacePayLoad(placeId));
	}

}
