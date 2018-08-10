package Swift;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Learn {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\\\Drivers\\\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("http://10.117.67.157/ElectronicReferralTriageProcess/");

		driver.findElement(By.id("UserName")).sendKeys("easya");

		driver.findElement(By.id("Password")).sendKeys("Gctest12@");

		driver.findElement(By.id("btnSignIn")).click();

		Thread.sleep(2000); // used thread for hold process

		driver.quit(); // for close browser
	}
}
