package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class FundTransfer_component  {

	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait30,wait60;
	private ScreenAction screenAction;

	public FundTransfer_component(AndroidDriver<WebElement> driver) {

		this.driver=driver;
		wait10 = new WebDriverWait(driver,10);
		wait30 = new WebDriverWait(driver,30);
		wait60 = new WebDriverWait(driver,60);

		screenAction=new ScreenAction(driver);
	}

	public void fundTransferMenu() {
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

	public void selectPayee(String toAccount) {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys(toAccount);

		//Click next icon in send menu
		driver.findElements(By.className("android.widget.TextView")).get(3).click();
	}

	public void selectAccount(String sourceAccount,String amount,String desc) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'amount')] | //*[contains(@text,'jumlah')] ")));

		List<WebElement> inputFields =driver.findElements(By.className("android.widget.EditText"));
		//input amount
		inputFields.get(0).sendKeys(amount);

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//select account
		driver.findElements(By.xpath("//*[@text='Select source account'] | //*[@text='Pilih rekening sumber']")).get(1).click();
		screenAction.scrollUntilElementByXpath("//*[@text='"+sourceAccount+"']").click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'amount')] | //*[contains(@text,'jumlah')] ")));

		//input desc
		inputFields.get(1).sendKeys(desc);

		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();

	}
	public void selectAccountSchedule(String sourceAccount,String amount,String desc) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'amount')] | //*[contains(@text,'jumlah')] ")));

		List<WebElement> inputFields =driver.findElements(By.className("android.widget.EditText"));
		//input amount
		inputFields.get(0).sendKeys(amount);

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//select account
		driver.findElements(By.xpath("//*[@text='Select source account'] | //*[@text='Pilih rekening sumber']")).get(1).click();
		screenAction.scrollUntilElementByXpath("//*[@text='"+sourceAccount+"']").click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'amount')] | //*[contains(@text,'jumlah')] ")));

		//input desc
		inputFields.get(1).sendKeys(desc);
		//tick schedule transfer box
		driver.findElement(By.xpath("//*[@text='Schedule transfer'] | //*[@text='Jadwalkan transfer']")).click();

		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();

	}

	public void selectSchedule(String recurrence,String frequency) throws InterruptedException {

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Start date'] | //*[@text='Tanggal mulai']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button1"))).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Period Time'] | //*[@text='Periode Waktu']"))).click();
		Thread.sleep(1000);

		recurrence= recurrence.toLowerCase();
		if(recurrence.equals("one time")) {
			driver.findElement(By.xpath("//*[@text='One time'] | //*[@text='Tidak diulang']")).click();
		}
		else {
			if(recurrence.equals("everyday")) {	
				driver.findElement(By.xpath("//*[@text='Everyday'] | //*[@text='Setiap hari']")).click();

			}
			else if(recurrence.equals("once a week")) {
				driver.findElement(By.xpath("//*[@text='Once a week'] | //*[@text='Seminggu sekali']")).click();

			}
			else if(recurrence.equals("once every two weeks")) {
				driver.findElement(By.xpath("//*[@text='Once every two weeks'] | //*[@text='2 Minggu sekali']")).click();

			}
			else if(recurrence.equals("once a month")) {		
				driver.findElement(By.xpath("//*[@text='Once a month'] | //*[@text='Sebulan sekali']")).click();

			}	

			Thread.sleep(1000);
			WebElement addFreq = driver.findElement(By.xpath("//*[@text='+']"));
			Thread.sleep(1000);

			List <WebElement> currFreq = driver.findElements(By.xpath("//*[@text='"+frequency+"']"));
			while(!(currFreq.size()>0)) {
				addFreq.click();	
				Thread.sleep(1000);
				currFreq = driver.findElements(By.xpath("//*[@text='"+frequency+"']"));
			}	
		}

		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();

	}

	public void selectTransferMethod(String transferMethod) throws InterruptedException {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'method')] | //*[contains(@text,'metode')]")));

		transferMethod=transferMethod.toLowerCase();
		if(transferMethod.equals("skn")) {
			driver.findElement(By.xpath("//*[contains(@text,'SKN')] | //*[contains(@text,'skn')]")).click();
		}
		else if(transferMethod.equals("network")){
			driver.findElement(By.xpath("//*[contains(@text,'network')]")).click();
		}	
		else {
			driver.findElement(By.xpath("//*[contains(@text,'rtgs')]")).click();
		}

		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();

	}

	public void summary() {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Confirmation')] | //*[contains(@text,'Konfirmasi')]"))).isDisplayed();
		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		screenAction.scrollUntilElementByXpath("//*[@text='TRANSFER'] | //*[@text='PEMINDAHAN DANA']").click();

	}

	public void summarySchedule() {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Confirmation')] | //*[contains(@text,'Konfirmasi')]"))).isDisplayed();
		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		screenAction.scrollUntilElementByXpath("//*[@text='TRANSFER'] | //*[@text='PEMINDAHAN DANA']").click();

	}

	public void result() {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Resi'] | //*[@text='Receipt']"))).isDisplayed();
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		driver.findElement(By.xpath("//*[contains(@text,'success')] | //*[contains(@text,'sukses')] ")).isDisplayed();
		screenAction.scrollUntilElementByXpath("//*[@text='DONE'] | //*[@text='SELESAI']").click();

	}
	public void resultSchedule() {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Resi'] | //*[@text='Receipt']"))).isDisplayed();
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		driver.findElement(By.xpath("//*[contains(@text,'process')] | //*[contains(@text,'proses')] ")).isDisplayed();
		screenAction.scrollUntilElementByXpath("//*[@text='DONE'] | //*[@text='SELESAI']").click();

	}
}
