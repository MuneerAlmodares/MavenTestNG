package apiRestAssured;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetStoreApiTest {
	
	@BeforeTest
	public void setup() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2/";
	}
	
	
	
	@Test (dependsOnMethods = "postApet")
	public void getPetById() {
		
		RestAssured
		.given().accept(ContentType.JSON)
		.get("/pet/20140990")
		.then().statusCode(200);
		
	}
	
	@Test
	public void findPetByStatus() {
		RestAssured
		.given().accept(ContentType.JSON).contentType("application/json").param("status", "pending")
		.when().get("/pet/findByStatus?status=available")
		.then().statusCode(200).contentType("application/json");
		
	}
	@Test (dependsOnMethods = "postCat")
	public void getPetByIdResponse() {
		Response myResopn = (Response) RestAssured 
		.given().accept(ContentType.JSON)
		.when().get("/pet/2022118");
		
		myResopn.prettyPrint();
		// verifying the status code
		myResopn.then()
		.assertThat().statusCode(200)
		.contentType("application/json");
		
		String petNme = myResopn.path("name");
		System.out.println("pet name is :" + petNme);
		Assert.assertEquals(petNme, "moner");
		
		int petId = myResopn.path("id");
		System.out.println("pet id : " + petId);
//		Assert.assertEquals(petId, "2022118");
		
		int tagId = myResopn.path("tags[0].id");
		System.out.println("tags id " + tagId);
		Assert.assertEquals(tagId, "2");
	 
		String TagsName = myResopn.path("tags[0].name");
		System.out.println("tagsnamd is :" + TagsName);
		
		// using jsonPath function
		
		String jsonpathname = myResopn.jsonPath().get("category.name");
		System.out.println("pet category name: " + jsonpathname);

		
		
	}
	
	@Test
	public void postCat() {
		String requestBody = "{\r\n"
				+ "  \"id\": 20202024,\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 1,\r\n"
				+ "    \"name\": \"BastA\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"muneer\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"Fahaad\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 2,\r\n"
				+ "      \"name\": \"aaws\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \"available\"\r\n"
				+ "}";
		Response myResponse = RestAssured
				.given().accept(ContentType.JSON).contentType("application/json").body(requestBody)
				.when().post("/pet");
		
		myResponse.then().statusCode(200).and().contentType("application/json");
		
		myResponse.prettyPrint();
	}
	
	@Test
	public void updateCat() {
		String bodyReuest = "{\r\n"
				+ "  \"id\": 20202024,\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 1,\r\n"
				+ "    \"name\": \"BastA\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"muneer\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"Fahaad\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 2,\r\n"
				+ "      \"name\": \"aaws\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \"pending\"\r\n"
				+ "}";
		
		Response catResponse = RestAssured
		.given().accept(ContentType.JSON).contentType("application/json")
		.body(bodyReuest).when().put("/pet");
		
	}
	
	@Test
	public void postAPet() {
		String requestBody = "{\r\n"
				+ "  \"id\": 20202022,\r\n"
				+ "  \"category\": {\r\n"
				+ "    \"id\": 1,\r\n"
				+ "    \"name\": \"bastA\"\r\n"
				+ "  },\r\n"
				+ "  \"name\": \"monuur\",\r\n"
				+ "  \"photoUrls\": [\r\n"
				+ "    \"fahaad\"\r\n"
				+ "  ],\r\n"
				+ "  \"tags\": [\r\n"
				+ "    {\r\n"
				+ "      \"id\": 2,\r\n"
				+ "      \"name\": \"aaws\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"status\": \"available\"\r\n"
				+ "}";
		Response myResponse = RestAssured
		.given().accept(ContentType.JSON).contentType("application/json").body(requestBody)
		.when().post("/pet");
		
		myResponse.then().statusCode(200)
		.and().contentType("application/json");
		
		
		myResponse.prettyPrint();
	
		
	}
	
	@Test
	public void deletTheCat() {
		Response deletResponse =RestAssured
		.given().accept(ContentType.JSON).contentType("application/json")
		.when().delete("/20202024");
		
		deletResponse.then().statusCode(200).contentType("application/json");
		deletResponse.body().jsonPath().get("message");
		
		
	}
	
	// creat a cat with request body in json file: Ex
	
	@Test
	public void creatCatWithJsonFile() {
		
		File catRequestBodyFile = new File(".src/test/resouurces.JsonTestData/vreatcat.json");
		
		Response myResponse = RestAssured
				.given().accept(ContentType.JSON).contentType("application/json").body(catRequestBodyFile)
				.when().post("/pet");
				
				myResponse.then().statusCode(200)
				.and().contentType("application/json");
				
				
				myResponse.prettyPrint();
			
		
	}
	
	
	
//	@AfterTest
//	public void cleanUP() {
//		deletTheCat();
		
	}
	
	
	

