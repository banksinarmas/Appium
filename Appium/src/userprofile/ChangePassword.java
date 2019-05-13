package userprofile;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import components.ChangePassword_component;
import components.ChangeEasyPin_component;
import components.EasyPin_component;
import framework.DeviceSetup;
import framework.LoadProperties;

public class ChangePassword extends DeviceSetup {
	private EasyPin_component easyPin_comp;
	//private ChangeEasyPin_component changeEasyPin_comp;
	private ChangePassword_component changePassword_comp;
	
	private String username,easyPin,newEasyPin,currentPassword, newPassword;
	
	public ChangePassword() throws IOException {
		this(DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"));	
	}

	public ChangePassword(String username) throws IOException {
		super(false,username);
		
		this.username=username;
		Properties prop = LoadProperties.getUserProperties(this.username);
		this.easyPin=prop.getProperty("EASYPIN");
		
		this.newEasyPin=easyPin;
		this.currentPassword=prop.getProperty("PASSWORD");
		this.newPassword ="Ab12345678";
		
	}

	@BeforeClass
	private void loadComponent(){
		easyPin_comp= new EasyPin_component(driver);
		//changeEasyPin_comp=new ChangeEasyPin_component(driver);
		changePassword_comp=new ChangePassword_component(driver);
	}

	@Test
	private void Test01_Login() throws Exception
	{			
		easyPin_comp.loginEasyPin(easyPin);
	}

	@Test(dependsOnMethods="Test01_Login")
	private void Test02_Settings_Menu() throws Exception
	{		
		changePassword_comp.settingsMenu();
	}
	
	@Test(dependsOnMethods="Test02_Settings_Menu")
	private void Test03_Change_Password_Menu() throws Exception
	{		
		changePassword_comp.changePasswordMenu();
	}
	
	@Test(dependsOnMethods= "Test03_Change_Password_Menu")
	private void Test04_ChangePassword() throws Exception
	{		
		changePassword_comp.changePassword(currentPassword);
	}
	@Test(dependsOnMethods= "Test04_ChangePassword")
	private void Test04_NewPassword() throws Exception
	{		
		changePassword_comp.NewPassword(newPassword);
	}


	
}
