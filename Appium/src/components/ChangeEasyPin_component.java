package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class ChangeEasyPin_component {
	
	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait30,wait65;
	private ScreenAction screenAction;
	
	public ChangeEasyPin_component(AndroidDriver<WebElement> driver) {
		this.driver=driver;
		
		wait10=new WebDriverWait(driver, 10);
		wait30=new WebDriverWait(driver, 30);
		wait65=new WebDriverWait(driver, 65);
		
		screenAction = new ScreenAction(driver);	
	}

	public void userProfileMenu() {
		WebElement userProfileMenu=wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PROFIL'] | //*[@text='PROFILE']")));
		wait65.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		userProfileMenu.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElements(By.className("android.widget.TextView")).get(2).click();
		
	}
	
	public void changeEasyPinMenu() {

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).click();
	}
	
	public void validatePassword(String currentPassword) {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Perbarui EasyPIN'] | //*[@text='Update EasyPIN'] "))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='android.widget.EditText']"))).sendKeys(currentPassword);
		
		screenAction.scrollUntilElementByXpath("//*[@text='Konfirmasi'] | //*[@text='Confirm']").click();
		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle"))).isDisplayed();
		driver.findElement(By.id("android:id/button1")).click();			
	}
	

}
