import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public class US36_consultationFicheVideo {

	WebDriver driver;
	Selenium selenium;
	String serverIp;
	String catalogueVideo;
	
	String nombreVideoCarroussel;


	@Before
	public void setUp() {
		
		serverIp = "http://192.168.25.24:8080/";
		catalogueVideo = serverIp + "ovp-web/catalogue.html";
		
		driver = new FirefoxDriver();
		selenium = new WebDriverBackedSelenium(driver, serverIp);
	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Test
	public void testUS36_consultationFicheVideo() {
		 
		//intLgTitreVideo = Integer.parseInt(lgTitreVideo);
		
		String titreVideoC = null;
		String lgTitreVideo;
		int intLgTitreVideo;
		int intCptVideo;
		String cptVideo;
		String varVideoTitle;
		String varVideoTitleComplete;
		String pointSuspension;
		String recupereTitreV;
		String lgNomDansListe;
		
		selenium.open("/ovp-web/catalogue.html");
		// Le clic sur une vidéo emméne sur sa fiche détaillé
		String titreVideoCarroussel = selenium.getText("css=h1");
		System.out.println(titreVideoCarroussel);
		// Récupérer le nombre de vidéo dans le carroussel
		String k = "2";   //Commencer par la vidéo 2 pour tester le carroussel
		int intk = Integer.parseInt(k);
		
		Number nombreVideoCarroussel = selenium.getXpathCount("//li[@class='roundabout-moveable-item']");
		Number nombreVideoCarroussel1 = selenium.getXpathCount("//li[@class='roundabout-moveable-item roundabout-in-focus']");
		nombreVideoCarroussel = nombreVideoCarroussel.intValue()+nombreVideoCarroussel1.intValue();

		System.out.println(nombreVideoCarroussel);
		
		// Parcours du carroussel pour l'affichage d'une fiche vidÃ©o dont la longueur du titre est infÃ©rieur Ã  11 caractÃ¨res
		while(intk < nombreVideoCarroussel.intValue()){
			
  			titreVideoC = selenium.getText("xpath=(//li[@id='videoBlocDefault']/div/div/h1)[" + k + "]");
 			System.out.println(titreVideoC);
			lgTitreVideo = selenium.getEval(selenium.getEval("'" + titreVideoC + "'.length"));
		    intLgTitreVideo = Integer.parseInt(lgTitreVideo);
		    
		    if(intLgTitreVideo>=11){
		    		
 				selenium.click("xpath=(//li[@id='videoBlocDefault']/a)[" + k + "]");
 				k = selenium.getEval(k + "+1");
 				intk = Integer.parseInt(k);
 				
		    }else{
		    	selenium.click("xpath=(//li[@id='videoBlocDefault']/a)[" + k + "]");
				selenium.waitForPageToLoad("30000");
				break;
		    }
		    
			
		}
		// Affichage de la fiche vidéo 
		selenium.click("xpath=(//li[@id='videoBlocDefault']/a)[" + k + "]");
		// Vérifier que le titre de la vidéo du carroussel correspond au titre de la fiche détaillée
		System.out.println("Fiche carroussel = "+titreVideoC);
		//a voir
		selenium.waitForPageToLoad("30000");
		selenium.click("xpath=(//li[@id='videoBlocDefault']/a)[" + k + "]");
		selenium.waitForPageToLoad("60000");
		selenium.click("xpath=(//li[@id='videoBlocDefault']/a)[" + k + "]");
		selenium.waitForPageToLoad("30000");
		
		selenium.waitForPageToLoad("30000");
		String titreVideoFiche = selenium.getText("id=videoTitle");
		System.out.println("Fiche vidéo = "+titreVideoFiche);
		assertEquals(titreVideoC, titreVideoFiche);
		assertTrue(selenium.isElementPresent("//aside[@id='listVideo']"));
		System.out.println("Réintégrer ce test \"verifyElementPresent //aside[@style='display: block; background-color: rgb(33, 33, 33);']\"");
		// Récupération du nombre de vidéo parmi la liste
		Number nombreVideo = selenium.getXpathCount("//li[@style='display: list-item;']");
		System.out.println(nombreVideo);
		// Le compteur de vidéo 'cptVideo' est initialisé à  1 pour récupéré la premiére vidéo de la liste afin de l'analyser
		cptVideo = "1";
		System.out.println(cptVideo);
		
		intCptVideo=Integer.parseInt(cptVideo);
		
		while(intCptVideo <= nombreVideo.intValue()){
			
			String nomVideo = selenium.getText("//li[" + cptVideo + "]/div[@class='infos']");
			String longueurNom = selenium.getEval(selenium.getEval("'" + nomVideo + "'.length"));
			int intLongueurNom = Integer.parseInt(longueurNom);
			if(intLongueurNom >= 11){
				// Récupération des dix premiers caractéres à  partir du titre de la vidéo
				selenium.click("xpath=(//li[@id='miniVideoBlocDefault']/a)[" + cptVideo + "]");
				selenium.waitForPageToLoad("30000");
				String videoTitle = selenium.getText("id=videoTitle");
				System.out.println(videoTitle);				
				// Retour à  la fiche vidéo précédente
				//String back = selenium.getEval(cptVideo + "-1");
				String back = selenium.getEval(cptVideo);
				selenium.open("/ovp-web/videoDetails.html?campaignId=1&videoId=" + back);
				System.out.println(back);
				String i = "0";
				int inti=Integer.parseInt(i);
				varVideoTitleComplete = "";
				while(inti<10){

					varVideoTitle = selenium.getEval("'" + videoTitle + "'['" + i + "']");
 					lgNomDansListe = selenium.getEval(selenium.getEval("'" + videoTitle + "'.length"));
					int intLgNomDansListe = Integer.parseInt(lgNomDansListe);
					
					if(intLgNomDansListe>=11){
						//varVideoTitleComplete = selenium.getEval("storedVars['varVideoTitleComplete']+storedVars['varVideoTitle']");
						varVideoTitleComplete=varVideoTitleComplete.concat(varVideoTitle);
						i = selenium.getEval(i + "+1;");
						inti=Integer.parseInt(i);
					}
					
				}
				
				// Construction du titre avec les points de suspensions
				System.out.println(varVideoTitleComplete);
				pointSuspension = "...";
				//recupereTitreV = selenium.getEval("storedVars['varVideoTitleComplete']+storedVars['pointSuspension']");
				recupereTitreV = varVideoTitleComplete.concat(pointSuspension);
				System.out.println(recupereTitreV);
				// Comparaison du nom construit avec les points de suspensions avec le nom affichÃ© dans la liste des vidÃ©os
				assertEquals(recupereTitreV, nomVideo);
				System.out.println("La nom de la vidéo "+nomVideo+" est bien testé");
				
				cptVideo = selenium.getEval(cptVideo + "+1");
				intCptVideo=Integer.parseInt(cptVideo);
				
				
			}else{
				cptVideo = selenium.getEval(cptVideo + "+1");
				intCptVideo=Integer.parseInt(cptVideo);
			}
				
		}
		selenium.selectWindow("null");
	}

}
