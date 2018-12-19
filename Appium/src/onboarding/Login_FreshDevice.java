package onboarding;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.Onboarding_component;
import framework.FreshDevice;
import framework.LoadProperties;

public class Login_FreshDevice extends FreshDevice{

	private String username,password,easyPin;
	private Onboarding_component onboarding_comp;
	
	private String releaseLockdownURL= "http://simigi.banksinarmas.com/ibank/server-init?action=inactiveDeviceLockdownXYZ&loginName=";
	private String FOLDER = "/Onboarding/Login_FreshDevice/"+deviceID;
	
	public Login_FreshDevice() throws IOException {
		super();
		Properties prop = LoadProperties.getProperties("credential.properties");
		username=prop.getProperty("username");
		password=prop.getProperty("password");
		easyPin=prop.getProperty("easyPin");
	}
	
	public Login_FreshDevice(String deviceID,int port,int systemPort,String username,String password,String easyPin) {
		super(deviceID,port,systemPort);
		this.username=username;
		this.password=password;
		this.easyPin=easyPin;
	}
	
	@BeforeClass
	public void loadComponent() throws HttpException, IOException{
		
		System.out.println("Release lockdown device for username "+username);
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(releaseLockdownURL+username);
		client.executeMethod(method);
		
		onboarding_comp = new Onboarding_component(driver);
		FOLDER=apkVersion+FOLDER;
	}
	
	@Test
	public void Test01_Onboarding_Landing_Page(Method method) {
		System.out.println(deviceID+"_"+method.getName());
		onboarding_comp.landingPage(FOLDER,method.getName());
	}
	
	@Test(dependsOnMethods="Test01_Onboarding_Landing_Page")
	public void Test02_Login_Username_Password(Method method) {
		System.out.println(deviceID+"_"+method.getName());
		onboarding_comp.loginUsernamePassword(FOLDER,method.getName(),username, password);	
	}
	
	@Test(dependsOnMethods="Test02_Login_Username_Password")
	public void Test03_Onboarding_Input_OTP(Method method) throws Exception {
		System.out.println(deviceID+"_"+method.getName());
		onboarding_comp.inputOTP(FOLDER,method.getName());
	}
	
	@Test(dependsOnMethods="Test03_Onboarding_Input_OTP")
	public void Test04_Onboarding_Create_EasyPin(Method method) throws Exception {
		System.out.println(deviceID+"_"+method.getName());
		onboarding_comp.createEasyPin(FOLDER,method.getName(),easyPin);
	}
	
	@Test(dependsOnMethods="Test04_Onboarding_Create_EasyPin")
	public void Test05_Dashboard_Page(Method method) {
		System.out.println(deviceID+"_"+method.getName());
		onboarding_comp.dashboardFreshDevice(FOLDER,method.getName());	
	}
	
	@Test(dependsOnMethods="Test05_Dashboard_Page")
	public void Test06_Logout(Method method) throws Exception {
		System.out.println(deviceID+"_"+method.getName());
		onboarding_comp.logout();
	}
}
