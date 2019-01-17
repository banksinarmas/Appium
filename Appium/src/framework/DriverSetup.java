package framework;

import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class DriverSetup {
	
	
    /**Setup the driver for android
     * @default deviceName		: name of android device (can be any)
     * @default platformName	: name of platform 
     * @default automationName	: automation engine to be used 
     * @param   deviceID 		: android device unique ID
     * @param   port 	 		: port which the appium server will be listened to
     * @param   systemPort 	 	: port which the uiautomator server will be listened to
     * @param   appPackage 		: android application package name
     * @param   appActivity 	: activity to launch app
     * @param   noReset 		: Whether about to launch the app in cleared data or not
     * @return  android driver with the following capabilities
     */
	
	public static AndroidDriver<WebElement> androidDevice(String deviceID,int port,int systemPort,String appPackage,String appActivity,boolean noReset) throws Exception 
	{		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","Android");
		capabilities.setCapability("udid",deviceID);
		capabilities.setCapability("systemPort",systemPort);
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("noReset", noReset);
		capabilities.setCapability("appPackage",appPackage);
		capabilities.setCapability("appActivity",appActivity);
		capabilities.setCapability("automationName","uiautomator2");
		capabilities.setCapability("newCommandTimeout", 360);
		
		return new AndroidDriver<WebElement>(new URL("http://127.0.0.1:"+port+"/wd/hub"), capabilities);
	}
	

}
