package Swift;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Swift.java.Base;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class Sanity extends Base {

	public static SoftAssert softassertion;

	@Test(priority = 1, description = "Launching the IE Browsers and performing the settings on Browser")
	public void launchSwiftIE1() {
		test = rep.startTest("Sanity");
		softassertion = new SoftAssert();
		test.log(LogStatus.INFO, "Launching the Browser");
		driver = fn_LaunchBrowser("IE");
		driver.get(Base.readPropertiesFile("AppURL"));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// softassertion.assertEquals(driver.getTitle().trim(), "Login - SWIFT -
		// Referral Management Syste");
		String title = driver.getTitle().trim();
		System.out.println(title);
		softassertion.assertEquals(title, "Login - SWIFT - Referral Management Syste");
		// test.log(LogStatus.PASS, "Browser launched successfully,Marking Test case
		// Passed");

	}

	@Test(priority = 2, description = "Verify the elements on the Login Page", dependsOnMethods = { "launchSwiftIE1" })
	public void verifyElements() {

		test.log(LogStatus.INFO, "Verify the elements on the Login Page");

		test.log(LogStatus.INFO, "Verify the username field elements on the Login Page");
		WebElement username = driver.findElement(By.xpath(Base.readPropertiesFile("username_xpath")));
		Base.highLighterMethod(driver, username);
		try {
			Assert.assertEquals(Base.existsElement("username_xpath"), true);
			test.log(LogStatus.PASS, "The username field Exists in the log in Page");
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "The username field does not exits, hence stopping the execution");
		}

		test.log(LogStatus.INFO, "Verify the Password field elements on the Login Page");
		WebElement password = driver.findElement(By.xpath(Base.readPropertiesFile("password_xpath")));
		Base.highLighterMethod(driver, password);
		try {
			Assert.assertEquals(Base.existsElement("password_xpath"), true);
			test.log(LogStatus.PASS, "The password field Exists in the login Page");
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "The password field does not exits, hence stopping the execution");
		}

		test.log(LogStatus.INFO, "Verify the Login Button exits on the Login Page");
		WebElement LoginButton = driver.findElement(By.xpath(Base.readPropertiesFile("LoginButton_xpath")));

		Base.highLighterMethod(driver, LoginButton);
		try {
			Assert.assertEquals(Base.existsElement("LoginButton_xpath"), true);
			test.log(LogStatus.PASS, "The login Button exists on the Page");
		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "The Login Button does not exits, hence stopping the execution");
		}
		test.log(LogStatus.INFO, "Verify the Login Link exits on the Login Page");
		WebElement Loginlink = driver.findElement(By.xpath(Base.readPropertiesFile("Loginlink_xpath")));

		Base.highLighterMethod(driver, Loginlink);
		try {
			Assert.assertEquals(Base.existsElement("Loginlink_xpath"), true);
			// Assert.assertEquals(false, true);
			test.log(LogStatus.PASS, "The login Link exists on the Page");
		} catch (AssertionError e) {
			System.out.println("I am in Exception");
			test.log(LogStatus.FAIL, "The Login Link does not exits, hence stopping the execution");
		}

		getScreenShot();
		softassertion.assertAll();

	}

	@Test(priority = 3, description = "Login should be successful ", dependsOnMethods = { "launchSwiftIE1" })
	public void logIn() {
		driver.navigate().to(Base.readPropertiesFile("AppURL"));
		test.log(LogStatus.INFO, "Verify the Login Functionality");
		WebElement user = driver.findElement(By.xpath(Base.readPropertiesFile("username_xpath")));
		user.sendKeys(new CharSequence[] { "easya" });
		WebElement pass = driver.findElement(By.xpath(Base.readPropertiesFile("password_xpath")));
		pass.sendKeys(new CharSequence[] { "Gctest12@" });
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Actions action = new Actions(driver);
		action.sendKeys(new CharSequence[] { Keys.ESCAPE });
		WebElement loginButton = driver.findElement(By.xpath(Base.readPropertiesFile("LoginButton_xpath")));
		loginButton.click();
		getScreenShot();
	}

	@Test(priority = 4, description = "This is checking the Logout Button and logout Operations ", dependsOnMethods = {
			"logIn" }, enabled = false)
	public void logOUT() {
		boolean existLink = existsElement("logoutLink");
		softassertion.assertEquals(existLink, true);
		driver.findElement(By.xpath(Base.readPropertiesFile("logoutLink"))).click();
		getScreenShot();

		// throw new SkipException("Test case needs to be skipped");
	}

	@AfterMethod()
	public void Close() {
		rep.endTest(test);
		rep.flush();

	}

	@AfterClass()
	public void end() {
		// softassertion.assertAll();
	}

}
