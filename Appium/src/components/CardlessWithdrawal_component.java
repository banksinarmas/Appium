package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class CardlessWithdrawal_component {

	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait30,wait60;
	private FundTransfer_component fundTransfer_comp;
	private ScreenAction screenAction;
	
	public CardlessWithdrawal_component(AndroidDriver<WebElement> driver) {
		
		this.driver=driver;
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
		fundTransfer_comp.selectPayee(phoneNo);
	}
	
	public void selectAccount(String sourceAccount,String amount,String desc) {
		fundTransfer_comp.selectAccount(sourceAccount, amount, desc);
	}
	
	public void summary() {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Summary'] | //*[@text='Ringkasan']")));

		screenAction.scrollUntilElementByXpath("//*[@text='Penarikan'] | //*[@text='Withdrawal']").click();

	}
	
	public void result() {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Cardless Withdrawal')]"))).isDisplayed();
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		driver.findElement(By.xpath("//*[contains(@text,'success')] | //*[contains(@text,'sukses')]")).isDisplayed();
		
		screenAction.scrollUntilElementByXpath("//*[@text='DONE'] | //*[@text='SELESAI']").click();
	}
}
