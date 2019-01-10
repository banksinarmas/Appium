package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class CardlessWithdrawal_component {

	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait30,wait60;
	private FundTransfer_component fundTransfer_comp;
	private ScreenAction screenAction;
	
	public CardlessWithdrawal_component(AndroidDriver<WebElement> driver) {
		
		this.driver=driver;
		wait10=new WebDriverWait(driver, 10);
		wait30=new WebDriverWait(driver, 30);
		wait60=new WebDriverWait(driver, 60);
		fundTransfer_comp=new FundTransfer_component(driver);
		screenAction=new ScreenAction(driver);
	}
	
	public void cardlessWdrMenu() {
		WebElement cardlessElement = wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='TARIK'] | //*[@text='CASH']")));	
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cardlessElement.click();
	}
	

	public void selectPhoneNo(String phoneNo) {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).isDisplayed();
		driver.findElements(By.className("android.widget.EditText")).get(1).sendKeys(phoneNo);
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+phoneNo+"']")).click();

	}
	
	public void selectAccount(String sourceAccount,String amount,String desc) {
		fundTransfer_comp.selectAccount(sourceAccount, amount, desc);
	}
	
	public void summary() {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'confirmation')] | //*[contains(@text,'Konfirmasi')]"))).isDisplayed();
		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		screenAction.scrollUntilElementByXpath("//*[@text='WITHDRAWAL'] | //*[@text='PENARIKAN']").click();

	}
	
	public void result(String fromAccountType) {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Cardless Withdrawal')]"))).isDisplayed();
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		//driver.findElement(By.xpath("//*[contains(@text,'success')] | //*[contains(@text,'sukses')]")).isDisplayed();
		
		if(fromAccountType.contains("NORMAL"))
			Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@text,'success')] | //*[contains(@text,'sukses')] ")).isDisplayed(), true);
		else 
			Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@text,'failed')] | //*[contains(@text,'gagal')] ")).isDisplayed(), true);

		screenAction.scrollUntilElementByXpath("//*[@text='DONE'] | //*[@text='SELESAI']").click();
	}
}
