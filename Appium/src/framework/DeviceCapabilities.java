package framework;

import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.android.AndroidDriver;

public class DeviceCapabilities {

	public  AndroidDriver<WebElement> driver;
	public  WebDriverWait wait5,wait10,wait20,wait30,wait50;

	public String deviceName,udid,systemPort,url;

	public DeviceCapabilities() {
			
		this("emulator-5554","emulator-5554","7000","127.0.0.1:4723");

	}

	public DeviceCapabilities(String deviceName,String udid,String systemPort,String url) {
		this.deviceName=deviceName;
		this.udid=udid;
		this.systemPort=systemPort;
		this.url=url;
	}

	@BeforeClass
	public void Setup() throws Exception 
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName",deviceName);
		capabilities.setCapability("applicationName",deviceName);
		capabilities.setCapability("udid",udid);
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("appPackage","com.ubyapp");
		capabilities.setCapability("appActivity","com.ubyapp.MainActivity");
		capabilities.setCapability("automationName","uiautomator2");
		capabilities.setCapability("systemPort",systemPort);
		capabilities.setCapability("skipUnlock",true);
		
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);

		//if (driver.isLocked()) {driver.pressKeyCode(26);driver.swipe(100, 500, 100, 1400, 100);}

		wait5= new WebDriverWait(driver, 5);
		wait10= new WebDriverWait(driver, 10);
		wait20= new WebDriverWait(driver, 20);
		wait30= new WebDriverWait(driver, 30);
		wait50= new WebDriverWait(driver, 50);
	}
}
