package apiRestAssured;


import java.io.File;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetStoreApiTest {
	
	
	int catId;
	int petID;
	
	@BeforeTest
	public void setup() {
		RestAssured.baseURI = "https://petstore.swagger.io/v2/";
	}
	
	
	
	@Test (dependsOnMethods = "postAPet")
	public void getPetById() {
		
		RestAssured
		.given().accept(ContentType.JSON)
		.get("/pet/2323226")
		.then().statusCode(200);
		
	}
	
	@Test
	public void findPetByStatus() {
		RestAssured
		.given().accept(ContentType.JSON).contentType("application/json").param("status", "pending")
		.when().get("/pet/findByStatus")
		.then().statusCode(200).contentType("application/json");
		
	}
	@Test (dependsOnMethods = {"postCat","updateCat"})
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
		Assert.assertEquals(petNme, "Ember");
		
		int petId = myResopn.path("id");
		System.out.println("pet id : " + petId);
		Assert.assertEquals(petId, "232323");
		
		int tagId = myResopn.path("tags[0].id");
		System.out.println("tags id " + tagId);
		Assert.assertEquals(tagId, "18");
		
	      // how to acces the tags name from the seconed object
		String Tags2Name = myResopn.path("tags[1].name");
		System.out.println("tagsnamd is :" + Tags2Name);
		Assert.assertEquals(Tags2Name,"Anatolian");
		// using jsonPath function
		
		String categoryName = myResopn.jsonPath().get("category.name");
		System.out.println("pet category name: " + categoryName);
		Assert.assertEquals(categoryName, "cat");
		
		String catStatus = myResopn.body().jsonPath().get("status");
		Assert.assertEquals(catStatus, "pending");

		
		
	}
	
	@Test
	public void postCat() {
		String requestBody = "{\n" + "    \"id\": 232323,\n" + "    \"category\": {\n" + "        \"id\": 21,\n"
				+ "        \"name\": \"cat\"\n" + "    },\n" + "    \"name\": \"Ember\",\n" + "    \"photoUrls\": [\n"
				+ "        \"string\"\n" + "    ],\n" + "    \"tags\": [\n" + "        {\n"
				+ "            \"id\": 18,\n" + "            \"name\": \"persian\"\n" + "        },\n" + "        {\n"
				+ "            \"id\": 2,\n" + "            \"name\": \"Anatolian\"\n" + "        }\n" + "    ],\n"
				+ "    \"status\": \"available\"\n" + "}";
		
		Response myResponse = RestAssured
				.given().accept(ContentType.JSON).contentType("application/json").body(requestBody)
				.when().post("/pet");
		
		myResponse.then().statusCode(200).and().contentType("application/json");
		
		myResponse.prettyPrint();
		catId = myResponse.jsonPath().get("id");
	}
	// update the cat status to pending
	@Test(dependsOnMethods = "postCat")
	public void updateCat() {
		String bodyReuest = "{\n" + "    \"id\": 232323,\n" + "    \"category\": {\n" + "        \"id\": 21,\n"
				+ "        \"name\": \"cat\"\n" + "    },\n" + "    \"name\": \"Ember\",\n" + "    \"photoUrls\": [\n"
				+ "        \"string\"\n" + "    ],\n" + "    \"tags\": [\n" + "        {\n"
				+ "            \"id\": 18,\n" + "            \"name\": \"persian\"\n" + "        },\n" + "        {\n"
				+ "            \"id\": 2,\n" + "            \"name\": \"Anatolian\"\n" + "        }\n" + "    ],\n"
				+ "    \"status\": \"pending\"\n" + "}";
		
		Response catResponse = RestAssured
		.given().accept(ContentType.JSON).contentType("application/json")
		.body(bodyReuest).when().put("/pet");
		
		catResponse.then().statusCode(200).and().contentType("application/json");
		Assert.assertEquals(catResponse.body().jsonPath().get("status"), "pending");
	}
	
	@Test
	public void postAPet() {
		String requestBody = "{\n" + "    \"id\": 2323226,\n" + "    \"category\": {\n" + "        \"id\": 21,\n"
				+ "        \"name\": \"dog\"\n" + "    },\n" + "    \"name\": \"Toby\",\n" + "    \"photoUrls\": [\n"
				+ "        \"string\"\n" + "    ],\n" + "    \"tags\": [\n" + "        {\n"
				+ "            \"id\": 29,\n" + "            \"name\": \"Sheperd\"\n" + "        },\n" + "        {\n"
				+ "            \"id\": 31,\n" + "            \"name\": \"Husky\"\n" + "        }\n" + "    ],\n"
				+ "    \"status\": \"available\"\n" + "}";
		Response myResponse = RestAssured
		.given().accept(ContentType.JSON).contentType("application/json").body(requestBody)
		.when().post("/pet");
		
		myResponse.then().statusCode(200)
		.and().contentType("application/json");
		
		
		myResponse.prettyPrint();
		
		// storing the pet id in a global variable (instance variable)
		petID = myResponse.jsonPath().getInt("id");
	
		
	}
	
	@Test
	public void deletThePet() {
		Response deleteResponse = RestAssured
				.given().accept(ContentType.JSON).contentType("application/json")
				.when().delete("/pet/" + petID);
		
		deleteResponse.then().statusCode(200).contentType("application/json");
		Assert.assertEquals(deleteResponse.body().jsonPath().get("message"), String.valueOf(petID));
		
		
	}
	public void deleteTheCat() {
		Response deleteResponse = RestAssured
		.given().accept(ContentType.JSON).contentType("application/json")
		.when().delete("/pet/" + catId);
		
		deleteResponse.then().statusCode(200).contentType("application/json");
		Assert.assertEquals(deleteResponse.body().jsonPath().get("message"), String.valueOf(catId));
	}
	
	// creat a cat with request body in json file: Ex
	
	@Test
	public void creatCatWithJsonFile() {
		
		File catRequestBodyFile = new File("./src/test/resouurces.JsonTestData/vreatcat.json");
		
		Response myResponse = RestAssured
				.given().accept(ContentType.JSON).contentType("application/json").body(catRequestBodyFile)
				.when().post("/pet");
				
				myResponse.then().statusCode(200)
				.and().contentType("application/json");
				
				
				myResponse.prettyPrint();
			
				catId = myResponse.jsonPath().get("id");
	}
	
	// RestAssured chain validation
		@Test
		public void chainValidation() {
	        File catRequestBodyFile = new File("./src/test/resources/JsonTestData/creatcat.json");
			
			Response myResponse = 
					RestAssured
					.given().accept(ContentType.JSON).contentType("application/json")
					.body(catRequestBodyFile)
					.when().post("/pet");
			
			myResponse
			.then().assertThat().statusCode(200)
			.and().assertThat().contentType("application/json")
			.and().assertThat().body("id", equalTo(345555))
			.and().assertThat().body("category.id", equalTo(21))
			.and().assertThat().body("category.name", equalTo("cat"))
			.and().assertThat().body("name", equalTo("Mimi"))
			.and().assertThat().body("tags[0].id", equalTo(18))
			.and().assertThat().body("tags[0].name", equalTo("persian"))
			.and().assertThat().body("tags[1].id", equalTo(2))
			.and().assertThat().body("tags[1].name", equalTo("Anatolian"))
			.and().assertThat().body("status", equalTo("available"));
			
			myResponse.prettyPrint();
			catId = myResponse.jsonPath().get("id");
		}
	
	
	
	@AfterTest
	public void cleanUP() {
		deleteTheCat();
		deletThePet();
	}
	
	// negative test cases 
		@Test
		public void invalidIdUpdateCat() {
			String catRequestBody = "{\n" + "    \"id\": '232323',\n" + "    \"category\": {\n" + "        \"id\": 21,\n"
					+ "        \"name\": \"cat\"\n" + "    },\n" + "    \"name\": \"Ember\",\n" + "    \"photoUrls\": [\n"
					+ "        \"string\"\n" + "    ],\n" + "    \"tags\": [\n" + "        {\n"
					+ "            \"id\": 18,\n" + "            \"name\": \"persian\"\n" + "        },\n" + "        {\n"
					+ "            \"id\": 2,\n" + "            \"name\": \"Anatolian\"\n" + "        }\n" + "    ],\n"
					+ "    \"status\": \"pending\"\n" + "}";
			
			Response catResponse = RestAssured
			.given().accept(ContentType.JSON).contentType("application/json").body(catRequestBody)
			.when().put("/pet");
			
			catResponse.then().statusCode(400).and().contentType("application/json");
			catResponse.prettyPrint();
			Assert.assertEquals(catResponse.body().jsonPath().get("message"), "bad input");
			
		}
}
	
	
	

