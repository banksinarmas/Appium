package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	}
	
	public void cardlessWdrMenu() {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']")));	
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.className("android.widget.TextView")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Cardless withdrawal'] | //*[@text='Tarik tunai tanpa kartu']"))).click();
	}
	

	public void selectPhoneNo(String folder,String filename,String phoneNo) {
		fundTransfer_comp.selectPayee(folder,filename,phoneNo);
	}
	
	public void selectAccount(String folder,String filename,String sourceAccount,String amount,String desc) {
		fundTransfer_comp.selectAccount(folder,filename,sourceAccount, amount, desc);
	}
	
	public void summary(String folder,String filename) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Summary'] | //*[@text='Ringkasan']")));

		screenAction.capture(folder, filename);
		screenAction.verticalScroll();
		driver.findElement(By.xpath("//*[@text='Penarikan'] | //*[@text='Withdrawal']")).click();

	}
	
	public void result(String folder,String filename) {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Cardless Withdrawal')]"))).isDisplayed();
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='OK']").click();

	}
}
