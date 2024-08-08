package com.inetbanking.utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inetbanking.testCases.BaseTest;

public class Reporting implements ITestListener{
	ExtentSparkReporter sparkRepoter;
	ExtentReports extent;
	ExtentTest test;
	WebDriver driver;
	
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss");  
		LocalDateTime now = LocalDateTime.now();  
		String repName = "Test-Report"+dtf.format(now)+".html";
		sparkRepoter = new ExtentSparkReporter(System.getProperty("user.dir")+"/Report/"+repName);
		extent = new ExtentReports();
		extent.attachReporter(sparkRepoter);
		sparkRepoter.config().setDocumentTitle("Automation Report");
		sparkRepoter.config().setReportName("Functional Testing");
		sparkRepoter.config().setTheme(Theme.DARK);
		
		
		extent.setSystemInfo("Tester Name", "Vikas Chourasia");
		
		//test = extent.createTest(result.getName());
		
		
	}

	

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getTestClass().getRealClass().getSimpleName());
		test.log(Status.PASS, "Test Case Passed is"+ result.getTestClass().getRealClass().getSimpleName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		String screenshotName = "screen_"+result.getTestClass().getRealClass().getSimpleName()+".png";
		test = extent.createTest(result.getTestClass().getRealClass().getSimpleName());
		//test.log(Status.FAIL, "Test Case Failed is"+ result.getTestClass().getRealClass().getSimpleName());
		test.log(Status.FAIL, "Test Case Failed is"+ result.getTestClass().getRealClass().getSimpleName());
		 this.driver = ((BaseTest)result.getInstance()).driver;
		 TakesScreenshot ts = (TakesScreenshot)driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		 File target = new File(System.getProperty("user.dir")+"/Screenshot/"+screenshotName);
		 try {
			FileUtils.copyFile(source, target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 test.addScreenCaptureFromPath(System.getProperty("user.dir")+"/Screenshot/"+screenshotName);
	  }
		
	

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}
	

}
