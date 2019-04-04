package userprofile;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.ChangeEasyPin_component;
import components.EasyPin_component;
import framework.DeviceSetup;
import framework.LoadProperties;

public class ChangeEasyPin extends DeviceSetup {

	private EasyPin_component easyPin_comp;
	private ChangeEasyPin_component changeEasyPin_comp;

	private String username,easyPin,newEasyPin;
	
	public ChangeEasyPin() throws IOException {
		this(DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"));	
	}

	public ChangeEasyPin(String username) throws IOException {
		super(false,username);
		
		this.username=username;
		Properties prop = LoadProperties.getUserProperties(this.username);
		this.easyPin=prop.getProperty("EASYPIN");
		
		this.newEasyPin=easyPin;
		
	}

	@BeforeClass
	private void loadComponent(){
		easyPin_comp= new EasyPin_component(driver);
		changeEasyPin_comp=new ChangeEasyPin_component(driver);
	}

	@Test
	private void Test01_Login() throws Exception
	{			
		easyPin_comp.loginEasyPin(easyPin);
	}

	@Test(dependsOnMethods="Test01_Login")
	private void Test02_Settings_Menu() throws Exception
	{		
		changeEasyPin_comp.settingsMenu();
	}
	
	@Test(dependsOnMethods="Test02_Settings_Menu")
	private void Test03_Change_EasyPin_Menu() throws Exception
	{		
		changeEasyPin_comp.changeEasyPinMenu();
	}
	
	@Test(dependsOnMethods= "Test03_Change_EasyPin_Menu")
	private void Test04_ChangeEasyPin() throws Exception
	{		
		changeEasyPin_comp.changeEasyPin(easyPin,newEasyPin);
	}


}
