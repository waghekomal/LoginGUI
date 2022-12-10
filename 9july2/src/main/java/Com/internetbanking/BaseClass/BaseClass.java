package Com.internetbanking.BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import Com.internetbanking.Utilities.TestUtil;
import Com.internetbanking.configuration.ReadConfigiration;
import net.bytebuddy.utility.RandomString;

public class BaseClass {

	ReadConfigiration readconfig = new ReadConfigiration();

	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUsername();
	public String password = readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;

	@Parameters("Browser")
	@BeforeMethod
	public void initialization(String br) {
		 
		logger = logger.getLogger("9JulyBanking");
		PropertyConfigurator.configure("Log4j.properties");

		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());

			driver = new ChromeDriver();
		} else {

			System.setProperty("webdriver.gecko.driver", readconfig.getForeFoxPath());

			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(baseURL);

	}

	public String getScreenshotAs(String testCaseName) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		String Random = RandomString.make(5);

		String destination = System.getProperty("user.dir") + "/Screenshots/" + testCaseName + "" + Random + ".png";

		FileUtils.copyFile(source, new File(destination));

		return destination;

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
