package Swift;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Swift.java.Base;
import com.relevantcodes.extentreports.LogStatus;

public class AppTest extends Base {
	public static SoftAssert softassertion;

	@Test(priority = 1, description = "Launching the IE Browsers and performing the settings on Browser")
	public void launchSwiftIE1() {
		test = rep.startTest("AppTest");
		softassertion = new SoftAssert();
		test.log(LogStatus.INFO, "Launching the Browser");
		driver = fn_LaunchBrowser("IE");
		driver.get(Base.readPropertiesFile("AppURL"));
		try {
			Thread.sleep(4000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
			softassertion.assertEquals(driver.getTitle().trim(), "Login - SWIFT - Referral Management System");
			test.log(LogStatus.PASS, "Browser launch successfull");
		}
	}

	@Test(priority = 2, description = "Verify the elements on the Login Page", dependsOnMethods = { "launchSwiftIE1" })
	public void verifyElements() {
		WebElement username = driver.findElement(By.xpath(Base.readPropertiesFile("username_xpath")));
		Base.highLighterMethod(driver, username);
		test.log(LogStatus.INFO, "Verify the elements on the Login Page");

		softassertion.assertEquals(Base.existsElement("username_xpath"), true);

		WebElement password = driver.findElement(By.xpath(Base.readPropertiesFile("password_xpath")));
		Base.highLighterMethod(driver, password);

		softassertion.assertEquals(Base.existsElement("password_xpath"), true);
		test.log(LogStatus.PASS, "password field is available");
		WebElement LoginButton = driver.findElement(By.xpath(Base.readPropertiesFile("LoginButton_xpath")));
		getScreenShot();
		Base.highLighterMethod(driver, LoginButton);
		softassertion.assertEquals(Base.existsElement("LoginButton_xpath"), true);
		WebElement Loginlink = driver.findElement(By.xpath(Base.readPropertiesFile("Loginlink_xpath")));
		Base.highLighterMethod(driver, Loginlink);
		softassertion.assertEquals(Base.existsElement("Loginlink_xpath"), true);

	}

	@Test(priority = 3, description = "Launch the application and test with invalid user and it should fail", dependsOnMethods = {
			"launchSwiftIE1" })
	public void logInInvalid() {
		WebDriverWait wait = new WebDriverWait(driver, 20L);

		WebElement user = driver.findElement(By.xpath(Base.readPropertiesFile("username_xpath")));
		user.sendKeys(new CharSequence[] { "Hallo" });
		WebElement pass = driver.findElement(By.xpath(Base.readPropertiesFile("password_xpath")));
		pass.sendKeys(new CharSequence[] { Base.readPropertiesFile("Hallo6756") });
		WebElement loginButton = driver.findElement(By.xpath(Base.readPropertiesFile("LoginButton_xpath")));
		loginButton.click();
		try {
			Thread.sleep(4000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[2]/div/form/div")));
		String AlertMessage = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/form/div")).getText();
		System.out.println(AlertMessage);
		softassertion.assertEquals(AlertMessage, Base.readPropertiesFile("Invalid_usr_msg"));
		getScreenShot();
		driver.navigate().back();
	}

	@Test(priority = 4, description = "Login should be successful ", dependsOnMethods = { "launchSwiftIE1" })
	public void logIn() {
		driver.navigate().to(Base.readPropertiesFile("AppURL"));
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

	@Test(priority = 6, description = "Check all the menu tabs and click to check Navigation in menu works", dependsOnMethods = {
			"logIn" })
	public void checktabs() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 20L);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='navigation']/ul/li[1]/a")));

		WebElement element = driver.findElement(By.id("navigation"));
		List<WebElement> elements = element.findElements(By.tagName("a"));

		int numMenuTabs = elements.size();
		Assert.assertEquals(numMenuTabs, Integer.parseInt(Base.readPropertiesFile("expected_menutabs")));
		softassertion.assertAll();
		for (int j = 0; j <= elements.size() - 1; j++) {
			element = driver.findElement(By.id("navigation"));
			elements = element.findElements(By.tagName("a"));
			System.out.println(((WebElement) elements.get(j)).getText());

			((WebElement) elements.get(j)).click();
			getScreenShot();

			Thread.sleep(2000L);
		}
	}

	@Test(priority = 9, description = "This is checking the Logout Button and logout Operations ", dependsOnMethods = {
			"Footerlink", "logIn" })
	public void logOUT() {
		boolean existLink = existsElement("logoutLink");
		softassertion.assertEquals(existLink, true);
		driver.findElement(By.xpath(Base.readPropertiesFile("logoutLink"))).click();
		softassertion.assertAll();
	}

	@Test(priority = 7, description = "This is a dummy test case to verify that Skipping option of the Framework works Properly")
	public void Dummy1() {
		throw new SkipException("Skipping Demo");
	}

	@Test(priority = 8, description = "The is a demo test case to record failure")
	public void Dummy2() {
		Assert.fail("Demo Failure message");
	}

	@Test(priority = 5, description = "Links at the footer exits", dependsOnMethods = { "checktabs", "logIn" })
	public void Footerlink() {
		boolean Ispresent = existsElement("footerHome");
		if (Ispresent) {
			boolean Ispresent1 = existsElement("helpLink");
			if (Ispresent1) {
				System.out.println("Help link exists");
				getScreenShot();
			} else {
				softassertion.fail("Help Link is missing in footer");
				getScreenShot();
			}
		} else {
			softassertion.fail("Home link is missing in footer");
		}
		softassertion.assertAll();
	}

	@AfterMethod()
	public void extentClose() {
		rep.endTest(test);
		rep.flush();
	}

	@AfterClass
	public void dClose() {
		// driver.close();
	}

}