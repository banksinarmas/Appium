package framework;

import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class DeviceSetup {

	public static AndroidDriver<WebElement> lockdownDevice(String deviceID,int port,int systemPort) throws Exception 
	{		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","Emulator");
		capabilities.setCapability("udid",deviceID);
		capabilities.setCapability("systemPort",systemPort);
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("appPackage","com.ubyapp");
		capabilities.setCapability("appActivity","com.ubyapp.MainActivity");
		capabilities.setCapability("automationName","uiautomator2");
		capabilities.setCapability("newCommandTimeout", 360);
		
		return new AndroidDriver<WebElement>(new URL("http://127.0.0.1:"+port+"/wd/hub"), capabilities);
	}
	
	public static AndroidDriver<WebElement> freshDevice(String deviceID,int port,int systemPort) throws Exception 
	{		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","Emulator");
		capabilities.setCapability("udid",deviceID);
		//capabilities.setCapability("systemPort",systemPort);
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("appPackage","com.ubyapp");
		capabilities.setCapability("appActivity","com.ubyapp.MainActivity");
		capabilities.setCapability("automationName","uiautomator2");
		capabilities.setCapability("newCommandTimeout", 360);
		
		return new AndroidDriver<WebElement>(new URL("http://127.0.0.1:"+port+"/wd/hub"), capabilities);
	}

}
