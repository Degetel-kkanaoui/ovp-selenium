import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public class US18_telechargementVideoDejaTelechargeTest {

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
	public void testUS18_telechargementVideoDejaTelecharge() {
		selenium.open("http://192.168.25.24:8080/ovp-web/videoDetails.html?campaignId=1&videoId=4");
		selenium.click("css=b");
		selenium.type("id=voucher", "111111");
		selenium.click("//button[@type='submit']");
		selenium.type("id=email", "test@degetel.com");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		// Test d'un mot de passe erroné 
		selenium.type("id=loginPass", "toto1");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		// Affichage du message d'erreur "Mot de passe ou login invalide"
		assertEquals("Mot de passe ou login invalide", selenium.getText("css=strong"));
		// Test du mot de passe correct
		selenium.type("id=loginPass", "test");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		System.out.println("selenium_blank63800"); //mettre un temps d'attente
		selenium.waitForPageToLoad("90000");
		selenium.selectPopUp("");
		selenium.close();
		selenium.selectWindow("");
		// Vérification du bouton "Se déconnecter"
		assertTrue(selenium.isElementPresent("id=logout"));
		assertEquals("Se déconnecter", selenium.getText("id=logout"));
		// Click sur le bouton "Se déconnecter"
		//selenium.click("id=logout");
		selenium.waitForPageToLoad("30000");
		selenium.click("id=logout");
		selenium.click("css=a");
		selenium.waitForPageToLoad("30000");
		selenium.click("css=b");
		selenium.type("id=voucher", "111111");
		selenium.click("//button[@type='submit']");
		selenium.type("id=email", "test@degetel.com");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		// Vérifier le message qui donne droit de télécharger que la vidéo correspondant au voucher déjà consommé
		assertEquals("Désolé", selenium.getText("css=h2"));
		assertEquals("Votre code promo ne vous donne droit qu'à un seul téléchargement. Vous avez déjà téléchargé la vidéo : The GrandMasters", selenium.getText("css=p.subtitle"));
		// Ouverture de la fiche vidéo correspondant au voucher déjà consommé
		selenium.click("link=The GrandMasters");
		selenium.waitForPageToLoad("90000");
		testTestConnection();
		// Vérifier la date de validité de la campagne
		selenium.open("http://192.168.25.24:8080/ovp-web/campaign/campaignDisplay.html?campaignId=1");
		selenium.click("id=modifyBtn");
		// Choisir une date de fin de la campagne inférieur à la date d'aujourd'hui
		selenium.type("id=startDate", "19-08-2013");
		selenium.type("id=endDate", "19-08-2013");		
		selenium.click("id=saveChangesBtn");
		selenium.waitForPageToLoad("30000");
		// Téléchargement de la vidéo avec une date de validité de la campagne expirée
		selenium.open("http://192.168.25.24:8080/ovp-web/videoDetails.html?campaignId=1&videoId=4");
//		selenium.click("id=logout");
		selenium.click("css=b");
		selenium.type("id=voucher", "111111");
		selenium.click("//button[@type='submit']");
		// Vérification du message que la campagne est expirée
		assertEquals("Cette campagne est expirée", selenium.getText("css=label.error"));		
		// Re-validation de la date de fin de la campagne à une date non expirée
		selenium.open("http://192.168.25.24:8080/ovp-web/campaign/campaignDisplay.html?campaignId=1");
		selenium.click("id=modifyBtn");
		selenium.click("css=#dateTimePickerEndingDate > span.add-on");
		selenium.click("css=.day:not(.disabled):first");
		selenium.type("id=startDate", "19-08-2013");
		selenium.click("id=saveChangesBtn");
		selenium.waitForPageToLoad("30000");
		// Ouverture de la deuxième campagne
		selenium.open("http://192.168.25.24:8080/ovp-web/videoDetails.html?campaignId=2&videoId=7");
		selenium.click("css=b");
		selenium.type("id=voucher", "222222");
		selenium.click("//button[@type='submit']");
		selenium.type("id=email", "test@degetel.com");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		// Test du mot de passe correct
		selenium.type("id=loginPass", "12");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		System.out.println("selenium_blank63800");
		selenium.selectPopUp("");
		selenium.close();
		selenium.selectWindow("");
		// Click ssur le bouton "Se déconnecter"
		selenium.click("id=logout");
	}
	
	public void testTestConnection() {
		selenium.open(serverIp + "ovp-web/");
		selenium.typeKeys("name=USERNAME", "admin@degetel.com");
		selenium.typeKeys("name=PASSWORD", "admin");
		selenium.click("css=button.btn.btn-danger");
		selenium.waitForPageToLoad("30000");	
	}

}