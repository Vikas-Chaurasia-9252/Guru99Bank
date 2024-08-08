package com.inetbanking.testCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

public class BaseTest {
	
	public static WebDriver driver;
	String userID ;
	String passWord;
	//String browser = "chrome";
	ReadConfig readconfig;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) throws IOException {
		readconfig = new ReadConfig();
		if(browser.equalsIgnoreCase("edge")) {
		System.setProperty("webdriver.edge.driver", readconfig.getEdgePath());
		driver = new EdgeDriver();
		}
		else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			driver = new ChromeDriver();	
		}
		else {
			System.out.println("This browser is not configured in code");
		}
		driver.manage().window().maximize();
		driver.get(readconfig.getURL());
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
		setUserandPassword();
		
	}
	
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	
	public void setUserandPassword() {
		userID = readconfig.getUserID();
		passWord = readconfig.getPassword();
		
	}

}
