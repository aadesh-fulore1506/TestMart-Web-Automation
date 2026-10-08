
package base;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import utils.ConfigReader;


public class BaseTest {

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return driver.get();
	}

//	@BeforeSuite(alwaysRun = true)
//	public void startServer() throws Exception {
//		StartServer server = new StartServer();
//		server.startServer();
//	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() {

	    String browser = ConfigReader.get("browser");
	    boolean headless = ConfigReader.getBoolean("headless");

	    WebDriver localDriver;

	    switch (browser.toLowerCase()) {

	        case "firefox":

	            FirefoxOptions ffOptions = new FirefoxOptions();

	            if (headless) {
	                ffOptions.addArguments("-headless");
	            }

	            localDriver = new FirefoxDriver(ffOptions);
	            break;

	        case "chrome":
	        default:

	            ChromeOptions chromeOptions = new ChromeOptions();

	            Map<String, Object> prefs = new HashMap<>();

	            prefs.put("profile.password_manager_leak_detection", false);
	            prefs.put("credentials_enable_service", false);
	            prefs.put("profile.password_manager_enabled", false);

	            chromeOptions.setExperimentalOption("prefs", prefs);

	            chromeOptions.addArguments("--remote-allow-origins=*");
	            chromeOptions.addArguments("--window-size=1920,1080");

	            if (headless) {
	                chromeOptions.addArguments("--headless=new");
	            }

	            localDriver = new ChromeDriver(chromeOptions);
	            break;
	    }

	    if (!headless) {
	        localDriver.manage().window().maximize();
	    }

	    localDriver.manage().timeouts()
	            .implicitlyWait(Duration.ofSeconds(
	                    ConfigReader.getInt("implicit.wait.seconds")));

	    localDriver.manage().timeouts()
	            .pageLoadTimeout(Duration.ofSeconds(
	                    ConfigReader.getInt("page.load.timeout.seconds")));

	    driver.set(localDriver);

	    getDriver().get(ConfigReader.get("base.url"));
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
			driver.remove();
		}
	}
}
