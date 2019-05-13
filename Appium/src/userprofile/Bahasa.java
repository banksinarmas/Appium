package userprofile;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.Bahasa_component;
import components.EasyPin_component;
import framework.DeviceSetup;
import framework.LoadProperties;

public class Bahasa extends DeviceSetup {

	private EasyPin_component easyPin_comp;
	//private ChangeEasyPin_component changeEasyPin_comp;
	//private ChangePassword_component changePassword_comp;
	private Bahasa_component bahasa_comp;
	
	private String username,easyPin,BahasaIndonesia;
	
	public Bahasa() throws IOException {
		this(DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"));	
	}

	public Bahasa(String username) throws IOException {
		super(false,username);
		
		this.username=username;
		Properties prop = LoadProperties.getUserProperties(this.username);
		this.easyPin=prop.getProperty("EASYPIN");
		
		//this.newEasyPin=easyPin;
		this.BahasaIndonesia="Bahasa Indonesia";
		
	}

	@BeforeClass
	private void loadComponent(){
		easyPin_comp= new EasyPin_component(driver);
		//changeEasyPin_comp=new ChangeEasyPin_component(driver);
		bahasa_comp=new Bahasa_component(driver);
	}

	@Test
	private void Test01_Login() throws Exception
	{			
		easyPin_comp.loginEasyPin(easyPin);
	}

	@Test(dependsOnMethods="Test01_Login")
	private void Test02_Settings_Menu() throws Exception
	{		
		bahasa_comp.settingsMenu();
	}
	
	@Test(dependsOnMethods="Test02_Settings_Menu")
	private void Test03_Bahasa_Menu() throws Exception
	{		
		bahasa_comp.BahasaMenu();
	}
	
	@Test(dependsOnMethods= "Test03_Bahasa_Menu")
	private void Test04_Bahasa() throws Exception
	{		
		bahasa_comp.Bahasa(BahasaIndonesia);
	}


}
