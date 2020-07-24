package apiPackages;


import org.testng.Assert;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
public class FirstApi {
	
	
	@BeforeMethod
	
	 public void beforeMethod() {
		RestAssured.baseURI="http://demo4032024.mockable.io/apitest";
    }
	
	
	@Test
	
	
	public void statusCodeVerification()
	{
		
		given().header("Content-Type","application/json").when().get().then().assertThat().statusCode(200);
		
		
	}
	@Test
	public void getHeadertype()
	{
			
		Assert.assertEquals(given().when().get().header("Content-Type"), "application/json; charset=UTF-8");
	}
	
	@Test
	public void getBody()
	{
		String json = given().header("Content-Type","application/json").when().get().getBody().asString();
		JsonPath jsonPath = new JsonPath(json);
		int str = jsonPath.getJsonObject("status");
		
		
		Assert.assertEquals(str, 200);
		int age=jsonPath.get("employeeData[0].age");
		
		Assert.assertEquals(age, 25);
		String role=jsonPath.get("employeeData[0].role");
		Assert.assertEquals(role, "QA Automation Developer");
		
		String Dob=jsonPath.get("employeeData[0].dob");
		Assert.assertEquals(Dob, "25-02-1994");
		String message = jsonPath.getJsonObject("message");
		Assert.assertEquals(message, "data retrieved successful");
		
	}
	
	@Test
	
	public void getCompany()
	{
		String json = given().header("Content-Type","application/json").when().get().getBody().asString();
		JsonPath jsonPath = new JsonPath(json);
		String company=jsonPath.get("employeeData[0].company");
		Assert.assertEquals(company, "ABC infotech");
		
	}
	

}
