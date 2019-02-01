package components;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class BillPayment_component {

	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait30,wait65;
	private ScreenAction screenAction;

	public BillPayment_component(AndroidDriver<WebElement> driver) {

		this.driver=driver;

		wait10=new WebDriverWait(driver, 10);
		wait30=new WebDriverWait(driver, 30);
		wait65=new WebDriverWait(driver, 65);

		screenAction = new ScreenAction(driver);

	}

	public void main_billerMenu() {	
		WebElement billElement = wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='PAY'] | //*[@text='BAYAR']")));
		wait65.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		billElement.click();

	}
	
	public void other_billerMenu(String billerName) throws InterruptedException {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Others'] | //*[@text='Lainnya']"))).click();
	
		Thread.sleep(1500);
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys(billerName);
		
		//get list biller appeared in search box, choose the very bottom
		List<WebElement> billerList =driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'"+billerName+"')]"));
		billerList.get(billerList.size()-1).click();
	}

	public void water_billerMenu(String billerName) {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Water'] | //*[@text='Air']"))).click();
		
		//get list biller appeared in search box, choose the very bottom
		List<WebElement> billerList =driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'"+billerName+"')]"));
		billerList.get(billerList.size()-1).click();
	}

	public void inputSubscriberNo(String subscriberNo) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pay / Top up to'] | //*[@text='Bayar / Isi ulang']"))).isDisplayed();
		driver.findElement(By.className("android.widget.EditText")).sendKeys(subscriberNo);

		driver.findElements(By.className("android.widget.TextView")).get(4).click();
	}

	public void selectAccount(String sourceAccount,String amount,String desc) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'amount')] | //*[contains(@text,'jumlah')] "))).isDisplayed();
		List<WebElement> inputFields =driver.findElements(By.className("android.widget.EditText"));

		//input amount if prepaid, leave if its postpaid
		WebElement inputAmount = driver.findElements(By.className("android.widget.EditText")).get(0);
		if(inputAmount.getAttribute("text").equals("0")) inputAmount.sendKeys(amount);

		try {
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//select account
		driver.findElements(By.xpath("//*[@text='Select source account'] | //*[@text='Pilih rekening sumber']")).get(1).click();
		screenAction.scrollUntilElementByXpath("//*[contains(@text,'"+sourceAccount+"')]").click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'amount')] | //*[contains(@text,'jumlah')] ")));

		//input desc
		inputFields.get(1).sendKeys(desc);

		//input bill period if billername is Asuransi Sinarmas or Asuransi MSIG
		List <WebElement> periodElements= driver.findElements(By.xpath("//*[@text='Bill Period'] | //*[@text='Periode tagihan']"));
		if(periodElements.size()>0) {	

			periodElements.get(1).click();
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.findElements(By.className("android.widget.TextView")).get(new Random().nextInt(3)+1).click();
			wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'amount')] | //*[contains(@text,'jumlah')] ")));
		}

		screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='BERIKUTNYA']").click();	
	}

	

	public void summary() {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Konfirmasi')] | //*[contains(@text,'Confirmation')]"))).isDisplayed();
		wait10.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		screenAction.scrollUntilElementByXpath("//*[@text='CONFIRM PAYMENT'] | //*[@text='KONFIRMASI PEMBAYARAN']").click();		
	}

	public void result(String fromAccountType) {
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Transaction Number')] | //*[contains(@text,'Nomor transaksi')]"))).isDisplayed();
		wait65.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		if(fromAccountType.contains("NORMAL"))
			Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@text,'success')] | //*[contains(@text,'sukses')] ")).isDisplayed(), true);
		else 
			Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@text,'failed')] | //*[contains(@text,'gagal')] ")).isDisplayed(), true);

		screenAction.scrollUntilElementByXpath("//*[@text='DONE'] | //*[@text='SELESAI']").click();

	}

}
