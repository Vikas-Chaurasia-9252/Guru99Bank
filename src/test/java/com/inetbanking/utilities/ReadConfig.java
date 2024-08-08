package com.inetbanking.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	Properties prop;
	FileInputStream file;
	public ReadConfig() throws IOException {
		prop = new Properties();
		try {
			file = new FileInputStream(System.getProperty("user.dir")+"//Configuration//config.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			e.getMessage();
		}
		
	}
	
	public String getURL() {
		return prop.getProperty("URL");
	}
	public String getUserID() {
		return prop.getProperty("userID");
	}
	public String getPassword() {
		return prop.getProperty("passWord");
	}
	public String getEdgePath() {
		return prop.getProperty("edgepath");
	}
	public String getChromePath() {
		return prop.getProperty("chromepath");
	}
	
	
	

}
