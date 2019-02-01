package components;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import framework.AppConfig;
import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class FundTransfer_component  {

	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait30,wait65;
	private ScreenAction screenAction;

	private boolean newPayee,afterCutoff;

	private static HashMap<String, String> appConfig = AppConfig.getConfig();
	private static LocalTime SKN_CUTOFF_TIME,RTGS_CUTOFF_TIME,SKN_OP_TIME_FROM,RTGS_OP_TIME_FROM;
	private static LocalTime SKN_OP_TIME_TO=LocalTime.parse("23:59");
	private static LocalTime RTGS_OP_TIME_TO=LocalTime.parse("23:59");

	public FundTransfer_component(AndroidDriver<WebElement> driver) {

		this.driver=driver;
		wait10 = new WebDriverWait(driver,10);
		wait30 = new WebDriverWait(driver,30);
		wait65 = new WebDriverWait(driver,65);
		screenAction=new ScreenAction(driver);

		SKN_CUTOFF_TIME=LocalTime.parse(appConfig.get("CUT_OFF_TIME_SKN"));

		String[] sknOpTime=appConfig.get("OP_TIME_SKN").split("\\|\\|");
		SKN_OP_TIME_FROM=LocalTime.parse(sknOpTime[0]);
		if(!sknOpTime[1].equals("24:00"))SKN_OP_TIME_TO=LocalTime.parse(sknOpTime[1]);

		RTGS_CUTOFF_TIME=LocalTime.parse(appConfig.get("CUT_OFF_TIME_RTGS"));
		String[] rtgsOpTime=appConfig.get("OP_TIME_RTGS").split("\\|\\|");
		RTGS_OP_TIME_FROM=LocalTime.parse(rtgsOpTime[0]);
		if(!rtgsOpTime[1].equals("24:00"))RTGS_OP_TIME_TO=LocalTime.parse(rtgsOpTime[1]);

	}

	public void fundTransferMenu() {
		WebElement ftElement = wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='SEND'] | //*[@text='KIRIM']")));
		wait65.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		try {
			Thread.sleep(1000);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ftElement.click();
	}

	public void selectPayee(String toAccount) {

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).isDisplayed();	
		driver.findElements(By.className("android.widget.EditText")).get(1).sendKeys(toAccount);

		WebElement addedTrfList= null;
		try {
			addedTrfList=driver.findElement(By.xpath("//android.widget.TextView[@text='"+toAccount+"']"));
		} catch (Exception e) {
			// TODO: handle exception
		}

		if(addedTrfList!=null)addedTrfList.click();
		else {
			driver.findElements(By.className("android.widget.EditText")).get(0).sendKeys(toAccount);
			driver.findElements(By.className("android.widget.TextView")).get(3).click();
			newPayee=true;
		}

	}

	public void getPayeeName(String bankCode) {

		if(isNewPayee()) {
			wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Bank Name or Code']"))).click();

			wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Select Bank']"))).isDisplayed();
			driver.findElement(By.className("android.widget.EditText")).sendKeys(bankCode);
			driver.findElement(By.xpath("//android.widget.TextView[@text='"+bankCode+"']")).click();

			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wait65.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

			WebElement inputPayeeName = driver.findElements(By.className("android.widget.EditText")).get(1);
			if(inputPayeeName.getAttribute("text").isEmpty()) inputPayeeName.sendKeys("TEST PAYEE");

			screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();
		}
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
		if(recurrence.equals("one_time")) {
			driver.findElement(By.xpath("//*[@text='One Time'] | //*[@text='Tidak diulang']")).click();
		}
		else {
			if(recurrence.equals("everyday")) {	
				driver.findElement(By.xpath("//*[@text='Everyday'] | //*[@text='Setiap hari']")).click();
			}
			else if(recurrence.equals("once_a_week")) {
				driver.findElement(By.xpath("//*[@text='Once A Week'] | //*[@text='Seminggu sekali']")).click();
			}
			else if(recurrence.equals("once_every_two_weeks")) {
				driver.findElement(By.xpath("//*[@text='Once Every Two Weeks'] | //*[@text='2 Minggu sekali']")).click();
			}
			else if(recurrence.equals("once_a_month")) {		
				driver.findElement(By.xpath("//*[@text='Once A Month'] | //*[@text='Sebulan sekali']")).click();
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
		LocalTime currentTime = LocalTime.now();

		if(transferMethod.equals("skn")) {
			if(currentTime.isAfter(SKN_OP_TIME_FROM)&&currentTime.isBefore(SKN_OP_TIME_TO))
				if(currentTime.isAfter(SKN_CUTOFF_TIME)&&currentTime.isBefore(SKN_OP_TIME_TO))afterCutoff=true;
			driver.findElement(By.xpath("//*[contains(@text,'SKN')] | //*[contains(@text,'skn')]")).click();
		}
		else if(transferMethod.equals("network")){
			driver.findElement(By.xpath("//*[contains(@text,'network')]")).click();
		}	
		else {
			if(currentTime.isAfter(RTGS_OP_TIME_FROM)&&currentTime.isBefore(RTGS_OP_TIME_TO))
				if(currentTime.isAfter(RTGS_CUTOFF_TIME)&&currentTime.isBefore(RTGS_OP_TIME_TO))afterCutoff=true;
			
			driver.findElement(By.xpath("//*[contains(@text,'rtgs')]")).click();
		}
		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();
	}

	public void summary() {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'confirm')] | //*[contains(@text,'Konfirmasi')]"))).isDisplayed();
		screenAction.scrollUntilElementByXpath("//*[@text='TRANSFER'] | //*[@text='PEMINDAHAN DANA']").click();

	}

	public void summarySchedule() {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'confirm')] | //*[contains(@text,'Konfirmasi')]"))).isDisplayed();
		screenAction.scrollUntilElementByXpath("//*[@text='TRANSFER'] | //*[@text='PEMINDAHAN DANA']").click();

	}

	public void result(String fromAccountType,String toAccountType) {
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Transaction Number')] | //*[contains(@text,'Nomor transaksi')]"))).isDisplayed();
		wait65.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		if(afterCutoff || (fromAccountType.contains("NORMAL") && !toAccountType.contains("BLOCK")))
			Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@text,'success')] | //*[contains(@text,'sukses')] ")).isDisplayed(), true);
		else 
			Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@text,'failed')] | //*[contains(@text,'gagal')] ")).isDisplayed(), true);
		screenAction.scrollUntilElementByXpath("//*[@text='DONE'] | //*[@text='SELESAI']").click();

	}
	public void resultSchedule(String fromAccountType,String toAccountType,String recurrence) {
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Transaction Number')] | //*[contains(@text,'Nomor transaksi')]"))).isDisplayed();
		wait65.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		if(recurrence.equals("one_time"))Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@text,'success')] | //*[contains(@text,'sukses')] ")).isDisplayed(), true);
		else Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@text,'process')] | //*[contains(@text,'proses')] ")).isDisplayed(), true);

		screenAction.scrollUntilElementByXpath("//*[@text='DONE'] | //*[@text='SELESAI']").click();

	}

	public boolean isNewPayee() {

		return this.newPayee;
	}
}
