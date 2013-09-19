import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public class testConnection {

	WebDriver driver;
	Selenium selenium;

	String serverIp;
	
	@Before
	public void setUp() {
		serverIp = "http://192.168.25.24:8080/";
		
		driver = new FirefoxDriver();
		selenium = new WebDriverBackedSelenium(driver, serverIp);
	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Test
	public void testTestConnection() {
		selenium.open(serverIp + "ovp-web/");
		selenium.typeKeys("name=USERNAME", "admin@degetel.com");
		selenium.typeKeys("name=PASSWORD", "admin");
		selenium.click("css=button.btn.btn-danger");
		selenium.waitForPageToLoad("30000");	
	}

}
