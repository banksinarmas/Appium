package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class ChangePassword_component {
	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait30,wait65;
	private ScreenAction screenAction;
	
	public ChangePassword_component(AndroidDriver<WebElement> driver) {
		this.driver=driver;
		
		wait10=new WebDriverWait(driver, 10);
		wait30=new WebDriverWait(driver, 30);
		wait65=new WebDriverWait(driver, 65);
		
		screenAction = new ScreenAction(driver);	
	}

	public void settingsMenu() {
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']")));
		wait65.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		driver.findElement(By.className("android.widget.TextView")).click();
		
		screenAction.scrollUntilElementByXpath("//*[@text='Settings'] | //*[@text='Pengaturan']").click();
		
	}
	
	public void changePasswordMenu() {

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Change Password']"))).click();
		//driver.findElement(By.xpath("//*[@text="Change Password"]"))
		//driver.findElement(By.xpath("//*[@text='Change Password']")).click();
		
	}
	
	public void changePassword(String currentPassword) {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Enter current password'] | //*[@text='Masukkan kata sandi terakhir']"))).isDisplayed();
		
		List<WebElement> listInput= driver.findElements(By.className("android.widget.EditText"));
		
		listInput.get(0).sendKeys(currentPassword);
		//listInput.get(1).sendKeys(newEasyPin);
		//listInput.get(2).sendKeys(newEasyPin);
				
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='Next']").click();
	
		}
		public void NewPassword(String newPassword)
		{
			wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Rubah kata sandi'] | //*[@text='Change Password'] "))).isDisplayed();
			
			List<WebElement> listInput= driver.findElements(By.className("android.widget.EditText"));
		
		//listInput.get(0).sendKeys(currentPassword);
		listInput.get(0).sendKeys(newPassword);
		listInput.get(1).sendKeys(newPassword);
				
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='Next']").click();
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle"))).isDisplayed();
		driver.findElement(By.id("android:id/button1")).click();
		
		wait30.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']")));
	}

}
