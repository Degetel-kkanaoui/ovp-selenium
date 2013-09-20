import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public class US29_consultationCatalogueVideoCampagneTest {

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
	public void testUS29_consultationCatalogueVideoCampagne() {
		selenium.open("/ovp-web/catalogue.html");
		// Vérification de l'affichage du carroussel
		assertTrue(selenium.isElementPresent("xpath=(//div[@class='span6 centered carrousel-wrapper'])"));
		// Test du titre affiché sous l'image
		//selenium.click("xpath=(//li[@id='videoBlocDefault']/a)[5]");
		assertTrue(selenium.isElementPresent("xpath=(//li[@id='videoBlocDefault']/div/div/h1)[5]"));
		// Ouverture de la fiche détaillé de la vidéo
		selenium.click("xpath=(//li[@id='videoBlocDefault']/a)[5]");
		selenium.waitForPageToLoad("30000");
		// Contrôle de l'intégrité de la fiche détaillé de la vidéo
		assertTrue(selenium.isElementPresent("//div[@class='previewContainer']/img[@class='preview']"));
		assertTrue(selenium.isElementPresent("css=img.preview"));
		assertTrue(selenium.isElementPresent("css=span.pull-right > img"));
		assertTrue(selenium.isElementPresent("css=div.row > div.clearfix"));
	}

}
