package framework;

import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import components.EasyPin_component;
import io.appium.java_client.android.AndroidDriver;

public class DeviceCapabilities {

	protected static AndroidDriver<WebElement> driver;
	protected static WebDriverWait wait5,wait10,wait20,wait30,wait60;
	protected static String easyPin;
	protected static AppiumServer appium;
	protected String deviceID;
	protected int port,systemPort;
	
	protected void setup(String deviceID,int port,int systemPort) throws Exception 
	{		
		appium = new AppiumServer(port);
		appium.startServer();
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","Emulator");
		capabilities.setCapability("udid",deviceID);
		capabilities.setCapability("systemPort",systemPort);
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("appPackage","com.ubyapp");
		capabilities.setCapability("appActivity","com.ubyapp.MainActivity");
		capabilities.setCapability("automationName","uiautomator2");
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:"+port+"/wd/hub"), capabilities);

		wait5= new WebDriverWait(driver, 5);
		wait10= new WebDriverWait(driver, 10);
		wait20= new WebDriverWait(driver, 20);
		wait30= new WebDriverWait(driver, 30);
		wait60= new WebDriverWait(driver, 60);
		
	}

}
