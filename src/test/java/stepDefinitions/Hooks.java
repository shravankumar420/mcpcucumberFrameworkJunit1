package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utils.TestContextSetup;

public class Hooks {
	TestContextSetup testcontextsetup;
	
	public Hooks(TestContextSetup testcontextsetup) {
		this.testcontextsetup = testcontextsetup;
	}
	
	@After
	public void afterScenario() throws IOException {
		
		testcontextsetup.testBase.WebDriverManager().quit();
		
	}
	//if we want to take screenshot only for failed
//	@AfterStep
//	public void addScreenShot(Scenario scenario) throws IOException {
//		
//		WebDriver driver=testcontextsetup.testBase.WebDriverManager();
//		if(scenario.isFailed()) {
//			File sourcePath=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//			byte[] fileContent=FileUtils.readFileToByteArray(sourcePath);
//						scenario.attach(fileContent, "image/png", "image");
//		}
//	}
	//if we want to take screenshot for each step
	@AfterStep
	public void addScreenShot(Scenario scenario) throws IOException {
		
		WebDriver driver=testcontextsetup.testBase.WebDriverManager();
		
			File sourcePath=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			byte[] fileContent=FileUtils.readFileToByteArray(sourcePath);		
			scenario.attach(fileContent, "image/png", "testimage");
		
	}
	
	//Below method is used for deleting ould Report files
	@BeforeAll
public static void clearOldFiles() throws IOException {
		
		//File f=new File(System.getProperty("user.dir")+"/test-output/Reports/ExtentReport May-23/test-output/screenshots");
		File file=new File(System.getProperty("user.dir")+"/test-output/Reports");
		FileUtils.deleteDirectory(file);
		file.delete();		

	}		

}
