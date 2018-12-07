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
	
	public void selectPayee(String folder,String filename,String toAccount) {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys(toAccount);
		screenAction.capture(folder, filename);
		
		driver.findElement(By.xpath("//*[@text='CONTINUE'] | //*[@text='LANJUT']")).click();
	}

	public void selectAccount(String folder,String filename,String sourceAccount,String amount,String desc) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//select account
		driver.findElement(By.xpath("//*[@text='Select your account'] | //*[@text='Pilih akun']")).click();
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
	public void selectAccountSchedule(String folder,String filename,String sourceAccount,String amount,String desc) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//select account
		driver.findElement(By.xpath("//*[@text='Select your account'] | //*[@text='Pilih akun']")).click();
		screenAction.scrollUntilElementByXpath("//*[contains(@text,'"+sourceAccount+"')]").click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available Balance')] | //*[contains(@text,'Saldo tersedia')] ")));
		List<WebElement> inputFields =driver.findElements(By.className("android.widget.EditText"));
		//input amount
		inputFields.get(0).sendKeys(amount);
		//input desc
		inputFields.get(1).sendKeys(desc);
		//tick schedule transfer box
		driver.findElement(By.xpath("//*[@text='Schedule transfer'] | //*[@text='Jadwalkan transfer']")).click();

		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();

	}

	public void selectSchedule(String folder,String filename,String recurrence,String frequency) throws InterruptedException {

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Schedule your transfer'] | //*[@text='Tentukan tanggal transfer']")));
		driver.findElement(By.xpath("//*[@text='Start date'] | //*[@text='Tanggal mulai']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button1"))).click();
		Thread.sleep(1000);
		List<WebElement> addFreq = driver.findElements(By.xpath("//*[@text='+']"));	
		recurrence= recurrence.toLowerCase();

		class SchFrequency{
			void set(int position){
				try {
					Thread.sleep(1000);
					List <WebElement> listFreq = driver.findElements(By.xpath("//*[@text='"+frequency+"']"));
					while(!(listFreq.size()>0)) {
						addFreq.get(position).click();	
						Thread.sleep(1000);
						listFreq = driver.findElements(By.xpath("//*[@text='"+frequency+"']"));
					}	
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("interup");
					e.printStackTrace();
				}
			}
		}

		//scroll until all scheduke option visible
		screenAction.scrollUntilElementByXpath("//*[@text='Once a month'] | //*[@text='Sebulan sekali']");
		SchFrequency schedFreq= new SchFrequency();
		if(recurrence.equals("one time")) {
			driver.findElement(By.xpath("//*[@text='One time'] | //*[@text='Tidak diulang']")).click();
		}
		else if(recurrence.equals("everyday")) {	
			driver.findElement(By.xpath("//*[@text='Everyday'] | //*[@text='Setiap hari']")).click();
			schedFreq.set(0);	
		}
		else if(recurrence.equals("once a week")) {
			driver.findElement(By.xpath("//*[@text='Once a week'] | //*[@text='Seminggu sekali']")).click();
			schedFreq.set(1);		
		}
		else if(recurrence.equals("once every two weeks")) {
			driver.findElement(By.xpath("//*[@text='Once every two weeks'] | //*[@text='2 Minggu sekali']")).click();
			schedFreq.set(2);		
		}
		else if(recurrence.equals("once a month")) {		
			driver.findElement(By.xpath("//*[@text='Once a month'] | //*[@text='Sebulan sekali']")).click();
			schedFreq.set(3);	
		}	

		screenAction.capture(folder, filename);	
		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();

	}

	public void selectTransferMethod(String folder,String filename,String transferMethod) throws InterruptedException {
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
		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();

	}

	public void summary(String folder,String filename) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='TRANSFER SUMMARY'] | //*[@text='Ringkasan Transfer']"))).isDisplayed();
		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		screenAction.capture(folder, filename);	
		screenAction.scrollUntilElementByXpath("//*[@text='TRANSFER'] | //*[@text='PEMINDAHAN DANA']").click();

	}

	public void summarySchedule(String folder,String filename) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Transfer Confirmation'] | //*[@text='Ringkasan Transfer']")));
		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='TRANSFER'] | //*[@text='PEMINDAHAN DANA']").click();

	}

	public void result(String folder,String filename) {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Transfer')]"))).isDisplayed();
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
