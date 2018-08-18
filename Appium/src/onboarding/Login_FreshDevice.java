package onboarding;

import org.testng.annotations.Test;

import components.OTP_component;
import components.Onboarding_component;
import framework.DeviceCapabilities;

public class Login_FreshDevice extends DeviceCapabilities {
	
	String username="usertest1";
	String password="12345678";
	String easyPin="123456";
	
	@Test
	public void Test00_Landing_Page() throws Exception
	{
		Onboarding_component.landingPage();
	}
	
	@Test(dependsOnMethods="Test00_Landing_Page")
	public void Test01_Username_Password() throws Exception
	{
		Onboarding_component.loginUsernamePassword(username, password);
	}
	
	@Test(dependsOnMethods="Test00_Landing_Page")
	public void Test02_Input_OTP() throws Exception
	{
		OTP_component.input();
	}
	
	@Test(dependsOnMethods="Test00_Landing_Page")
	public void Test03_Create_EasyPin() throws Exception
	{
		Onboarding_component.createEasyPin(easyPin);
	}
	
	@Test(dependsOnMethods="Test03_Create_EasyPin")
	public void Test04_Create_EasyPin() throws Exception
	{
		Onboarding_component.dashboardFreshDevice();
	}
	
	
}
