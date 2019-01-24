package framework;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import io.appium.java_client.android.AndroidDriver;

@Listeners(framework.TestListener.class)
public class DeviceSetup  {

	private AppiumServer appium;
	protected AndroidDriver<WebElement> driver;

	protected String deviceID,apkVersion;
	private int port,systemPort;
	private boolean freshDevice;

	protected static final Properties DEFAULT_PROPERTIES = LoadProperties.getDefaultProperties();

	protected DeviceSetup(boolean freshDevice,String username) throws IOException {
		Properties deviceProp = LoadProperties.getDeviceProperties();
		String[] device = deviceProp.getProperty(username).split(",");

		this.freshDevice=freshDevice;
		this.deviceID=device[0];
		this.port=Integer.parseInt(device[1]);
		this.systemPort=Integer.parseInt(device[2]);

	}

	@BeforeClass
	public void launch() throws Exception 
	{	
		//uninstall uiautomator from device
		AndroidAPK.adbCommand("cmd /c adb -s "+deviceID+" shell pm uninstall io.appium.uiautomator2.server");
		AndroidAPK.adbCommand("cmd /c adb -s "+deviceID+" shell pm uninstall io.appium.uiautomator2.server.test");

		//start appium
		appium = new AppiumServer(port);
		appium.startServer();

		//if setup is fresh device this will check the latest apk, download apk, and launch app after cleared its data
		boolean noReset=true;
		if(freshDevice) {
			AndroidAPK apk = new AndroidAPK(deviceID,port,systemPort);
			apk.download();
			apk.install();
			noReset=false;
		}
		
		apkVersion=AndroidAPK.getApkVersion(deviceID);
		System.out.println("Launching apk version: "+apkVersion +" on device: "+deviceID);

		//launch simobiplus app 
		driver= DriverSetup.androidDevice(deviceID, port, systemPort, "com.ubyapp", "com.ubyapp.MainActivity", noReset);


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
