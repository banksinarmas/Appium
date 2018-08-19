package components;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.DeviceCapabilities;

public class BillPayment_component extends DeviceCapabilities {

	public static void other_billerMenu(String billerName) {
				
		WebElement billElement = wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PAY'] | //*[@text='BAYAR']")));
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		billElement.click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pay others'] | //*[@text='Bayar lain-lain']"))).click();

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys(billerName);

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[contains(@text,'"+billerName+"')]"))).click();	
		
	}
	
	public static void waterPayment_selectMerchantSubscriberNo(String billerName,String subscriberNo) {

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Select Merchant'] | //*[@text='Pilih merchant']"))).click();

		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@text='BAYAR TAGIHAN BARU'] | //*[@text='PAY A NEW BILL']")));

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'"+billerName+"')]"))).click();
		
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys(subscriberNo);

		driver.findElement(By.xpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']")).click();
		
	}
	
	public static void waterPayment_billerMenu() {
		
		WebElement billElement = wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PAY'] | //*[@text='BAYAR']")));
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		billElement.click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pay water bill'] | //*[@text='Bayar tagihan air']"))).click();
		
	}
	
	public static void inputSubscriberNo(String subscriberNo) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PAYMENT/PURCHASE'] | //*[@text='PEMBAYARAN/PEMBELIAN']"))).isDisplayed();

		driver.findElement(By.className("android.widget.EditText")).sendKeys(subscriberNo);

		driver.findElement(By.xpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']")).click();
		
	}

	
	public static void block1_selectAccount(String sourceAccount) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] "))).isDisplayed();

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@text='Please select an account'] | //*[@text='Pilih akun']")).click();;
		
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'"+sourceAccount+"')]"))).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));
		
		driver.findElement(By.xpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']")).click();
		
	}
	
	public static void block2_selectAccount(String sourceAccount,String amount,String desc) {
		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] "))).isDisplayed();

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@text='Select account'] | //*[@text='Pilih akun']")).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'"+sourceAccount+"')]"))).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));

		List<WebElement> inputFields =driver.findElements(By.className("android.widget.EditText"));
		
		inputFields.get(0).sendKeys(amount);

		inputFields.get(1).sendKeys(desc);
		
		driver.findElement(By.xpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']")).click();
		
	}
	
	public static void block3_selectAccount(String sourceAccount,String amount,String desc) {

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));
		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@text='Select account'] | //*[@text='Pilih akun']")).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'"+sourceAccount+"')]"))).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));

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
		inputFields.get(0).sendKeys(amount);
		inputFields.get(1).sendKeys(desc);
		
		driver.findElement(By.xpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']")).click();
		
	}
	
	public static void summary() {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PAYMENT SUMMARY'] | //*[@text='RINGKASAN PEMBAYARAN'] | //*[@text='Ringkasan pembelian']"))).isDisplayed();

		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='CONFIRM PAYMENT'] | //*[@text='KONFIRMASI PEMBAYARAN']"))).click();
		
	}
	
	public static void result() {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Payment')] | //*[contains(@text,'Pembayaran')]"))).isDisplayed();

		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		List<WebElement> listWE = driver.findElements(By.xpath("//*[@text='OK']"));
		while (!(listWE.size()>0)){
			driver.swipe(50, 1500, 50, 200, 3000);
			listWE  = driver.findElements(By.xpath("//*[@text='OK']"));
		}

		if(listWE.size()>0)listWE.get(0).click();
		
	}
}
