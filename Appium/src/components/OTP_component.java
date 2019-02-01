package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class OTP_component {
	
	private WebDriverWait wait10,wait30;
	private AndroidDriver<WebElement> driver;
	private static final String OTP_NUMBER="123456";
	private ScreenAction screenAction;

	public OTP_component(AndroidDriver<WebElement> driver) {	
		this.driver=driver;
		wait10= new WebDriverWait(driver,10);
		wait30= new WebDriverWait(driver,30);
		
		screenAction=new ScreenAction(driver);
	}
	
	public void inputOTP() throws Exception
	{
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'SMS')]"))).isDisplayed();
		
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys(OTP_NUMBER);
		
		screenAction.verticalScroll();
		
		driver.findElement(By.xpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']")).click();
	}
}
