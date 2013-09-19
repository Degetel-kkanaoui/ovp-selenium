import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public class LoginMultiCampaign {

	WebDriver driver;
	Selenium selenium;
	String serverIp;
	String ficheVideoAdress;
	@Before
	public void setUp() {
		serverIp = "http://192.168.25.24:8080/";
		ficheVideoAdress = serverIp + "ovp-web/videoDetails.html?";
		
		driver = new FirefoxDriver();
		selenium = new WebDriverBackedSelenium(driver,serverIp);
		
	}

	
    @After	
	public void tearDown() {
		driver.close();
	}

	@Test
	public void testLoginMultiCampaign() {
		selenium.open(ficheVideoAdress + "campaignId=1&videoId=4");
		selenium.click("css=b");
		selenium.type("id=voucher", "111111");
		selenium.click("//button[@type='submit']");
		selenium.type("id=email", "test@degetel.com");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		// Test du mot de passe correct
		selenium.type("id=signInPass", "test");
		selenium.type("id=signInPassConfirm", "test");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		System.out.println("selenium_blank63800");
		selenium.selectPopUp("");
		selenium.close();
		selenium.selectWindow("");
		// Click ssur le bouton "Se déconnecter"
		selenium.click("id=logout");
		selenium.open(ficheVideoAdress + "campaignId=2&videoId=7");
		selenium.click("css=b");
		selenium.type("id=voucher", "222222");
		selenium.click("//button[@type='submit']");
		selenium.type("id=email", "test@degetel.com");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		// Test du mot de passe correct
		selenium.type("id=loginPass", "12");
//		selenium.type("id=signInPassConfirm", "12");
		selenium.click("xpath=(//button[@type='submit'])[2]");
		System.out.println("selenium_blank63800");
		selenium.selectPopUp("");
		selenium.close();
		selenium.selectWindow("");
		// Click ssur le bouton "Se déconnecter"
		selenium.click("id=logout");
	}
	
	
	

}
