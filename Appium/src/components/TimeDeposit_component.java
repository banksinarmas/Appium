package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;


public class TimeDeposit_component {

	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait20,wait60;
	private ScreenAction screenAction;

	public TimeDeposit_component(AndroidDriver<WebElement> driver) {

		this.driver=driver;
		wait10=new WebDriverWait(driver,10);
		wait20=new WebDriverWait(driver,20);
		wait60=new WebDriverWait(driver,60);
		screenAction = new ScreenAction(driver);

	}

	public void timeDepositMenu() {
		WebElement tdMenu = wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@content-desc='Time Deposit'] | //*[@content-desc='Deposito Berjangka']")));
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		tdMenu.click();

		screenAction.scrollUntilElementByXpath("//*[@text='OPEN NEW TIME DEPOSIT'] | //*[@text='DEPOSITO BERJANGKA BARU']").click();

	}

	public void createTimeDepositOnline(String folder,String filename,String sourceAccount,String amount,String term,String tdType) throws InterruptedException {

		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available balance')] | //*[contains(@text,'Saldo tersedia')] ")));

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> selectAccBox=driver.findElements(By.xpath("//*[@text='Select account'] | //*[@text='Pilih akun']"));
		selectAccBox.get(selectAccBox.size()-1).click();

		//input amount
		screenAction.scrollUntilElementByXpath("//*[contains(@text,'"+sourceAccount+"')]").click();
		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available balance')] | //*[contains(@text,'Saldo tersedia')] ")));
		driver.findElement(By.className("android.widget.EditText")).sendKeys(amount);
		
		//input period
		driver.findElement(By.xpath("//*[@text='Select time period'] | //*[@text='Pilih jangka waktu']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'"+term+" bulan')] | //*[contains(@text,'"+term+" month')]"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available balance')] | //*[contains(@text,'Saldo tersedia')]"))).isDisplayed();

		//scroll until tdType option become visible
		screenAction.scrollUntilElementByXpath("//*[@text='Automatic roll-over (+interest)'] | //*[@text='Diperpanjang otomatis (+bunga)']");
		//input td type
		if(tdType.equals("aro")) {
			driver.findElement(By.xpath("//*[@text='Automatic roll-over'] | //*[@text='Diperpanjang otomatis']")).click();
		}
		else if(tdType.equals("non-aro")) {
			driver.findElement(By.xpath("//*[@text='Non automatic roll-over'] | //*[@text='Tidak diperpanjang otomatis']")).click();
		}
		else if(tdType.equals("aro-pi")) {
			driver.findElement(By.xpath("//*[@text='Automatic roll-over (+interest)'] | //*[@text='Diperpanjang otomatis (+bunga)']")).click();
		}
	
		Thread.sleep(1000);
		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='CONTINUE'] | //*[@text='LANJUTKAN']").click();

	}
	public void termAndCondition(String folder,String filename) {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Terms and conditions'] | //*[@text='Syarat dan ketentuan']"))).isDisplayed();
		
		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='I AGREE'] | //*[@text='SAYA SETUJU']").click();

	}

	public void summary(String folder,String filename) {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='SUMMARY'] | //*[@text='Ringkasan']"))).isDisplayed();
		
		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='CREATE TIME DEPOSIT'] | //*[@text='Membuat Deposito Berjangka']").click();
		
	}

	public void result(String folder,String filename) {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Time Deposit creation')] | //*[contains(@text,'Pembuatan Deposito Berjangka')]"))).isDisplayed();
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		screenAction.capture(folder, filename);
		List<WebElement> checkSuccess = driver.findElements(By.xpath("//*[contains(@text,'success')] | //*[contains(@text,'sukses')]"));
		if(checkSuccess.size()>0) {		
			
			screenAction.scrollUntilElementInvisibleByXpath("//*[contains(@text,'success')] | //*[contains(@text,'sukses')]");
			screenAction.capture(folder, filename+"_"+1);
		}
		
		screenAction.scrollUntilElementByXpath("//*[@text='DONE'] | //*[@text='SELESAI']").click();

	}
}
