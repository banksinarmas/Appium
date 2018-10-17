package onboarding;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import components.EasyPin_component;
import components.FundTransfer_component;
import components.OTP_component;
import framework.AppiumServer;
import framework.DriverSetup;
import framework.LoadProperties;
import io.appium.java_client.android.AndroidDriver;

public class Login_LockdownDevice {

	private AppiumServer appium;
	private AndroidDriver<WebElement> driver;
	
	protected String deviceID,easyPin;
	private int port,systemPort;
	
	protected OTP_component otp_comp;
	protected EasyPin_component easyPin_comp;
	protected FundTransfer_component fundTransfer_comp;	
	
	protected Login_LockdownDevice() throws IOException {
		
		Properties deviceProp=LoadProperties.getProperties("device.properties");
		port=Integer.parseInt(deviceProp.getProperty("port"));
		systemPort=Integer.parseInt(deviceProp.getProperty("systemPort"));
		deviceID=deviceProp.getProperty("deviceID");
		
	}
	
	protected Login_LockdownDevice(String deviceID,int port,int systemPort) {
		
		this.port=port;
		this.systemPort=systemPort;
		this.deviceID=deviceID;
		
	}
	@BeforeClass
	protected void start() throws IOException 
	{		
		appium = new AppiumServer(port);
		appium.startServer();

		driver=new DriverSetup(deviceID,port,systemPort).driver;
			
		otp_comp=new OTP_component(driver);
		fundTransfer_comp= new FundTransfer_component(driver);
		easyPin_comp=new EasyPin_component(driver);
		
	}
	@AfterClass
	protected void stop() throws Exception
	{
		appium.stopServer();
	}
	protected void login(Method method) throws IOException {

		Properties prop = LoadProperties.getProperties("credential.properties");
		easyPin=prop.getProperty("easyPin");
	
		System.out.println(deviceID+"_"+method.getName());
		easyPin_comp.loginEasyPin(easyPin);
	}
	
}
