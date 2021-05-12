package qautomation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestScript {

	public static WebDriver driver=null;
	public String browser = System.getProperty("browser");
	public String url = System.getProperty("URL");
	
 @BeforeTest
  public void beforeTest() {
	
	if(browser.equalsIgnoreCase("Chrome"))
	{
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
	Map<String, Object> prefs = new HashMap<String, Object>();
	ChromeOptions options = new ChromeOptions();
	options.setExperimentalOption("prefs", prefs);
	options.addArguments("--disable-arguments");
	options.addArguments("--test-type");
	options.addArguments("test");	
	options.addArguments("disable-infobars");
	driver = new ChromeDriver(options);
	}
	else if(browser.equalsIgnoreCase("FireFox"))
	{
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\geckodriver_v23.exe");
		FirefoxProfile profile = new FirefoxProfile();	    
		profile.setAcceptUntrustedCertificates(false);
	    driver = new FirefoxDriver(profile);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	}
	else if (browser.equalsIgnoreCase("IE"))
	{
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\IEDriverServer351.exe");
		DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
		caps.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR,"accept");
		caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,true);
		caps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,"http://www.google.com/");							
		driver = new InternetExplorerDriver(caps);	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	}
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.manage().window().maximize();
  }

  @Test
  public void TestApplication() {
	  
	driver.get(url);  
	String title = driver.getTitle();	
	System.out.println("Title="+title);
	AssertJUnit.assertTrue(title.contains("QAutomation")); 
	
  }
 
  @AfterTest
  public void afterTest() {
	  driver.quit();		
  }

}