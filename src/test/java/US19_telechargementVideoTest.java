import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public class US19_telechargementVideoTest {

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
	public void testUS19_telechargementVideo() {
		selenium.open("/ovp-web/videoDetails.html?campaignId=1&videoId=1");
		selenium.click("css=b");
		// Sasi du code promo
		selenium.type("id=voucher", "111111");
		String codePromo = selenium.getValue("id=voucher");
		System.out.println("'" + codePromo + "'");
		// Test du code promo à 6 caractères
		String longueurAttendu = "6";
		String longueurActuel = selenium.getEval("'" + codePromo + "'.length");
		System.out.println(longueurActuel);
		System.out.println(longueurAttendu);		
		// selenium.gotoIf("storedVars['longueurActuel']==storedVars['longueurAttendu']", "lengthResultOK");
		// selenium.gotoIf("storedVars['longueurActuel']!=storedVars['longueurAttendu']", "lengthResultKO");
		// selenium.label("lengthResultOK");
		System.out.println("\"La longueur du code promo est conforme\"");
		// selenium.gotolabel("continue");
		// selenium.label("lengthResultKO");
		System.out.println("\"La longueur du code promo n'est pas conforme\"");
		// selenium.label("continue");
		selenium.click("//button[@type='submit']");
		// Vérification que la modal "Créer un compte"  est affichée
		assertEquals("container-fluid modal hide well-black in", selenium.getAttribute("css=#signModal@class"));
		assertTrue(selenium.isTextPresent("Créer un compte"));
		assertTrue(selenium.isTextPresent("Inscrivez-vous et téléchargez gratuitement la vidéo de votre choix ! Grâce à votre mot de passe vous pourrez revenir à tout moment pour profiter de vos cadeaux."));
		String varEmail = "TestUS19@degetel.com";
		String i = "1";
		selenium.type("id=email", varEmail);
		// Contrôle des caractères de l'email
		assertTrue(selenium.getValue("id=email").matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"));
		selenium.click("xpath=(//button[@type='submit'])[2]");
		// Changement de l'email au cas ou l'email existe en base
		// selenium.label("changeEmail");
		boolean msgCompteExistant = selenium.isTextPresent("Vous avez déjà un compte, saisissez votre mot de passe pour télécharger votre vidéo");
		System.out.println(msgCompteExistant);
		// selenium.gotoIf("'${msgCompteExistant}' != 'true'", "newCount");
		// selenium.label("suite");
		String varEmailReel = selenium.getEval("'" + i + "'+'" + varEmail + "'");
		i = selenium.getEval(i + "+1;");
		selenium.type("id=email", "");
		selenium.type("id=email", varEmailReel);
		System.out.println(varEmailReel);
		selenium.click("xpath=(//button[@type='submit'])[2]");
		// selenium.goto("changeEmail");
		// selenium.label("newCount");
		selenium.type("id=signInPass", "test");
		selenium.type("id=signInPassConfirm", "test");
		selenium.click("id=contactEmail");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		System.out.println("selenium_blank63800");
		selenium.selectPopUp("");
		selenium.close();
		selenium.selectWindow("");
		selenium.click("id=logout");
		// Ouverture de la deuxième campagne et test de l'utilisateur déjà inscrit dans la première campagne
		selenium.open("http://192.168.25.24:8080/ovp-web/videoDetails.html?campaignId=2&videoId=7");
		selenium.selectWindow("null");
		selenium.click("css=b");
		selenium.type("id=voucher", "222222");
		selenium.click("//button[@type='submit']");
		selenium.type("id=email", varEmailReel);
		selenium.type("id=signInPass", "test19");
		selenium.type("id=signInPassConfirm", "test19");
		selenium.click("id=contactEmail");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		selenium.selectPopUp("");
		selenium.close();
		selenium.selectWindow("");
		selenium.click("id=logout");
	}

}
