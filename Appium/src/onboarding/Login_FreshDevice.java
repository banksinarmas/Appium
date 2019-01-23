package onboarding;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.httpclient.HttpException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.EasyPin_component;
import components.OTP_component;
import components.Onboarding_component;
import framework.DeviceSetup;
import framework.LoadProperties;

public class Login_FreshDevice extends DeviceSetup{

	private String username,password,easyPin;
	private OTP_component otp_comp;
	private Onboarding_component onboarding_comp;
	private EasyPin_component easyPin_comp;
	
//	private String releaseLockdownURL= "http://simigi.banksinarmas.com/ibank/server-init?action=inactiveDeviceLockdownXYZ&loginName=";

	public Login_FreshDevice() throws IOException {
		this(DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"));
	}
	
	public Login_FreshDevice(String username) throws IOException {
		super(true,username);

		this.username=username;
		Properties prop = LoadProperties.getUserProperties(username);
		this.password=prop.getProperty("PASSWORD");
		this.easyPin=prop.getProperty("EASYPIN");
		
		this.username=username;
	}
	
	@BeforeClass
	public void loadComponent() throws HttpException, IOException{
	/*	
		System.out.println("Release lockdown device for username "+username);
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(releaseLockdownURL+username);
		client.executeMethod(method);
		*/
		onboarding_comp = new Onboarding_component(driver);
		otp_comp = new OTP_component(driver);
		easyPin_comp = new EasyPin_component(driver);
	}
	
	@Test
	public void Test01_Onboarding_Landing_Page() {
		
		onboarding_comp.landingPage();
	}
	
	@Test(dependsOnMethods="Test01_Onboarding_Landing_Page")
	public void Test02_Login_Username_Password() {
		
		onboarding_comp.loginUsernamePassword(username, password);	
	}
	
	@Test(dependsOnMethods="Test02_Login_Username_Password")
	public void Test03_Onboarding_Input_OTP() throws Exception {
		
		otp_comp.inputOTP();
	}
	
	@Test(dependsOnMethods="Test03_Onboarding_Input_OTP")
	public void Test04_Onboarding_Create_EasyPin() throws Exception {
		
		easyPin_comp.createEasyPin(easyPin);
	}
	
	@Test(dependsOnMethods="Test04_Onboarding_Create_EasyPin")
	public void Test05_Dashboard_Page() {
		
		onboarding_comp.dashboardFreshDevice();	
	}
	
	@Test(dependsOnMethods="Test05_Dashboard_Page")
	public void Test06_Logout() throws Exception {
		
		onboarding_comp.logout();
	}
}
