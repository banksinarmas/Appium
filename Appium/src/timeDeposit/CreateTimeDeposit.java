package timeDeposit;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class CreateTimeDeposit {

	private static AndroidDriver<WebElement> driver;
	private static WebDriverWait wait10,wait30,wait60;

	//AppiumManager apm = new AppiumManager();

	@BeforeClass
	public void Setup() throws Exception 
	{
		//	apm.appiumServerForAndroid();
		Thread.sleep(5000);

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","Emulator");
		capabilities.setCapability("udid","emulator-5554");
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("appPackage","com.ubyapp");
		capabilities.setCapability("appActivity","com.ubyapp.MainActivity");
		capabilities.setCapability("automationName","uiautomator2");
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		wait10= new WebDriverWait(driver, 10);
		wait30= new WebDriverWait(driver, 30);
		wait60= new WebDriverWait(driver, 60);
	}

	@Test
	public void Test00_Login_Page() throws Exception
	{
		try {
			wait10.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button2"))).click();

		} catch (Exception e) {
			// TODO: handle exception
		}

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text ='LOGIN']"))).click();		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).isDisplayed();
		driver.findElementByClassName("android.widget.EditText").sendKeys("123456");
		driver.findElement(By.xpath("//*[@text='CONTINUE'] | //*[@text='LANJUTKAN']")).click();

	}

	@Test(dependsOnMethods="Test00_Login_Page")
	public void Test01_After_Login_Page() throws Exception
	{

		WebElement tdElement = wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@content-desc='Time Deposit'] | //*[@content-desc='Deposito Berjangka']")));
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		tdElement.click();

		Thread.sleep(500);	
		driver.swipe(50, 1500, 50, 200, 3000);
		Thread.sleep(500);
		driver.swipe(50, 1500, 50, 200, 3000);
		Thread.sleep(500);		

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text ='CREATE TIME DEPOSIT'] | //*[@text ='Deposito Berjangka Baru']"))).click();

	}

	@Test(dependsOnMethods="Test01_After_Login_Page")
	public void Test02_Time_Deposit_Page() throws Exception
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available balance')] | //*[contains(@text,'Saldo tersedia')]"))).isDisplayed();

		List<WebElement> selAcc = driver.findElements(By.xpath("//*[@text='Select account'] | //*[@text='Pilih akun']"));
		selAcc.get(selAcc.size()-1).click();

		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Available balance')] | //*[contains(@text,'Saldo tersedia')]")));

		List<WebElement> accBox =driver.findElements(By.className("android.widget.TextView"));
		accBox.get(2).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available balance')] | //*[contains(@text,'Saldo tersedia')]"))).isDisplayed();
		driver.findElement(By.className("android.widget.EditText")).sendKeys("500000");;

		driver.findElement(By.xpath("//*[@text='Select time period'] | //*[@text='Pilih jangka waktu']")).click();

		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Available balance')] | //*[contains(@text,'Saldo tersedia')]")));
		driver.findElement(By.xpath("//*[contains(@text,'1 bulan')] | //*[contains(@text,'1 month')]")).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available balance')] | //*[contains(@text,'Saldo tersedia')]"))).isDisplayed();

		driver.swipe(21, 1200, 21, 750, 2000);

		Thread.sleep(500);

		driver.findElement(By.xpath("//*[@text='Automatic roll-over'] | //*[@text='Diperpanjang otomatis']")).click();

		driver.findElement(By.xpath("//*[@text='CONTINUE'] | //*[@text='LANJUTKAN']")).click();

	}

	@Test(dependsOnMethods="Test02_Time_Deposit_Page")
	public void Test03_Auto_Rollover_Time_Deposit_TnC_Page() throws Exception
	{

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@content-desc='Syarat dan Ketentuan Deposito Berjangka Online'] | //*[@content-desc='Online Time Deposit Terms and Conditions']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='I Agree'] | //*[@text='Saya Setuju']")).click();

	}
	@Test(dependsOnMethods="Test03_Auto_Rollover_Time_Deposit_TnC_Page")
	public void Test04_Auto_Rollover_Time_Deposit_Summary_Page() throws Exception
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='SUMMARY'] | //*[@text='Ringkasan']"))).isDisplayed();

		driver.findElement(By.xpath("//*[@text='CREATE TIME DEPOSIT'] | //*[@text='Membuat Deposito Berjangka']")).click();

	}
	
	@Test(dependsOnMethods="Test04_Auto_Rollover_Time_Deposit_Summary_Page")
	public void Test05_Auto_Rollover_Time_Deposit_EasyPin_Page() throws Exception
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).isDisplayed();

		driver.findElement(By.className("android.widget.EditText")).sendKeys("123456");
		
		driver.findElement(By.xpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']")).click();

	}
	@Test(dependsOnMethods="Test05_Auto_Rollover_Time_Deposit_EasyPin_Page")
	public void Test06_Auto_Rollover_Time_Deposit_Result_Page() throws Exception
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Time Deposit creation')] | //*[contains(@text,'Pembuatan Deposito Berjangka')]"))).isDisplayed();
	
		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		driver.findElement(By.xpath("//*[@text='OK']")).click();			

	}
	
	@AfterClass
	public void logout() throws Exception
	{	
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']"))).isDisplayed();
		
		driver.findElement(By.className("android.widget.TextView")).click();
			
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KELUAR'] | //*[@text='LOG OUT']"))).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text ='LOGIN']"))).isDisplayed();

	}

}
