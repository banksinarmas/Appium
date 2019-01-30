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

	private String username,easyPin,currentPassword,newEasyPin;
	
	public ChangeEasyPin() throws IOException {
		this(DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"));	
	}

	public ChangeEasyPin(String username) throws IOException {
		super(false,username);
		
		this.username=username;
		Properties prop = LoadProperties.getUserProperties(this.username);
		this.easyPin=prop.getProperty("EASYPIN");
		this.currentPassword=prop.getProperty("PASSWORD");
		
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
	private void Test02_User_Profile_Menu() throws Exception
	{		
		changeEasyPin_comp.changeEasyPinMenu();
	}
	
	@Test(dependsOnMethods="Test02_User_Profile_Menu")
	private void Test03_Change_EasyPin_Menu() throws Exception
	{		
		changeEasyPin_comp.changeEasyPinMenu();
	}
	
	@Test(dependsOnMethods= "Test03_Change_EasyPin_Menu")
	private void Test04_Create_New_EasyPin() throws Exception
	{		
		easyPin_comp.createEasyPin(newEasyPin);
	}
	@Test(dependsOnMethods= "Test04_Create_New_EasyPin")
	private void Test05_Validate_Password_Page() throws Exception
	{	
		changeEasyPin_comp.validatePassword(currentPassword);
	}

}
