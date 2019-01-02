package framework;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import io.appium.java_client.android.AndroidDriver;

@Listeners(framework.TestListener.class)
public class LockdownDevice {

	private AppiumServer appium;
	protected AndroidDriver<WebElement> driver;

	protected String deviceID,easyPin,apkVersion;
	private int port,systemPort;


	protected LockdownDevice() throws IOException {
		Properties deviceProp=LoadProperties.getProperties("device.properties");
		port=Integer.parseInt(deviceProp.getProperty("port"));
		systemPort=Integer.parseInt(deviceProp.getProperty("systemPort"));
		deviceID=deviceProp.getProperty("deviceID");
	}

	protected LockdownDevice(String deviceID,int port,int systemPort) {
		this.port=port;
		this.systemPort=systemPort;
		this.deviceID=deviceID;
	}

	@BeforeClass
	public void launch() throws Exception 
	{	
		AndroidAPK.adbCommand("cmd /c adb -s "+deviceID+" shell pm uninstall io.appium.uiautomator2.server");
		AndroidAPK.adbCommand("cmd /c adb -s "+deviceID+" shell pm uninstall io.appium.uiautomator2.server.test");
		apkVersion=AndroidAPK.getApkVersion(deviceID);

		System.out.println("Launching apk version: "+apkVersion);
		appium = new AppiumServer(port);
		appium.startServer();

		driver=DriverSetup.lockdownDevice(deviceID, port, systemPort);

		this.easyPin=LoadProperties.getProperties("credential.properties").getProperty("easyPin");

	}
	@AfterClass
	public void stopAppium() 
	{
		appium.stopServer();
	}

	
	public AndroidDriver<WebElement> getDriver() {
		
		return this.driver;
	}
}
