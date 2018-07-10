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

import framework.DeviceCapabilities;
import io.appium.java_client.android.AndroidDriver;

public class ChangeEasyPin  {

	private  AndroidDriver<WebElement> driver;
	private  WebDriverWait wait5,wait10,wait20,wait30,wait50;

	private String deviceName,udid,systemPort,url;

	public ChangeEasyPin()  {
		this("emulator-5554","emulator-5554","7000","127.0.0.1:4723");
	}

	public ChangeEasyPin(String deviceName,String udid,String systemPort,String url) {
		
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

		driver = new AndroidDriver<WebElement>(new URL("http://"+url+"/wd/hub"), capabilities);

		//if (driver.isLocked())driver.unlockDevice();

		wait5= new WebDriverWait(driver, 5);
		wait10= new WebDriverWait(driver, 10);
		wait20= new WebDriverWait(driver, 20);
		wait30= new WebDriverWait(driver, 30);
		wait50= new WebDriverWait(driver, 50);
	}

	@Test
	public void Test00_Login_Page() throws Exception
	{
		try {
			wait20.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button2"))).click();
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text=\"Don't Show This Again\"]"))).click();
			driver.findElement(By.xpath("//*[@text='OK']")).click();
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text ='LOGIN']"))).click();		
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).isDisplayed();
		driver.findElementByClassName("android.widget.EditText").sendKeys("123456");
		driver.findElement(By.xpath("//*[@text='CONTINUE'] | //*[@text='LANJUTKAN']")).click();

	}

	@Test(dependsOnMethods= {"Test00_Login_Page"})
	public void Test01_After_Login_Page() throws Exception
	{
		try {
			if(wait50.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Simas Kasbon']"))).isDisplayed()) {
				driver.findElement(By.xpath("//*[@text='Sembunyikan sampai periode berikutnya']")).click();
				driver.findElement(By.xpath("//*[@text='TIDAK']")).click();

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		wait50.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']"))).isDisplayed();

		wait50.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		driver.findElement(By.className("android.widget.TextView")).click();

	}
	@Test(dependsOnMethods= "Test01_After_Login_Page")
	public void Test02_Update_EasyPin_Page() throws Exception
	{		
		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Update EasyPIN'] | //*[@text='Ubah EasyPIN']"))).click();;

		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Buat EasyPIN baru'] | //*[@text='Enter new EasyPIN']"))).isDisplayed();
		driver.findElement(By.className("android.widget.EditText")).sendKeys("123456");

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Konfirmasi EasyPIN'] | //*[@text='Confirm new EasyPIN']"))).click();
		driver.findElement(By.className("android.widget.EditText")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE'] ")).click();

	}
	@Test(dependsOnMethods= "Test02_Update_EasyPin_Page")
	public void Test03_Enter_Password_Page() throws Exception
	{		
		Thread.sleep(500);
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Enter current password'] | //*[@text='Masukkan kata sandi terakhir'] "))).sendKeys("Ab123456");
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='android.widget.EditText']"))).sendKeys("Ab123456");
		driver.findElement(By.xpath("//*[@text='Konfirmasi'] | //*[@text='Confirm']")).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle"))).isDisplayed();
		driver.findElement(By.id("android:id/button1")).click();

	}
	
	@Test(dependsOnMethods= "Test03_Enter_Password_Page")
	public void logout() throws Exception
	{		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']"))).isDisplayed();
		wait30.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		driver.findElement(By.className("android.widget.TextView")).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KELUAR'] | //*[@text='LOG OUT']"))).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text ='LOGIN']"))).isDisplayed();

	}
}
