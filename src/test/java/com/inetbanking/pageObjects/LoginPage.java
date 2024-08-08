package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.inetbanking.utilities.Utilities;

public class LoginPage extends Utilities {
	
	WebDriver driver;
	public LoginPage(WebDriver rdriver) {
		super(rdriver);
		this.driver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(css="input[name='uid']")
	WebElement userIDField;
	
	@FindBy(name="password")
	WebElement pwdField;
	
	@FindBy(name="btnLogin")
	WebElement loginBtn;
	
	@FindBy(css=".heading3")
	WebElement welcomeManagerMessage;
	
	@FindBy(xpath="//a[@href='Logout.php']")
	WebElement logOutButton;
	
	
	
	public void login(String userID, String password) {
		userIDField.sendKeys(userID);
		pwdField.sendKeys(password);
		loginBtn.click();
		//waitForElementToAppear(welcomeManagerMessage);
		
	}
	public String getPageTitle() {
		return welcomeManagerMessage.getText();
	}
	public void logout() throws InterruptedException {
		logOutButton.click();
		waitForAlert();
		driver.switchTo().alert().accept();
		waitForElementToAppear(userIDField);
	}
	
	
	

}
