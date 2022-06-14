package com.test.helpers;




import java.io.IOException;
import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;

import com.test.models.CreateUserPojo;
import com.test.models.CreateUserResponseBodyPojo;
import com.test.models.DeleteUserRequestBodyPojo;
import com.test.models.DeleteUserResponseBodyPojo;
import com.test.models.GetUserResponseBodyPojo;
import com.test.models.LoginPojo;
import com.test.models.LoginResponseBodyPojo;
import com.test.models.UpdateUserPojo;
import com.test.models.UpdateUserResponseBodyPojo;
//import com.tests.constants.EndPionts;
//import com.tests.constants.SourcePath;
import com.tests.constants.EndPionts;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import  io.restassured.module.jsv.JsonSchemaValidator;

//import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class UserServiceHelper extends ReusableMetods{
	
	static String token;
	 protected static String id;
	protected static String userId;
	@BeforeClass
	public  void setup() throws IOException
	{
     RestAssured.baseURI=getBaseUri();
	
	}
	
	
	public static Response loginToApplication() throws IOException
	{
		
		LoginPojo obj=loginPojo();
		RequestSpecification request=RestAssured.given().contentType(ContentType.JSON)
		.body(obj);
		//here we are  sending the fully loaded LoginPojo class object instead of sending this data {"username":"S.sharvirala@ta.com","password":"S.sharvirala@123"}
		
		
		//Schema validation for login request
		//for response schema validation first we need to convert the response into schema then validate the schema
		//.then().body(matchesJsonSchemaInClasspath("LoginUserReqSchema.json"));//getting error
		Response response=	request.when()
		.post(EndPionts.LOGIN);
		response.prettyPrint();
	
		return response ;
		
		
	}
	
	
	public static String getToken() throws IOException
	{
		Response response=loginToApplication();
		token=response.jsonPath().get("[0].token");
		return token;
	}
	public static Response getUserData() throws IOException
	{
		token=getToken();
		Header header=new Header("token",token);
		RequestSpecification request=	RestAssured.given().header(header).log().all();
		
		Response response=	request.when()
		.get(EndPionts.GET_DATA);
		
		//response.prettyPrint();
		//	ArrayList obj1=response.jsonPath().get("findAll{it->it.accountno==\"TA-22123101\"}");
		//System.out.println(obj1);//printing the record
		
		return response;		
				
				
	
		
	}
	public static Response addUserData() throws IOException
	{
		CreateUserPojo obj=createUserReqPojo( );
		token=getToken();
		Header header=new Header("token",token);//setting token variable in header
		RequestSpecification request=	RestAssured.given().header(header)
		.body(obj)
		.contentType(ContentType.JSON);
		
		Response response=	request.when()
		.post(EndPionts.ADD_DATA);
		
		//response.prettyPrint();
	
		
		return response;
		
	}
	public static Response updateUserData(String id,String userId) throws IOException
	{
	
		
		token=getToken();
		UpdateUserPojo obj=updateUserReqPojo(id,userId);
		Header header=new Header("token",token);
		RequestSpecification request=	RestAssured.given().header(header)
				.contentType(ContentType.JSON)
		.body(obj);//serialization
		
		
		Response response=	request.when()
		.put(EndPionts.UPDATE_DATA);
		return response;
	}
	public static Response deleteUserData(String id,String userId) throws IOException
	{
		token=getToken();
		DeleteUserRequestBodyPojo obj=deleteUserReqPojo(id,userId);
		Header header=new Header("token",token);
		RequestSpecification request=	RestAssured.given().header(header)
		.body(obj)
		.contentType(ContentType.JSON);
		
		Response response=	request.when()
		.delete(EndPionts.DELETE_DATA);
		//response.prettyPrint();
				
		
				
		return response;
	}
	
}
