package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class ChangeEasyPin_component {
	
	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait30,wait60;
	private ScreenAction screenAction;
	
	public ChangeEasyPin_component(AndroidDriver<WebElement> driver) {
		this.driver=driver;
		
		wait10=new WebDriverWait(driver, 10);
		wait30=new WebDriverWait(driver, 30);
		wait60=new WebDriverWait(driver, 60);
		
		screenAction = new ScreenAction(driver);	
	}

	public void changeEasyPinMenu() {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']"))).isDisplayed();
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		driver.findElement(By.className("android.widget.TextView")).click();
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Update EasyPIN'] | //*[@text='Ubah EasyPIN']"))).click();
	}
	
	public void validatePassword(String folder,String filename,String currentPassword) {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Perbarui EasyPIN'] | //*[@text='Update EasyPIN'] "))).sendKeys(currentPassword);
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='android.widget.EditText']"))).sendKeys(currentPassword);
		
		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='Konfirmasi'] | //*[@text='Confirm']").click();
		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle"))).isDisplayed();
		driver.findElement(By.id("android:id/button1")).click();			
	}
	
	public void createNewEasyPin(String folder,String filename,String newEasyPin) throws Exception
	{
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Buat EasyPIN baru'] | //*[@text='Enter new EasyPIN']"))).isDisplayed();
		driver.findElement(By.className("android.widget.EditText")).sendKeys(newEasyPin);
	
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Konfirmasi EasyPIN'] | //*[@text='Confirm new EasyPIN']"))).isDisplayed();
		driver.findElement(By.className("android.widget.EditText")).sendKeys(newEasyPin);
		
		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']").click();

	}
}
