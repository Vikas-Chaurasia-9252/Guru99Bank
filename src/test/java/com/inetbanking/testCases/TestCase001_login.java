package com.inetbanking.testCases;

import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

import junit.framework.Assert;

public class TestCase001_login extends BaseTest {
	
	
	@Test
	public void loginMe(){
		
		LoginPage loginuser = new LoginPage(driver);
		loginuser.login(userID, passWord);
		Assert.assertEquals(true, loginuser.getPageTitle().contains("Welcome To Manager's Page of Guru99 Bank"));
	}

}
