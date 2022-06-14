package com.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.tests.constants.SourcePath;



public class PropertyfileUtility{
	
	public static String getPropertyValue(String key) throws IOException
	{
	String path=SourcePath.APPLICATION_PROPERTIES_PATH;//properties file path is storing in to path variable
	FileInputStream fi=new FileInputStream(new File(path));
	
	Properties pf=new Properties();
	pf.load(fi);
	String value=pf.getProperty(key);
	return value;
	}

}
