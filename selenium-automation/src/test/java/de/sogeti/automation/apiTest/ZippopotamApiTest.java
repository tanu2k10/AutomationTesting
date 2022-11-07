package de.sogeti.automation.apiTest;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import de.sogeti.automation.model.api.LocationPostcodes;
import de.sogeti.automation.util.PropertiesUtil;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class ZippopotamApiTest {
	
	static RequestSpecification requestSpec;
	
	static ResponseSpecification responseSpec;
	
	@BeforeAll
    static void initAll() {
		RestAssured.baseURI = PropertiesUtil.properties.getProperty("apiUrl");
		
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setContentType(JSON);
		requestSpec = requestSpecBuilder.build();
		
		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuilder.expectResponseTime(lessThan(2L), SECONDS);
		responseSpecBuilder.expectContentType(JSON);
		
		responseSpec = responseSpecBuilder.build();
		
		
    }

	

	@Test
	void test_zipcodes_response_success_given_country_state_city() {	

		LocationPostcodes places = given()
			.spec(requestSpec)
			.pathParam("country", "de")
			.pathParam("state", "bw")
			.pathParam("city", "stuttgart")
			.log().all()
		.when()
			.get("/{country}/{state}/{city}")
		.then()
			.log().all()
			.spec(responseSpec)
			.assertThat()
			.statusCode(200)
			.body("country", is("Germany"))
			.body("state", is("Baden-Württemberg"))
			.extract().as(LocationPostcodes.class);
	
		
		List<String> placeNameForPostCode70597 = new ArrayList<>();
		for (int i = 0; i < places.getPlaces().size(); i++) {
			String postCode = places.getPlaces().get(i).getPostCode();
			if ("70597".equals(postCode)) {
				placeNameForPostCode70597.add( places.getPlaces().get(i).getPlaceName() );
			}
		}
		
		assertTrue(placeNameForPostCode70597.contains("Stuttgart Degerloch"), "For Post Code 70597 the place name has (Stuttgart Degerloch)");
		
	}
	
	
	@ParameterizedTest
	@CsvSource({
		"us, 	90210,	Beverly Hills",
		"us,	12345,	Schenectady",
		"ca,	B2R,	Waverley"
	})
	void test_api_response_placeName_for_country_and_postcode(String country, String postalCode, String placeName) {

		
		LocationPostcodes places = given()
			.spec(requestSpec)
			.pathParam("country", country)
			.pathParam("postal-code", postalCode)
			.log().all()
		.when()
			.get("/{country}/{postal-code}")
		.then()
			.log().all()
			.spec(responseSpec)
			.assertThat()
			.statusCode(200)
			.extract().as(LocationPostcodes.class);
		
		
		List<String> placeNameList = new ArrayList<>();
		for (int i = 0; i < places.getPlaces().size(); i++) {
			placeNameList.add( places.getPlaces().get(i).getPlaceName() );
		}
		
		String assertionMsg = String.format(
				"Verify in Response - Place Name:%s for each input Country:%s and Postal Code:%s.", 
				placeName, country,postalCode);

		assertTrue(placeNameList.contains(placeName), assertionMsg);
		
	}

}
