package com.test.helpers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matcher;
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
import com.test.utils.PropertyfileUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class ReusableMetods {
	
	//@BeforeClass
	public  String getBaseUri() throws IOException
	{
		
		String uri=PropertyfileUtility.getPropertyValue("tekArchuri");
		return uri;
		
	}
	public static LoginPojo loginPojo() throws IOException
	{
		String username=PropertyfileUtility.getPropertyValue("username");
		String password=PropertyfileUtility.getPropertyValue("password");
		//creating object for the LoginPojo class
				LoginPojo obj=new LoginPojo();
				obj.setUsername(username);
				obj.setPassword(password);
				return obj;
	}
	
	public static  LoginResponseBodyPojo[] loginResPojo(Response response)
	{
	LoginResponseBodyPojo[] loginResponsepojo=response.as(LoginResponseBodyPojo[].class);//deserialization
	return loginResponsepojo;
		 
	}
	
	
	public static GetUserResponseBodyPojo[] getUserResBodyPojo(Response response)
	{
		GetUserResponseBodyPojo[] getUserResPojo=response.as(GetUserResponseBodyPojo[].class);
		return getUserResPojo;
		
	}
	public static  CreateUserPojo createUserReqPojo()
	{
		CreateUserPojo createUserReqPojo=new  CreateUserPojo();
		createUserReqPojo.setAccountno("TA-1100990z");
		createUserReqPojo.setDepartmentno("9");
		createUserReqPojo.setSalary("3456");
		createUserReqPojo.setPincode("666666");
		return createUserReqPojo;
		
	}

	public static CreateUserResponseBodyPojo createUserResPojo(Response response)
	
	{
		CreateUserResponseBodyPojo updateUserResPojo=response.as(CreateUserResponseBodyPojo.class);
		return updateUserResPojo;
	}
	
	public static  UpdateUserPojo updateUserReqPojo(String id,String userid)
	{
		UpdateUserPojo updateUserReqPojo=new  UpdateUserPojo();
		updateUserReqPojo.setAccountno("TA-110099ab");
		updateUserReqPojo.setDepartmentno("9");
		updateUserReqPojo.setSalary("3456");
		updateUserReqPojo.setPincode("666333");
		updateUserReqPojo.setId(id);
		updateUserReqPojo.setUserid(userid);
		return updateUserReqPojo;
		
	}
	
	public static UpdateUserResponseBodyPojo updateUserResPojo(Response response)
	
	{
		UpdateUserResponseBodyPojo updateUserResPojo=response.as(UpdateUserResponseBodyPojo.class);
		return updateUserResPojo;
	}
	public static DeleteUserRequestBodyPojo deleteUserReqPojo(String id,String userid)
	{
		DeleteUserRequestBodyPojo deleteUserReqPojo=new  DeleteUserRequestBodyPojo();
		
		deleteUserReqPojo.setId(id);
		deleteUserReqPojo.setUserid(userid);
		return deleteUserReqPojo;
		
	}
	public static DeleteUserResponseBodyPojo deleteUserResPojo(Response response)
	{
		DeleteUserResponseBodyPojo deleteUserResPojo=response.as(DeleteUserResponseBodyPojo.class);
		return deleteUserResPojo;
	}
	

	
	public static void verifyStatuscode(Response response,int statuscode1)
	{
		Response  res=response;
		res.then().statusCode(statuscode1);
	}
	
	
	
	public static void verifyTime(Response response,long time)
	{
		Response  res=response;
		res.then().time(Matchers.lessThan(time));
		
	}
	public static void getStatus(Response response,String status)
	{
		//response.then().get[0];
		
	}
	public static void verifyContentType(Response response,ContentType contentType)
	{
		Response res=response;
		res.then().contentType(contentType);
	}

}
