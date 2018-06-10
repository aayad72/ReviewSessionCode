package api.tests;



import static org.junit.Assert.*;

import org.junit.Test;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReqresApi {

	@Test
	public void getUsersTest() {
		
//		given().accept(ContentType.JSON)
//	.and().params("page",2)
//	.when().get("https://reqres.in/api/users")
//	.then().assertThat().statusCode(200);
		
		
		
		Response response = given().accept(ContentType.JSON)
				.and().params("page",2)
				.when().get("https://reqres.in/api/users");
				
				System.out.println(response.getStatusLine());
				System.out.println(response.getContentType());
				System.out.println(response.headers());
				System.out.println(response.body().asString());
				
				//add assertions for parts of response
				assertEquals(200,response.statusCode());
				assertTrue(response.contentType().contains("application/json"));
				
				Header header = new Header("X-Powered-By", "Express");
				assertTrue(response.headers().asList().contains(header));
				
				JsonPath json = response.jsonPath();
				assertEquals(12, json.getInt("total"));
				assertEquals(4, json.getInt("total_pages"));
				
				assertEquals(4, json.getInt("data.id[0]"));
				//Verify that Charles's id is 5
				System.out.println(json.getString("data.find{it.first_name=='Charles'}.id"));
				
				assertEquals(5, json.getInt("data.find{it.first_name=='Charles'}.id"));
				
				assertEquals("Tracey", json.getString("data.find{it.id==6}.first_name"));
				assertEquals("Ramos", json.getString("data.find{it.id==6}.last_name"));
				
				
		
	}

}
