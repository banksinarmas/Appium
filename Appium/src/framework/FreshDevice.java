package framework;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;

public class FreshDevice {

	private AppiumServer appium;
	protected AndroidDriver<WebElement> driver;
	
	protected String deviceID;
	private int port,systemPort;

	
	protected FreshDevice() throws IOException {
		Properties deviceProp=LoadProperties.getProperties("device.properties");
		port=Integer.parseInt(deviceProp.getProperty("port"));
		systemPort=Integer.parseInt(deviceProp.getProperty("systemPort"));
		deviceID=deviceProp.getProperty("deviceID");
	}
	
	protected FreshDevice(String deviceID,int port,int systemPort) {
		this.port=port;
		this.systemPort=systemPort;
		this.deviceID=deviceID;
	}
	
	@BeforeClass
	protected void launch() throws Exception 
	{		
		appium = new AppiumServer(port);
		appium.startServer();

		driver=DeviceSetup.freshDevice(deviceID, port, systemPort);
						
	}
	@AfterClass
	protected void stopAppium() throws Exception
	{
		appium.stopServer();
	}
}
