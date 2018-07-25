package com.blind.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class);

	protected WebDriver driver;
	protected Properties prop;
	public FileInputStream fis;

	@BeforeMethod
	public void browserInitialization() throws IOException {

		try {
			prop = new Properties();
			String dir = System.getProperty("user.dir");
			fis = new FileInputStream(dir + "/src/main/java/com/blind/config/config.properties");
//			fis = new FileInputStream(
//					"/Users/mohammadsikder/eclipse-workspace/BlindsApplicationProject/src/main/java/com/blind/config/config.properties");
			prop.load(fis);
			log.info("========file uploaded!!!!!!===============");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String browsername = prop.getProperty("browser");
		if (browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
			// System.setProperty("webdriver.chrome.driver",
			// "/Users/mohammadsikder/Downloads/chromedriver");
			driver = new ChromeDriver();
			log.info("===== " + browsername + " is opened!!!");
		} else if (browsername.equalsIgnoreCase("FF")) {
			System.setProperty("webdriver.gecko.driver", "/Users/mohammadsikder/Downloads/geckodriver");
			driver = new FirefoxDriver();
			log.info(browsername + " is opened!!!");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(1000);
		driver.quit();
	}

}
