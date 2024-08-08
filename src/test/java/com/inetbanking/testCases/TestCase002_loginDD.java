package com.inetbanking.testCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.testData.XLUtils;

import junit.framework.Assert;

public class TestCase002_loginDD extends BaseTest{
	
	@Test(dataProvider="LoginCredentials")
	public void loginMe(String user, String pass) throws InterruptedException{
	
		LoginPage loginuser = new LoginPage(driver);
		loginuser.login(user, pass);
		if(isAlertPresent()) {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
			String message = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			//System.out.println("Test case failed");
			System.out.println("Test Case is failed with this error = "+message);
			Assert.assertTrue("UserID is not vaild or password is wrong", false);
		}
		else {
			loginuser.logout();
			//System.out.println("Test case Passed");
			Assert.assertTrue(true);
		}
		
	}
	
	public boolean isAlertPresent() {
		
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e) {
			return false;
		}
		
	}
	
	@DataProvider(name="LoginCredentials")
	public String[][] getData() throws IOException {
		XLUtils obj = new XLUtils(System.getProperty("user.dir")+"\\src\\test\\java\\com\\inetbanking\\testData\\LoginTestData.xlsx" , "Sheet1");
		int rowCount = obj.getRowCount();
		int cellCount = obj.getCellCount();
		//System.out.println("Rows="+ rowCount + " column= "+ cellCount);
		String data[][] = new String[rowCount][cellCount];
		for(int r=1 ; r<=rowCount ; r++) {
			for(int c=0 ; c<cellCount ; c++) {
				data[r-1][c] = obj.getCellValue(r, c);
			//System.out.println(data[r-1][c]);
			}
		}
		
		return data;
	}

}
