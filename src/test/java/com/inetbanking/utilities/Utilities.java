package com.inetbanking.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilities {
	static WebDriver driver;
	public Utilities(WebDriver rdriver) {
		this.driver = rdriver;
	}
	
	 
	
	public void waitForElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForAlert() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
		wait.until(ExpectedConditions.alertIsPresent());
	}

}
