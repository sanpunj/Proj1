package Swift;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Swift.java.Base;

public class Apptest3 extends Base {

	public SoftAssert softassertion;

	@Test(priority = 1, description = "Launching the  Browsers and performing the settings on Browser")
	public void launchSwiftIE1() {
		// softassertion = new SoftAssert();
		test = rep.startTest("AppTest");
		driver = fn_LaunchBrowser("FF");
		// driver.get("https://accounts.google.com");
		driver.get("https://mail.rediff.com/cgi-bin/login.cgi");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Hallo");
			getScreenShot();
		}
	}

	@Test(priority = 2, description = "Login")
	public void login() {
		// launchSwiftIE1();

		/*
		 * try { Thread.sleep(10000); } catch (InterruptedException e) {
		 * e.printStackTrace();
		 * 
		 * 
		 * }
		 */
		WebElement user1 = driver.findElement(By.xpath("//*[@id=\'login1\']"));
		user1.click();
		user1.sendKeys("testselenium2019");
		// user1.sendKeys(Keys.ENTER);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement pass1 = driver.findElement(By.xpath("//*[@id=\'password\']"));
		pass1.click();
		pass1.sendKeys("test2019");
		WebElement nextbutton = driver
				.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div/form/div/div[6]/div[1]/input"));
		nextbutton.click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getScreenShot();
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[2]/a[2]")).click();
		getScreenShot();
		// driver.quit();

	}

	@Test(priority = 3, description = "Login")
	public void login1() {
		launchSwiftIE1();
		WebElement user1 = driver.findElement(By.xpath("//*[@id=\'login1\']"));
		user1.click();
		user1.sendKeys("testselenium2019");
		// user1.sendKeys(Keys.ENTER);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement pass1 = driver.findElement(By.xpath("//*[@id=\'password\']"));
		pass1.click();
		pass1.sendKeys("test2019");
		WebElement nextbutton = driver
				.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div/form/div/div[6]/div[1]/input"));
		nextbutton.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[2]/a[2]")).click();
		// driver.quit();
		getScreenShot();
	}

	@Test(priority = 4, description = "Login")
	public void login2() {
		launchSwiftIE1();
		WebElement user1 = driver.findElement(By.xpath("//*[@id=\'login1\']"));
		user1.click();
		user1.sendKeys("testselenium2019");
		// user1.sendKeys(Keys.ENTER);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement pass1 = driver.findElement(By.xpath("//*[@id=\'password\']"));
		pass1.click();
		pass1.sendKeys("test2019");
		getScreenShot();
		WebElement nextbutton = driver
				.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/div/form/div/div[6]/div[1]/input"));
		nextbutton.click();
		getScreenShot();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[2]/a[2]")).click();
		getScreenShot();
	}

	@BeforeMethod
	public void StartnewSoftAssert() {
		// init();
		softassertion = new SoftAssert();
		// launchSwiftIE1();
	}

	@AfterMethod
	public void CollectSoftassert() {
		softassertion.assertAll();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	@AfterMethod()
	public void extentClose() {
		rep.endTest(test);
		rep.flush();
	}

	@AfterClass
	public void quitall() {
		driver.quit();

	}

}