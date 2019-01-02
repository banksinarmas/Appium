package framework;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import io.appium.java_client.android.AndroidDriver;

@Listeners(framework.TestListener.class)
public class FreshDevice {

	private AppiumServer appium;
	public AndroidDriver<WebElement> driver;
	
	protected String deviceID,apkVersion;
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
		AndroidAPK.adbCommand("cmd /c adb -s "+deviceID+" shell pm uninstall io.appium.uiautomator2.server");
		AndroidAPK.adbCommand("cmd /c adb -s "+deviceID+" shell pm uninstall io.appium.uiautomator2.server.test");

		appium = new AppiumServer(port);
		appium.startServer();

		AndroidAPK apk = new AndroidAPK(deviceID,port,systemPort);
		apk.download();
		apk.install();
		apkVersion=AndroidAPK.getApkVersion(deviceID);
		
		System.out.println("Launching apk version: "+apkVersion);
		driver=DriverSetup.freshDevice(deviceID, port, systemPort);
						
	}
	@AfterClass
	protected void stopAppium() throws Exception
	{
		appium.stopServer();
	
	}

	public AndroidDriver<WebElement> getDriver() {
		
		return this.driver;
	}
}
