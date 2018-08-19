package components;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.DeviceCapabilities;

public class EasyPin_component extends DeviceCapabilities{

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
	
	public static void input(String easyPin) throws Exception
	{
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).isDisplayed();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys(easyPin);

		driver.findElement(By.xpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']")).click();

	}
	

	
}
