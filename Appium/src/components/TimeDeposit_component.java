package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.DeviceCapabilities;

public class TimeDeposit_component extends DeviceCapabilities{

	public static void timeDepositMenu() {
		WebElement tdMenu = wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@content-desc='Time Deposit'] | //*[@content-desc='Deposito Berjangka']")));
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		tdMenu.click();

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Time Deposit Information'] | //*[@text='Informasi Deposito Berjangka']"))).isDisplayed();

		List<WebElement> listWE = driver.findElements(By.xpath("//*[@text='CREATE TIME DEPOSIT'] | //*[@text='Deposito Berjangka Baru']"));
		while (!(listWE.size()>0)){
			driver.swipe(50, 1500, 50, 200, 3000);
			listWE  = driver.findElements(By.xpath("//*[@text='CREATE TIME DEPOSIT'] | //*[@text='Deposito Berjangka Baru']"));
		}

		if(listWE.size()>0)listWE.get(0).click();

	}

	public static void createTimeDepositOnline(String sourceAccount,String amount,String term,String tdType) {

		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available balance')] | //*[contains(@text,'Saldo tersedia')] ")));

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> selectAccBox=driver.findElements(By.xpath("//*[@text='Select account'] | //*[@text='Pilih akun']"));
		selectAccBox.get(selectAccBox.size()-1).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'"+sourceAccount+"')]"))).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available balance')] | //*[contains(@text,'Saldo tersedia')] ")));

		driver.findElement(By.className("android.widget.EditText")).sendKeys(amount);

		driver.findElement(By.xpath("//*[@text='Select time period'] | //*[@text='Pilih jangka waktu']")).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'"+term+" bulan')] | //*[contains(@text,'"+term+" month')]"))).click();
		
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available balance')] | //*[contains(@text,'Saldo tersedia')]"))).isDisplayed();

		if(tdType.equals("aro")) {
			driver.findElement(By.xpath("//*[@text='Automatic roll-over'] | //*[@text='Diperpanjang otomatis']")).click();
		}
		else if(tdType.equals("non-aro")) {
			driver.findElement(By.xpath("//*[@text='Non automatic roll-over'] | //*[@text='Tidak diperpanjang otomatis']")).click();
		}
		else if(tdType.equals("aro-pi")) {
			driver.findElement(By.xpath("//*[@text='Automatic roll-over (+interest)'] | //*[@text='Diperpanjang otomatis (+bunga)']")).click();
		}
		
		driver.swipe(50, 1500, 50, 200, 3000);
		driver.findElement(By.xpath("//*[@text='CONTINUE'] | //*[@text='LANJUTKAN']")).click();


	}
	public static void termAndCondition() {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Terms and conditions'] | //*[@text='Syarat dan ketentuan']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='I AGREE'] | //*[@text='SAYA SETUJU']")).click();

	}

	public static void summary() {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='SUMMARY'] | //*[@text='Ringkasan']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='CREATE TIME DEPOSIT'] | //*[@text='Membuat Deposito Berjangka']")).click();

	}

	public static void result() {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Time Deposit creation')] | //*[contains(@text,'Pembuatan Deposito Berjangka')]"))).isDisplayed();

		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='OK']"))).click();
		//driver.findElement(By.xpath("//*[@text='OK']")).click();

	}
}
