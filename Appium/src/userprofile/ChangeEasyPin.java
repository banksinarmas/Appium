package userprofile;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class ChangeEasyPin {

	private  AndroidDriver<WebElement> driver;
	private  WebDriverWait wait10,wait30,wait60;

	//AppiumManager apm = new AppiumManager();
	private String deviceName,udid,systemPort;

	public ChangeEasyPin() {
		this.deviceName="4723";
		this.udid="emulator-5554";
		this.systemPort="7001";	

	}

	public ChangeEasyPin(String deviceName,String udid,String systemPort) {
		this.deviceName=deviceName;
		this.udid=udid;
		this.systemPort=systemPort;

	}

	@BeforeClass
	public void Setup() throws Exception 
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName",deviceName);
		capabilities.setCapability("udid",udid);
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("appPackage","com.ubyapp");
		capabilities.setCapability("appActivity","com.ubyapp.MainActivity");
		capabilities.setCapability("automationName","uiautomator2");
		capabilities.setCapability("systemPort",systemPort);

		driver = new AndroidDriver<WebElement>(new URL("http://192.168.1.62:4444/wd/hub"), capabilities);

		if (driver.isLocked())driver.unlockDevice();

		wait10= new WebDriverWait(driver, 10);
		wait30= new WebDriverWait(driver, 30);
		wait60= new WebDriverWait(driver, 60);
	}

	@Test
	public void Test00_Login_Page() throws Exception
	{
		try {
			wait30.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button2"))).click();

		} catch (Exception e) {
			// TODO: handle exception
		}

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text ='LOGIN']"))).click();		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).isDisplayed();
		driver.findElementByClassName("android.widget.EditText").sendKeys("123456");
		driver.findElement(By.xpath("//*[@text='CONTINUE'] | //*[@text='LANJUTKAN']")).click();

	}

	@Test(dependsOnMethods= "Test00_Login_Page")
	public void Test01_After_Login_Page() throws Exception
	{
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']"))).isDisplayed();
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		driver.findElement(By.className("android.widget.TextView")).click();

	}
	@Test(dependsOnMethods= "Test01_After_Login_Page")
	public void Test02_Update_EasyPin_Page() throws Exception
	{		
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Update EasyPIN'] | //*[@text='Ubah EasyPIN']"))).click();;

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Buat EasyPIN baru'] | //*[@text='Enter new EasyPIN']"))).isDisplayed();
		driver.findElement(By.className("android.widget.EditText")).sendKeys("123456");

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Konfirmasi EasyPIN'] | //*[@text='Confirm new EasyPIN']"))).click();
		driver.findElement(By.className("android.widget.EditText")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE'] ")).click();

	}
	@Test(dependsOnMethods= "Test02_Update_EasyPin_Page")
	public void Test03_Enter_Password_Page() throws Exception
	{		
		//Thread.sleep(1000);
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Enter current password'] | //*[@text='Masukkan kata sandi terakhir'] "))).sendKeys("Ab123456");
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='android.widget.EditText']"))).sendKeys("Ab123456");
		driver.findElement(By.xpath("//*[@text='Konfirmasi'] | //*[@text='Confirm']")).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle"))).isDisplayed();
		driver.findElement(By.id("android:id/button1")).click();

	}
	@AfterClass
	public void logout() throws Exception
	{		
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']"))).isDisplayed();
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		driver.findElement(By.className("android.widget.TextView")).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KELUAR'] | //*[@text='LOG OUT']"))).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text ='LOGIN']"))).isDisplayed();

	}
}
