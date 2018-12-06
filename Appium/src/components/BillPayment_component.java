package components;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class BillPayment_component {
	
	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait30,wait60;
	private ScreenAction screenAction;
	
	public BillPayment_component(AndroidDriver<WebElement> driver) {
		
		this.driver=driver;
		
		wait10=new WebDriverWait(driver, 10);
		wait30=new WebDriverWait(driver, 30);
		wait60=new WebDriverWait(driver, 60);
		
		screenAction = new ScreenAction(driver);
		
	}

	public void other_billerMenu(String billerName) {
				
		WebElement billElement = wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PAY'] | //*[@text='BAYAR']")));
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		billElement.click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pay others'] | //*[@text='Bayar lain-lain']"))).click();
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys(billerName);
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'"+billerName+"')]"))).click();	
		
	}
	
	public void waterPayment_selectMerchantSubscriberNo(String folder,String filename,String billerName,String subscriberNo) {

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Select Merchant'] | //*[@text='Pilih merchant']"))).click();
		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@text='BAYAR TAGIHAN BARU'] | //*[@text='PAY A NEW BILL']")));
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'"+billerName+"')]"))).click();

		//input subscriber no
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys(subscriberNo);

		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();
		
	}
	
	public void waterPayment_billerMenu() {
		
		WebElement billElement = wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PAY'] | //*[@text='BAYAR']")));
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		billElement.click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pay water bill'] | //*[@text='Bayar tagihan air']"))).click();
	}
	
	public void inputSubscriberNo(String folder,String filename,String subscriberNo) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PAYMENT/PURCHASE'] | //*[@text='PEMBAYARAN/PEMBELIAN']"))).isDisplayed();
		driver.findElement(By.className("android.widget.EditText")).sendKeys(subscriberNo);

		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();	
	}
	
	public void block1_selectAccount(String folder,String filename,String sourceAccount) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] "))).isDisplayed();

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@text='Please select an account'] | //*[@text='Pilih akun']")).click();;
	
		screenAction.scrollUntilElementByXpath("//*[contains(@text,'"+sourceAccount+"')]").click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));
		
		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();
		
	}
	
	public void block2_selectAccount(String folder,String filename,String sourceAccount,String amount,String desc) {
		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] "))).isDisplayed();

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//select account
		driver.findElement(By.xpath("//*[@text='Select account'] | //*[@text='Pilih akun']")).click();
		screenAction.scrollUntilElementByXpath("//*[contains(@text,'"+sourceAccount+"')]").click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));
		List<WebElement> inputFields =driver.findElements(By.className("android.widget.EditText"));
		//input amount
		inputFields.get(0).sendKeys(amount);
		//input desc
		inputFields.get(1).sendKeys(desc);
		
		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();		
	}
	
	public void block3_selectAccount(String folder,String filename,String sourceAccount,String amount,String desc) {

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));
		
		//select account
		driver.findElement(By.xpath("//*[@text='Select account'] | //*[@text='Pilih akun']")).click();
		screenAction.scrollUntilElementByXpath("//*[contains(@text,'"+sourceAccount+"')]").click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));
		
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//select bill period
		driver.findElements(By.xpath("//*[@text='Bill Period'] | //*[@text='Periode tagihan']")).get(1).click();
		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElements(By.className("android.widget.TextView")).get(new Random().nextInt(3)+1).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));
	
		List<WebElement> inputFields =driver.findElements(By.className("android.widget.EditText"));
		//input amount
		inputFields.get(0).sendKeys(amount);
		//input desc
		inputFields.get(1).sendKeys(desc);
		
		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();		
		
	}
	
	public void summary(String folder,String filename) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PAYMENT SUMMARY'] | //*[@text='RINGKASAN PEMBAYARAN'] | //*[@text='Ringkasan pembelian']"))).isDisplayed();
		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
	
		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='CONFIRM PAYMENT'] | //*[@text='KONFIRMASI PEMBAYARAN']").click();		
	}
	
	public void result(String folder,String filename) {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'payment')] | //*[contains(@text,'Payment')] //*[contains(@text,'Pembayaran')] | //*[contains(@text,'pembayaran')]"))).isDisplayed();
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		
		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementInvisibleByXpath("//*[contains(@text,'MB-')]");
		screenAction.capture(folder, filename+"_"+1);
		
		screenAction.scrollUntilElementByXpath("//*[@text='DONE'] | //*[@text='SELESAI']").click();

	}
}
