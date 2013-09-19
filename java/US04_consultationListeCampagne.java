import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;


public class US04_consultationListeCampagne {

	WebDriver driver;
	Selenium selenium;
	String serverIp;
	String listCampagne;

	@Before
	public void setUp() {
		
		serverIp = "http://192.168.25.24:8080/";
		listCampagne = serverIp + "ovp-web/campaign/campaignList.html";
		
		driver = new FirefoxDriver();
		selenium = new WebDriverBackedSelenium(driver, serverIp);
	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Test
	public void testUS04_consultationListeCampagne() {
		testTestConnection();
		//selenium.open(listCampagne);
		assertTrue(selenium.isElementPresent("//div[@class='span8 centered well container-campaign-list']"));
		assertTrue(selenium.isTextPresent("Vos campagnes"));
		assertTrue(selenium.isTextPresent("Nom de la campagne"));
		//assertTrue(selenium.isTextPresent("Dernière mise à jour"));
		assertTrue(selenium.isTextPresent("Date de début/ fin de la campagne"));
		assertTrue(selenium.isTextPresent("Actions"));
		// VÃ©rifier que le picto "Statistiques" est prÃ©sent
		assertTrue(selenium.isElementPresent("//tbody[@id='campaignListTbody']/tr/td[4]/a[2]/img"));
		// VÃ©rifier que le picto "Fiche de campagne" est prÃ©sent
		assertTrue(selenium.isElementPresent("css=img"));
		selenium.click("//tbody[@id='campaignListTbody']/tr/td[4]/a[2]/img");
		selenium.open("/ovp-web/campaign/campaignList.html");
	}
	
	public void testTestConnection() {
		selenium.open(serverIp + "ovp-web/");
		selenium.typeKeys("name=USERNAME", "admin@degetel.com");
		selenium.typeKeys("name=PASSWORD", "admin");
		selenium.click("css=button.btn.btn-danger");
		selenium.waitForPageToLoad("30000");	
	}

}
