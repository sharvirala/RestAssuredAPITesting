package com.test.tests;

import java.io.IOException;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.helpers.UserServiceHelper;
import com.test.models.CreateUserResponseBodyPojo;
import com.test.models.DeleteUserResponseBodyPojo;
import com.test.models.GetUserResponseBodyPojo;
import com.test.models.LoginResponseBodyPojo;
import com.test.models.UpdateUserResponseBodyPojo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TekArchApiE2ETest extends UserServiceHelper{
	static Response response;
	
	@Test(priority=1,enabled=true)
	
	public static void validUserLogin_TC_01() throws IOException
	{
		Response response=loginToApplication();
		
	     LoginResponseBodyPojo[] loginrespojo=loginResPojo(response);
	
		
		
		//Schema validation for login request
		//for response schema validation first we need to convert the response into schema then validate the schema
		//response.then().body(matchesJsonSchemaInClasspath("LoginUserReqSchema.json"));//getting error
		verifyStatuscode(response, 201);
		verifyTime(response, 5000l);
		verifyContentType(response,ContentType.JSON);
		
		String token=loginrespojo[0].getToken();
		System.out.println("token====="+token);
		
		// schema validation login response 
		response.then().body(matchesJsonSchemaInClasspath("ReqResSchema\\LoginUserResSchema.json"));
		
		
	}
	
	@Test(priority=2,enabled=true)
	
	public static void addUserData_TC_02() throws IOException
	{
		
		String ExpectedStatus="success";
		response=addUserData();
		
		//response.then().body(matchesJsonSchemaInClasspath("ReqResSchema\\AddUserReqSchema.json"));
		verifyStatuscode(response, 201);
		verifyTime(response, 5000l);
		verifyContentType(response,ContentType.JSON);
		response.then().body(matchesJsonSchemaInClasspath("ReqResSchema\\AddUserResSchema.json"));
		CreateUserResponseBodyPojo createUserRespons=createUserResPojo(response);//Deserialization
		String actualstaus=createUserRespons.getStatus();
		Assert.assertEquals(actualstaus, ExpectedStatus);
		
		
	}
	@Test(priority=3,enabled=true)
	
	public static void getUserData_TC_03() throws IOException
	{
		response=getUserData();
		verifyStatuscode(response, 200);
		verifyTime(response, 15000l);
		verifyContentType(response,ContentType.JSON);
		response.then().body(matchesJsonSchemaInClasspath("ReqResSchema\\GetUserResSchema.json"));
		GetUserResponseBodyPojo[] listofuserspojo=getUserResBodyPojo(response);//deserialization
		
		 id=listofuserspojo[0].getId();
		 userId=listofuserspojo[0].getUserid();
		System.out.println("id====="+id);
		
		
	}
	@Test(priority=4,enabled=true)
	
	public static void updateUserData_TC_04() throws IOException
	{
		String actualstatus="success";
		//response.then().body(matchesJsonSchemaInClasspath("ReqResSchema\\UpdateUserReqSchema.json"));
		response=updateUserData(id, userId);
		verifyStatuscode(response, 200);
		verifyTime(response, 5000l);
		verifyContentType(response,ContentType.JSON);
		UpdateUserResponseBodyPojo updateUserResponse=updateUserResPojo(response);//deserialization
		response.then().body(matchesJsonSchemaInClasspath("ReqResSchema\\UpdateUserResSchema.json"));
		String expectedstatus=updateUserResponse.getStatus();
		Assert.assertEquals(actualstatus, expectedstatus);
		
		
		
	}
	@Test(priority=5,enabled=true)
	
	public static void deleteUserData_TC_05() throws IOException
	{
		String actualstatus="success";
		//response.then().body(matchesJsonSchemaInClasspath("ReqResSchema\\DeleteUserReqSchema.json"));
		response=deleteUserData(id, userId);
		verifyStatuscode(response, 200);
		verifyTime(response, 5000l);
		verifyContentType(response,ContentType.JSON);
		DeleteUserResponseBodyPojo updateUserResponse=deleteUserResPojo(response);//deserialization
		response.then().body(matchesJsonSchemaInClasspath("ReqResSchema\\DeleteUserResSchema.json"));
		String expectedstatus=updateUserResponse.getStatus();
		Assert.assertEquals(actualstatus, expectedstatus);
		
	}
}
