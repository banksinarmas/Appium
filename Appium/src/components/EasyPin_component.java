package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class EasyPin_component {

	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait15,wait30,wait65;
	private ScreenAction screenAction;
	
	public EasyPin_component(AndroidDriver<WebElement> driver) {
		this.driver=driver;

		wait10 = new WebDriverWait(driver,10);
		wait15 = new WebDriverWait(driver,15);
		wait30 = new WebDriverWait(driver,30);
		wait65 = new WebDriverWait(driver,65);
		
		screenAction=new ScreenAction(driver);
	}	
	
	public void loginEasyPin(String easyPin) {
		
		try {
			wait15.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text=\"Don't Show This Again\"] | //*[@text='Jangan Tampilkan Lagi']"))).click();
			driver.findElement(By.xpath("//*[@text='OK']")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text ='LOG IN']"))).click();		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).isDisplayed();
		driver.findElementByClassName("android.widget.EditText").sendKeys(easyPin);	

		screenAction.scrollUntilElementByXpath("//*[@text='CONTINUE'] | //*[@text='LANJUTKAN']").click();
	}
	
	public void inputEasyPin(String easyPin) throws Exception
	{
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys(easyPin);
	
		screenAction.scrollUntilElementByXpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']").click();

	}
	public void createEasyPin(String easyPin) throws Exception
	{

		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'EasyPIN')]"))).isDisplayed();
		driver.findElement(By.className("android.widget.EditText")).sendKeys("123456");
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Konfirmasi')] | //*[contains(@text,'Confirm')]"))).isDisplayed();
		driver.findElement(By.className("android.widget.EditText")).sendKeys("123456");
		
		screenAction.scrollUntilElementByXpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']").click();

	}
	
}
