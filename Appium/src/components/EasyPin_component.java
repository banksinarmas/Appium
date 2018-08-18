package components;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.DeviceCapabilities;

public class EasyPin_component extends DeviceCapabilities{

	public static void input(String easyPin) throws Exception
	{
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).isDisplayed();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys(easyPin);

		//driver.findElement(By.className("android.widget.EditText")).sendKeys(easyPin);

		driver.findElement(By.xpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']")).click();

	}
	

	
}
