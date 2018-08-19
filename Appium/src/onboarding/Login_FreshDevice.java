package onboarding;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.Onboarding_component;
import framework.FreshDeviceCapabilities;
import framework.LoadProperties;

public class Login_FreshDevice extends FreshDeviceCapabilities {
	
	private String username,password,easyPin;
	
	@BeforeClass
	private void config() throws IOException {
	 Properties prop = LoadProperties.getProperties("credential.properties");
	 username=prop.getProperty("username");
	 password=prop.getProperty("password");
	 easyPin=prop.getProperty("easyPin");
	}
	
	@Test
	private void Test01_Landing_Page() throws Exception
	{
		Onboarding_component.landingPage();
	}
	
	@Test(dependsOnMethods="Test01_Landing_Page")
	private void Test02_Login_Username_Password_Page() throws Exception
	{
		Onboarding_component.loginUsernamePassword(username, password);
	}
	
	@Test(dependsOnMethods="Test02_Login_Username_Password_Page")
	private void Test03_Input_OTP_Page() throws Exception
	{
		Onboarding_component.inputOTP();
	}
	
	@Test(dependsOnMethods="Test03_Input_OTP_Page")
	private void Test04_Create_EasyPin_Page() throws Exception
	{
		Onboarding_component.createEasyPin(easyPin);
	}
	
	@Test(dependsOnMethods="Test04_Create_EasyPin_Page")
	private void Test05_Dashboard_Page() throws Exception
	{
		Onboarding_component.dashboardFreshDevice();
	}
	
}
