import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public class US13_generationAutomatiseeCodePromo {

	WebDriver driver;
	Selenium selenium;
	
	String serverIp;

	@Before
	public void setUp() {
		
		serverIp = "http://192.168.25.24:8080/";
		
		driver = new FirefoxDriver();
		selenium = new WebDriverBackedSelenium(driver, "http://192.168.25.24:8080/");
	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Test
	public void testUS13_generationAutomatiseeCodePromo() {
		testTestConnection();
		selenium.open("/ovp-web/campaign/campaignDisplay.html?campaignId=1");
		String codePromo = selenium.getValue("id=codeVoucher");
		System.out.println("${codeVoucher}");
		// Test du code promo à 6 caractères
		String longueurAttendu = "6";
		String longueurActuel = selenium.getEval("'" + codePromo + "'.length");
		System.out.println(longueurActuel);
		System.out.println(longueurAttendu);
		if(longueurActuel==longueurAttendu){
			System.out.println("\"La longueur du code promo est conforme\"");
		}else{
			System.out.println("\"La longueur du code promo n'est pas conforme\"");
		}

		assertTrue(selenium.isElementPresent("//div[@class='span8 centered well']"));
	}
	
	public void testTestConnection() {
		selenium.open(serverIp + "ovp-web/");
		selenium.typeKeys("name=USERNAME", "admin@degetel.com");
		selenium.typeKeys("name=PASSWORD", "admin");
		selenium.click("css=button.btn.btn-danger");
		selenium.waitForPageToLoad("30000");	
	}

}
