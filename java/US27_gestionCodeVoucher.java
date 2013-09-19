import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public class US27_gestionCodeVoucher {

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
	public void testUS27_gestionCodeVoucher() {
		testTestConnection();
		selenium.open("/ovp-web/videoDetails.html?campaignId=1&videoId=1");
		selenium.click("css=b");
		// Test du champ Voucher obligatoire 
		selenium.type("id=voucher", "");
		selenium.click("//button[@type='submit']");
		String champsObligatoires = selenium.getText("css=label.error");
		System.out.println(champsObligatoires);
		// Test du code Voucher invalide
		selenium.type("id=voucher", "123654");
		selenium.click("//button[@type='submit']");
		String codePromoInvalide = selenium.getText("css=label.error");
		System.out.println(codePromoInvalide);
		// Changement de la date de dÃ©but et de fin Ã  des dates expirÃ©es
		selenium.open("/ovp-web/campaign/campaignDisplay.html?campaignId=1");
		selenium.click("id=modifyBtn");
		selenium.type("id=startDate", "28-06-2013");
		selenium.type("id=endDate", "28-06-2013");
		selenium.click("id=saveChangesBtn");
		selenium.waitForPageToLoad("30000");
		// Test de la campagne expirée
		selenium.open("/ovp-web/videoDetails.html?campaignId=1&videoId=1");
		selenium.click("css=b");
		selenium.type("id=voucher", "111111");
		selenium.click("//button[@type='submit']");
		String campagneExpiree = selenium.getText("css=label.error");
		System.out.println(campagneExpiree);
		// Mise à  jour de la date de fin de la campagne à  la date du jour
		selenium.open("/ovp-web/campaign/campaignDisplay.html?campaignId=1");
		selenium.click("id=modifyBtn");
		selenium.click("css=#dateTimePickerEndingDate > span.add-on");
		selenium.clickAt("css=.day.new:first", "0,0");
		selenium.click("id=saveChangesBtn");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=startDate", "28-06-2013");
		//selenium.click("id=saveChangesBtn");
		selenium.waitForPageToLoad("30000");
		// Test de la campagne avec un voucher valide
		selenium.open("/ovp-web/videoDetails.html?campaignId=1&videoId=1");
		selenium.click("css=b");
		selenium.type("id=voucher", "111111");
		selenium.click("//button[@type='submit']");
		assertEquals("container-fluid modal hide well-black in", selenium.getAttribute("css=#signModal@class"));
		assertTrue(selenium.isTextPresent("Créer un compte"));
		assertTrue(selenium.isTextPresent("Inscrivez-vous et téléchargez gratuitement la vidéo de votre choix ! Grâce à votre mot de passe vous pourrez revenir à tout moment pour profiter de vos cadeaux."));
		assertTrue(selenium.isTextPresent("Code promo :"));
		assertTrue(selenium.isTextPresent("Email :"));
		assertTrue(selenium.isTextPresent("Mot de passe :"));
		assertTrue(selenium.isTextPresent("Confirmation du mot de passe :"));
		assertEquals("", selenium.getText("id=contactEmail"));
		assertEquals("J'accepte d'être contacté par email", selenium.getText("//form[@id='signInForm']/div[6]/label"));
		assertTrue(selenium.isTextPresent("Ok"));
		assertTrue(selenium.isElementPresent("xpath=(//button[@type='submit'])[2]"));
		assertEquals("Déjà inscrit", selenium.getText("id=alreadySignedIn"));
		              
		              
	}
	
	public void testTestConnection() {
		selenium.open(serverIp + "ovp-web/");
		selenium.typeKeys("name=USERNAME", "admin@degetel.com");
		selenium.typeKeys("name=PASSWORD", "admin");
		selenium.click("css=button.btn.btn-danger");
		selenium.waitForPageToLoad("30000");	
	}

}
