package userprofile;

import org.testng.annotations.Test;

import components.ChangeEasyPin_component;
import components.Onboarding_component;
import framework.DeviceCapabilities;

public class ChangeEasyPin extends DeviceCapabilities {

	String easyPin="123456";
	String currentPassword="12345678";
	
	@Test
	public void Test00_Login_Page() throws Exception
	{
		Onboarding_component.loginEasyPin(easyPin);
	}

	@Test(dependsOnMethods= {"Test00_Login_Page"})
	public void Test01_After_Login_Page() throws Exception
	{
		ChangeEasyPin_component.changeEasyPinMenu();
	}
	@Test(dependsOnMethods= "Test01_After_Login_Page")
	public void Test02_Change_EasyPin_Page() throws Exception
	{		
		ChangeEasyPin_component.createNew(easyPin);
	}
	@Test(dependsOnMethods= "Test02_Change_EasyPin_Page")
	public void Test03_Validate_Password_Page() throws Exception
	{		
		ChangeEasyPin_component.validatePassword(currentPassword);
	}
	
}
