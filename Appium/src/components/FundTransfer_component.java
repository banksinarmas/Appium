package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.DeviceCapabilities;

public class FundTransfer_component extends DeviceCapabilities {

	public static void fundTransferMenu() {

		WebElement ftElement = wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='SEND'] | //*[@text='KIRIM']")));

		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		try {
			Thread.sleep(1000);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ftElement.click();

	}	
	public static void selectPayee(String toAccount) {

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys(toAccount);
		
		driver.findElement(By.xpath("//*[@text='CONTINUE'] | //*[@text='LANJUT']")).click();
	}

	public static void selectAccount(String sourceAccount,String amount,String desc) {

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@text='Select your account'] | //*[@text='Pilih akun']")).click();

	//	wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Select your account'] | //*[@text='Pilih akun']"))).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'"+sourceAccount+"')]"))).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));

		List<WebElement> inputFields =driver.findElements(By.className("android.widget.EditText"));

		inputFields.get(0).sendKeys(amount);

		inputFields.get(1).sendKeys(desc);

		driver.findElement(By.xpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']")).click();
	}
	public static void selectAccountSchedule(String sourceAccount,String amount,String desc) {

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath("//*[@text='Select your account'] | //*[@text='Pilih akun']")).click();
		
	//	wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Select your account'] | //*[@text='Pilih akun']"))).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'"+sourceAccount+"')]"))).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));

		List<WebElement> inputFields =driver.findElements(By.className("android.widget.EditText"));

		inputFields.get(0).sendKeys(amount);

		inputFields.get(1).sendKeys(desc);

		driver.findElement(By.xpath("//*[@text='Schedule transfer'] | //*[@text='Jadwalkan transfer']")).click();

		driver.findElement(By.xpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']")).click();
	}

	public static void selectSchedule(String recurrence,String frequency) throws InterruptedException {

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Schedule your transfer'] | //*[@text='Tentukan tanggal transfer']")));

		driver.findElement(By.xpath("//*[@text='Start date'] | //*[@text='Tanggal mulai']")).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button1"))).click();

		List<WebElement> addFreq = driver.findElements(By.xpath("//*[@text='+']"));
		
		recurrence= recurrence.toLowerCase();
		
		class SchFrequency{
			void set(int position){
				List <WebElement> listFreq = driver.findElements(By.xpath("//*[@text='"+frequency+"']"));
				while(!(listFreq.size()>0)) {
					addFreq.get(position).click();	
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					listFreq = driver.findElements(By.xpath("//*[@text='"+frequency+"']"));
				}	
			}
		}
		
		if(recurrence.equals("one time")) {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='One time'] | //*[@text='Tidak diulang']"))).click();
	
		}
		else if(recurrence.equals("everyday")) {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Everyday'] | //*[@text='Setiap hari']"))).click();
		new SchFrequency().set(0);
		
		}
		else if(recurrence.equals("once a week")) {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Once a week'] | //*[@text='Seminggu sekali']"))).click();
		new SchFrequency().set(1);
			
		}
		else if(recurrence.equals("once every two weeks")) {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Once every two weeks'] | //*[@text='2 Minggu sekali']"))).click();
		new SchFrequency().set(2);
			
		}
		else if(recurrence.equals("once a month")) {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Once a month'] | //*[@text='Sebulan sekali']"))).click();
		new SchFrequency().set(3);
			
		}	
	
		driver.swipe(50, 1500, 50, 200, 3000);
		driver.findElement(By.xpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']")).click();
	}

	public static void selectTransferMethod(String method) throws InterruptedException {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'method')] | //*[contains(@text,'metode')]")));

		method=method.toLowerCase();
		if(method.equals("skn")) {
			driver.findElement(By.xpath("//*[contains(@text,'SKN')] | //*[contains(@text,'skn')]")).click();
		}
		else if(method.equals("network")){
			driver.findElement(By.xpath("//*[contains(@text,'network')]")).click();
		}	
		else {
			driver.findElement(By.xpath("//*[contains(@text,'rtgs')]")).click();
		}
		
		driver.swipe(50, 1500, 50, 200, 3000);

		driver.findElement(By.xpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']")).click();
	}

	public static void summary() {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='TRANSFER SUMMARY'] | //*[@text='Ringkasan Transfer']"))).isDisplayed();
		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='TRANSFER'] | //*[@text='PEMINDAHAN DANA']"))).click();

	}


	public static void summarySchedule() {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Transfer Confirmation'] | //*[@text='Ringkasan Transfer']")));
		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		driver.swipe(50, 1500, 50, 200, 3000);
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='TRANSFER'] | //*[@text='PEMINDAHAN DANA']"))).click();

	}

	public static void result() {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Transfer')]"))).isDisplayed();

		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		List<WebElement> result = driver.findElements(By.xpath("//*[@text='OK']"));
		while (!(result.size()>0)){
			driver.swipe(50, 1500, 50, 200, 3000);
			result  = driver.findElements(By.xpath("//*[@text='OK']"));
		}

		if(result.size()>0)result.get(0).click();

	}

}
