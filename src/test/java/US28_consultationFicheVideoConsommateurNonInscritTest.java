import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;


public class US28_consultationFicheVideoConsommateurNonInscritTest {

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
	public void testUS28_consultationFicheVideoConsommateurNonInscrit() {
		selenium.open("/ovp-web/videoDetails.html?campaignId=1&videoId=1");
		assertTrue(selenium.isElementPresent("id=listVideo"));
		assertTrue(selenium.isElementPresent("id=videoTitle"));
		assertTrue(selenium.isElementPresent("id=videoSynopsis"));
		assertEquals("", selenium.getText("css=span.pull-right > img"));
		assertTrue(selenium.isElementPresent("css=span.pull-right > img"));
		assertTrue(selenium.isElementPresent("css=b"));
		assertEquals("Télécharger", selenium.getText("id=download"));
		selenium.click("id=download");
		assertTrue(selenium.isElementPresent("id=signModal"));
		assertTrue(selenium.isTextPresent("Entrez votre code promo"));
		assertEquals("Code promo :", selenium.getText("css=label.control-label"));
		assertEquals("", selenium.getText("id=voucher"));
		assertTrue(selenium.isElementPresent("id=voucher"));
		assertTrue(selenium.isElementPresent("//button[@type='submit']"));
		assertTrue(selenium.isTextPresent("Ok"));
		selenium.click("css=button.close");
	}

}
