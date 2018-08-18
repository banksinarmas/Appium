package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.DeviceCapabilities;

public class Onboarding_component extends DeviceCapabilities{

	public static void loginEasyPin(String easyPin) {
		
		try {
			wait20.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button2"))).click();

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text=\"Don't Show This Again\"]"))).click();
			driver.findElement(By.xpath("//*[@text='OK']")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text ='LOGIN']"))).click();		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).isDisplayed();
		driver.findElementByClassName("android.widget.EditText").sendKeys(easyPin);

		driver.findElement(By.xpath("//*[@text='CONTINUE'] | //*[@text='LANJUTKAN']")).click();
	}
	public static void landingPage() {
		
		try {
			wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='ALLOW']"))).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			wait10.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button2"))).click();

		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text=\"Don't Show This Again\"] | //*[@text='Jangan Tampilkan Lagi']"))).click();
			driver.findElement(By.xpath("//*[@text='OK']")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='CONTINUE'] | //*[@text='LANJUTKAN']"))).click();
		
	}
	
	public static void loginUsernamePassword(String username,String password) {
		
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Selamat Datang!'] | //*[@text='Welcome!']"))).isDisplayed();

		List<WebElement> credentials = driver.findElements(By.className("android.widget.EditText"));
		credentials.get(0).sendKeys(username);
		credentials.get(1).sendKeys(password);
		
		driver.findElement(By.xpath("//*[@text='LOGIN']")).click();
		
	}
	
	public static void createEasyPin(String easyPin) throws Exception
	{
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Create EasyPIN'] | //*[@text='Buat EasyPIN']"))).isDisplayed();

		driver.findElement(By.className("android.widget.EditText")).sendKeys("123456");

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Konfirmasi EasyPIN'] | //*[@text='Confirm EasyPIN']"))).isDisplayed();

		driver.findElement(By.className("android.widget.EditText")).sendKeys("123456");

		driver.findElement(By.xpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']")).click();

	}
	public static void dashboardFreshDevice() {
		
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='OK']"))).click();
	
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']"))).isDisplayed();

		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		
	}
	
	public static void logout() throws Exception
	{	
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']"))).isDisplayed();

		try {
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Menyerahkan'] | //*[@text='Submit']"))).click();	

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		driver.findElement(By.className("android.widget.TextView")).click();

		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KELUAR'] | //*[@text='LOG OUT']"))).click();

		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text ='LOGIN']"))).isDisplayed();

	}
}
