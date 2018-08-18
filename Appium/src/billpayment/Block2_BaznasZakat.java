package billpayment;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class Block2_BaznasZakat {

	private static AndroidDriver<WebElement> driver;
	private static  WebDriverWait wait5,wait10,wait20,wait30,wait60;

	//AppiumManager apm = new AppiumManager();

	@BeforeClass
	public void Setup() throws Exception 
	{

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","Emulator");
		capabilities.setCapability("udid","emulator-5554");
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("noReset", true);
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

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text ='LOGIN']"))).click();		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).isDisplayed();
		driver.findElementByClassName("android.widget.EditText").sendKeys("123456");
		driver.findElement(By.xpath("//*[@text='CONTINUE'] | //*[@text='LANJUTKAN']")).click();

	}

	@Test(dependsOnMethods="Test00_Login_Page")
	public void Test01_After_Login_Page() throws Exception
	{
		
		WebElement billElement = wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PAY'] | //*[@text='BAYAR']")));
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		billElement.click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pay others'] | //*[@text='Bayar lain-lain']"))).click();

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys("baznas");

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Baznas Zakat']"))).click();

	}

	@Test(dependsOnMethods="Test01_After_Login_Page")
	public void Test02_Inquiry_Page() throws Exception
	{		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PAYMENT/PURCHASE'] | //*[@text='PEMBAYARAN/PEMBELIAN']"))).isDisplayed();

		driver.findElement(By.className("android.widget.EditText")).sendKeys("12345");;

		driver.findElement(By.xpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']")).click();

	}

	@Test(dependsOnMethods="Test02_Inquiry_Page")
	public void Test03_Select_Account_Page() throws Exception
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Select account'] | //*[@text='Pilih akun']"))).click();

		Thread.sleep(2000);
		List<WebElement> accBox =driver.findElements(By.className("android.widget.TextView"));
		accBox.get(2).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));

		List<WebElement> inputFields =driver.findElements(By.className("android.widget.EditText"));
		
		inputFields.get(0).sendKeys("1000");

		inputFields.get(1).sendKeys("test");
		
		driver.findElement(By.xpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']")).click();
	}

	@Test(dependsOnMethods="Test03_Select_Account_Page")
	public void Test04_Summary_Page() throws Exception
	{
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PAYMENT SUMMARY'] | //*[@text='RINGKASAN PEMBAYARAN']")));

		driver.findElement(By.xpath("//*[@text='CONFIRM PAYMENT'] | //*[@text='KONFIRMASI PEMBAYARAN']")).click();

	}

	@Test(dependsOnMethods="Test04_Summary_Page")
	public void Test05_Block2_Baznas_Zakat_EasyPin_Page() throws Exception
	{
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).isDisplayed();

		driver.findElement(By.className("android.widget.EditText")).sendKeys("123456");

		driver.findElement(By.xpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']")).click();

	}
	@Test(dependsOnMethods="Test05_Block2_Baznas_Zakat_EasyPin_Page")
	public void Test06_Block2_Baznas_Zakat_Result_Page() throws Exception
	{
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Payment')] | //*[contains(@text,'Pembayaran')]"))).isDisplayed();

		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		//driver.findElement(By.xpath("//*[@text='OK']")).click();

		List<WebElement> listWE = driver.findElements(By.xpath("//*[@text='OK']"));
		while (!(listWE.size()>0)){
			driver.swipe(50, 1500, 50, 200, 3000);
			listWE  = driver.findElements(By.xpath("//*[@text='OK']"));
		}

		if(listWE.size()>0)listWE.get(0).click();
	}
	
	
	@AfterSuite
	public void logout() throws Exception
	{	
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']"))).isDisplayed();

		try {
			WebElement feedBox=	wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'feedback')] | //*[contains(@text,'saran')]")));
			if(feedBox.isDisplayed()) {
				feedBox.click();
				Thread.sleep(2000);
				}

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		driver.findElement(By.className("android.widget.TextView")).click();

		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KELUAR'] | //*[@text='LOG OUT']"))).click();

		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text ='LOGIN']"))).isDisplayed();

	}

}
