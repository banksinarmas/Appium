package framework;

import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;

public class FreshDeviceCapabilities {
	protected static AndroidDriver<WebElement> driver;
	protected static WebDriverWait wait5,wait10,wait20,wait30,wait60;
	
	@BeforeClass
	protected void Setup() throws Exception 
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","Emulator");
		capabilities.setCapability("udid","emulator-5554");
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("appPackage","com.ubyapp");
		capabilities.setCapability("appActivity","com.ubyapp.MainActivity");
		capabilities.setCapability("automationName","uiautomator2");
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		wait5= new WebDriverWait(driver, 5);
		wait10= new WebDriverWait(driver, 10);
		wait20= new WebDriverWait(driver, 20);
		wait30= new WebDriverWait(driver, 30);
		wait60= new WebDriverWait(driver, 60);
	}
}
