package com.Swift.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import util.ExtentManager;

public class Base {
	public static WebDriver driver = null;
	public static String browserName = null;
	private static Logger Log = Logger.getLogger(Logger.class.getName());
	public ExtentReports rep = ExtentManager.getInstance();
	public ExtentTest test;

	// public static Actions action = new Actions();
	public static WebDriver openIEBrowser() throws Exception {
		try {

			System.setProperty("webdriver.ie.driver", "C:\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			Log.info("New Driver instantiated");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("http://10.117.67.157/ElectronicReferralTriageProcess/");
		} catch (Exception e) {
			((org.apache.commons.logging.Log) Log).error("Base Class : OpenIEBrowser  : Exception" + e.getMessage());
		}
		return driver;
	}

	// Creating a custom highlighter function
	public static void highLighterMethod(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}

	// Creating a basic iselement exist function with xpath as input

	public static boolean existsElement(String xpathexp) {
		Boolean Status = null;
		try {
			System.out.println(xpathexp);
			driver.findElement(By.xpath(readPropertiesFile(xpathexp)));
		} catch (NoSuchElementException e) {
			System.out.println("The element is not found");
			Status = false;
			return Status;
		}
		System.out.println("I am Returning true");
		Status = true;
		return Status;
	}

	public static boolean fnexistsElement(String id) {
		Boolean Status = null;
		try {
			System.out.println(id);
			driver.findElement(By.id(readPropertiesFile(id)));
		} catch (NoSuchElementException e) {
			System.out.println("I am in Exception");
			Status = false;
			// return false;
		}
		System.out.println("I am Returning true");
		Status = true;
		return Status;
	}

	// reading value from properties file for Key value pairs

	public static String readPropertiesFile(String PropKey) {
		String returnValue = null;
		Properties prop = new Properties();
		try {
			InputStream input = new FileInputStream(
					"C:\\Users\\win7\\eclipse-workspace\\Swiftart\\src\\test\\resources\\Config.properties");
			prop.load(input);
			// System.out.println("I am in readProperty");
			returnValue = prop.getProperty(PropKey);
			// System.out.println(PropKey);
			// System.out.println(returnValue);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return returnValue;

	}

	public static WebDriver fn_LaunchBrowser(String browsername) {
		if (browsername == "CH") {
			System.setProperty("webdriver.chrome.driver", readPropertiesFile("chromedriver_exe"));
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("chrome.switches", "--disable-extensions");
			opt.addArguments("user-data-dir=C:\\Users\\win7\\AppData\\Local\\Chrome\\User Data\\Default\\Profile 1");
			opt.addArguments("disable-infobars");
			opt.addArguments("test-type");
			opt.addArguments("start-maximized");
			// opt.addArguments("--js-flags=--expose-gc");
			opt.addArguments("--enable-precise-memory-info");
			opt.addArguments("--disable-popup-blocking");
			opt.addArguments("--disable-default-apps");
			// opt.addArguments("--proxy-server =proxy1.health.qld.gov.au:80");

			Base.driver = new ChromeDriver(opt);
		} else if (browsername == "FF") {
			System.setProperty("webdriver.gecko.driver", readPropertiesFile("firefox_exe"));
			ProfilesIni profile = new ProfilesIni();
			FirefoxProfile testprofile = profile.getProfile("default");
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(testprofile);
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability("marionatte", false);
			options.merge(dc);
			driver = new FirefoxDriver(options);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (browsername == "IE") {
			// System.setProperty(InternetExplorerDriverService.IE_DRIVER_LOGLEVEL_PROPERTY,
			// "NULL");

			InternetExplorerOptions options = new InternetExplorerOptions();
			DesiredCapabilities cap = new DesiredCapabilities();
			String PROXY = "proxy1.health.qld.gov.au:80";
			Proxy proxy = new Proxy();
			proxy.setAutodetect(false);
			cap.setCapability(CapabilityType.PROXY, PROXY);
			options.merge(cap);
			System.setProperty("webdriver.ie.driver", readPropertiesFile("iedriver_exe"));
			Base.driver = new InternetExplorerDriver();
		}
		Base.driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		Base.driver.manage().window().maximize();
		return Base.driver;
	}

	public static void jsclick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void getScreenShot() {
		Date d = new Date();
		String SSfileName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile,
					new File(System.getProperty("user.dir") + "//screenshots//" + "-" + SSfileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.log(LogStatus.INFO, "ScreenShot->",
				test.addScreenCapture(System.getProperty("user.dir") + "//screenshots//" + "-" + SSfileName));
	}
}
