package userprofile;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.ChangeEasyPin_component;
import framework.DeviceCapabilities;
import framework.LoadProperties;

public class ChangeEasyPin extends DeviceCapabilities {

	private String currentPassword,newEasyPin;
		
	@BeforeClass
	private void config() throws IOException {
	 Properties prop = LoadProperties.getProperties("credential.properties");
	 currentPassword=prop.getProperty("password");
	 newEasyPin=prop.getProperty("newEasyPin");
	}

	@Test
	private void Test01_After_Login_Page() throws Exception
	{
		ChangeEasyPin_component.changeEasyPinMenu();
	}
	@Test(dependsOnMethods= "Test01_After_Login_Page")
	private void Test02_Change_EasyPin_Page() throws Exception
	{		
		ChangeEasyPin_component.createNew(newEasyPin);
	}
	@Test(dependsOnMethods= "Test02_Change_EasyPin_Page")
	private void Test03_Validate_Password_Page() throws Exception
	{		
		ChangeEasyPin_component.validatePassword(currentPassword);
	}
	
}
