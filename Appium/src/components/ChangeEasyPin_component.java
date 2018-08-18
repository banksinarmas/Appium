package components;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.DeviceCapabilities;

public class ChangeEasyPin_component extends DeviceCapabilities {

	public static void changeEasyPinMenu() {
		
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']"))).isDisplayed();

		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		driver.findElement(By.className("android.widget.TextView")).click();
		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Update EasyPIN'] | //*[@text='Ubah EasyPIN']"))).click();
	
	}	
	public static void validatePassword(String currentPassword) {
		
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Enter current password'] | //*[@text='Masukkan kata sandi terakhir'] "))).sendKeys("Ab123456");
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='android.widget.EditText']"))).sendKeys(currentPassword);
		driver.findElement(By.xpath("//*[@text='Konfirmasi'] | //*[@text='Confirm']")).click();

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle"))).isDisplayed();
		driver.findElement(By.id("android:id/button1")).click();
				
	}
	
	public static void createNew(String easyPin) throws Exception
	{
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Buat EasyPIN baru'] | //*[@text='Enter new EasyPIN']"))).isDisplayed();

		driver.findElement(By.className("android.widget.EditText")).sendKeys("123456");

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Konfirmasi EasyPIN'] | //*[@text='Confirm new EasyPIN']"))).isDisplayed();

		driver.findElement(By.className("android.widget.EditText")).sendKeys("123456");

		driver.findElement(By.xpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']")).click();

	}
}
