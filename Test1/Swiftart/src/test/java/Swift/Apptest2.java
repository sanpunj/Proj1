package Swift;
//import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
///Access control check
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Swift.java.Base;

public class Apptest2 extends Base {
	public static SoftAssert softassertion;

	/*
	 * @Test(priority = 1)
	 * 
	 * public void launchSwiftIE() { softassertion = new SoftAssert();
	 * System.setProperty("webdriver.ie.driver",
	 * Base.readPropertiesFile("iedriverpath")); driver = new
	 * InternetExplorerDriver(); driver.manage().window().maximize();
	 * driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 * driver.get(Base.readPropertiesFile("AppURL"));//
	 * ("http://10.117.67.157/ElectronicReferralTriageProcess/"); try {
	 * Thread.sleep(4000); } catch (InterruptedException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); }
	 * System.out.println(driver.getTitle().trim());
	 * softassertion.assertEquals(driver.getTitle().trim(),
	 * "Login - SWIFT - Referral Management System"); }
	 */

	@Test(priority = 1, description = "Launching the IE Browsers and performing the settings on Browser")
	public void launchSwift1() {
		softassertion = new SoftAssert();
		test = rep.startTest("AppTest");
		driver = fn_LaunchBrowser("IE");
		driver.get(Base.readPropertiesFile("AppURL"));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			softassertion.assertEquals(driver.getTitle().trim(), "Login - SWIFT - Referral Management System");
		}
	}

	@Test(priority = 2, dependsOnMethods = { "launchSwift1" })
	public void logIn() {
		WebElement user = driver.findElement(By.xpath(Base.readPropertiesFile("username_xpath")));
		user.sendKeys("punjs");
		WebElement pass = driver.findElement(By.xpath(Base.readPropertiesFile("password_xpath")));
		pass.sendKeys("Mandeep123!");
		WebElement loginButton = driver.findElement(By.xpath(Base.readPropertiesFile("LoginButton_xpath")));
		loginButton.click();
	}

	@Test(priority = 3, description = "Set the user access to pending", dependsOnMethods = { "logIn" })
	public void Setaccess() {

		// WebElement elem =
		// driver.findElement(By.xpath("//*[@id='navigation']/ul/li[2]/a"));
		// jsclick(elem);
		driver.findElement(By.xpath("//*[@id='navigation']/ul/li[2]/a")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id='searchCriteria']")).sendKeys("Punit");
		// WebElement elem1 = driver.findElement(By.xpath("//*[@id='searchUser']"));
		// jsclick(elem1);

		driver.findElement(By.xpath("//*[@id='searchUser']")).click();
		driver.findElement(By.xpath("//*[@id=\'userAccounts\']/div[2]/table/tbody/tr/td[2]/a")).click();
		WebElement MemberShipStatus = driver
				.findElement(By.xpath(("/html/body/div[1]/div[3]/fieldset/table/tbody/tr[2]/td[2]/span/span/input")));
		MemberShipStatus.click();
		MemberShipStatus.clear();
		MemberShipStatus.sendKeys("Pending");
		MemberShipStatus.sendKeys(Keys.RETURN);
		driver.findElement(By.xpath("//a[@id='updateMembership']")).click();
	}

	@Test(priority = 4, description = "Set the user access to Declined", dependsOnMethods = { "logIn" })
	public void Setaccess1() {

		driver.findElement(By.xpath("//*[@id='navigation']/ul/li[2]/a")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id='searchCriteria']")).sendKeys("Punit");
		driver.findElement(By.xpath("//*[@id='searchUser']")).click();
		driver.findElement(By.xpath("//*[@id=\'userAccounts\']/div[2]/table/tbody/tr/td[2]/a")).click();
		WebElement MemberShipStatus = driver
				.findElement(By.xpath(("/html/body/div[1]/div[3]/fieldset/table/tbody/tr[2]/td[2]/span/span/input")));
		MemberShipStatus.click();
		MemberShipStatus.clear();
		MemberShipStatus.sendKeys("Declined");
		MemberShipStatus.sendKeys(Keys.RETURN);
		driver.findElement(By.xpath("//a[@id='updateMembership']")).click();
	}

	@Test(priority = 4, description = "Set the user access to Deactivated", dependsOnMethods = { "logIn" })
	public void Setaccess2() {

		driver.findElement(By.xpath("//*[@id='navigation']/ul/li[2]/a")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id='searchCriteria']")).sendKeys("Punit");
		driver.findElement(By.xpath("//*[@id='searchUser']")).click();
		driver.findElement(By.xpath("//*[@id=\'userAccounts\']/div[2]/table/tbody/tr/td[2]/a")).click();
		WebElement MemberShipStatus = driver
				.findElement(By.xpath(("/html/body/div[1]/div[3]/fieldset/table/tbody/tr[2]/td[2]/span/span/input")));
		MemberShipStatus.click();
		MemberShipStatus.clear();
		MemberShipStatus.sendKeys("Deactivated");
		MemberShipStatus.sendKeys(Keys.RETURN);
		driver.findElement(By.xpath("//a[@id='updateMembership']")).click();
	}

	@Test(priority = 4, description = "Set the user access to Approved", dependsOnMethods = { "logIn" })
	public void Setaccess3() {

		driver.findElement(By.xpath("//*[@id='navigation']/ul/li[2]/a")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@id='searchCriteria']")).sendKeys("Punit");
		driver.findElement(By.xpath("//*[@id='searchUser']")).click();
		driver.findElement(By.xpath("//*[@id=\'userAccounts\']/div[2]/table/tbody/tr/td[2]/a")).click();
		WebElement MemberShipStatus = driver
				.findElement(By.xpath(("/html/body/div[1]/div[3]/fieldset/table/tbody/tr[2]/td[2]/span/span/input")));
		MemberShipStatus.click();
		MemberShipStatus.clear();
		MemberShipStatus.sendKeys("Approved");
		MemberShipStatus.sendKeys(Keys.RETURN);
		driver.findElement(By.xpath("//a[@id='updateMembership']")).click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void dClose() {
		driver.close();
	}

	@AfterMethod()
	public void extentClose() {
		rep.endTest(test);
		rep.flush();
	}

}
