package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class EasyPin_component {

	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait5,wait10,wait20,wait30,wait60;
	private ScreenAction screenAction;
	
	public EasyPin_component(AndroidDriver<WebElement> driver) {
		this.driver=driver;
		
		wait5 = new WebDriverWait(driver,5);
		wait10 = new WebDriverWait(driver,10);
		wait20 = new WebDriverWait(driver,20);
		wait30 = new WebDriverWait(driver,30);
		wait60 = new WebDriverWait(driver,60);
		
		screenAction=new ScreenAction(driver);
	}	
	
	public void loginEasyPin(String easyPin) {
		
		try {
			wait20.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button2"))).click();

		} catch (Exception e) {
			// TODO: handle exception
		}	
		try {
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text=\"Don't Show This Again\"] | //*[@text='Jangan Tampilkan Lagi']"))).click();
			driver.findElement(By.xpath("//*[@text='OK']")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text ='LOG IN']"))).click();		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).isDisplayed();
		driver.findElementByClassName("android.widget.EditText").sendKeys(easyPin);	
	
		screenAction.scrollUntilElementByXpath("//*[@text='CONTINUE'] | //*[@text='LANJUTKAN']").click();
	}
	
	public void input(String folder,String filename,String easyPin) throws Exception
	{
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys(easyPin);
		screenAction.capture(folder, filename);
	
		screenAction.scrollUntilElementByXpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']").click();


	}
	
}
