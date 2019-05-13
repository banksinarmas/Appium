package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class CCIndigoETB_component {
	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait65;
	private ScreenAction screenAction;
	
	
	public CCIndigoETB_component(AndroidDriver<WebElement> driver) {
		this.driver=driver;
		
		wait10=new WebDriverWait(driver, 10);
	//	wait30=new WebDriverWait(driver, 30);
		wait65=new WebDriverWait(driver, 65);
		
		screenAction = new ScreenAction(driver);	
	}
	public void BukaProdukBank() {
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']")));
		wait65.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		driver.findElement(By.className("android.widget.TextView")).click();
		//wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Open bank product'] | //*[@text='Buka P']")));
		screenAction.scrollUntilElementByXpath("//*[@text='Open bank product'] | //*[@text='Buka produk bank']").click();
		
	}
	
	public void ProdukMenu() {
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pilihan Produk'] | //*[@text='Product Selections']")));
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Credit Card')] | //*[@text='Kartu Kredit']"))).click();
	}
	
	public void CCIndigoMenu()
	{
		//wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Rekening Tabungan')] | //*[@text='Rekening Tabungan")));
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Pilih kartu kredit Anda')] | //*[@text='Choose your Credit Card'] "))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Kart Kredit Indigo')] | //*[@text='Indigo Credit Card']"))).click();
		
	}
	
	public void TNCIndigo ()
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='SAYA SETUJU'] | //*[@text='I AGREE']"))).click();
	}
	
	public void EmailIndigo ()
	{
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='CONTINUE'] | //*[@text='BERIKUTNYA']"))).click();
	}
	public void AddressIndigo(String RT, String RW, String Address) throws InterruptedException
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Hi, where is your current address'] | //*[@text='Hi, di mana Anda tinggal sekarang?'] "))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Province'] | //*[@text='Provinse']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='BANTEN']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Hi, where is your current address'] | //*[@text='Hi, di mana Anda tinggal sekarang?'] "))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='City'] | //*[@text='Kota']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KAB. SERANG']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Hi, where is your current address'] | //*[@text='Hi, di mana Anda tinggal sekarang?'] "))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='District'] | //*[@text='Kecamatan']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='ANYAR']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Hi, where is your current address'] | //*[@text='Hi, di mana Anda tinggal sekarang?'] "))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Sub District'] | //*[@text='Kelurahan']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='ANYAR']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Hi, where is your current address'] | //*[@text='Hi, di mana Anda tinggal sekarang?'] "))).isDisplayed();
		screenAction.scrollUntilElementByXpath("//*[@text='RT']").click();
		List<WebElement> inputFields =driver.findElements(By.className("android.widget.EditText"));
		//input RT
		inputFields.get(2).sendKeys(RT);
		Thread.sleep(4000);
		//input RW
		inputFields.get(3).sendKeys(RW);
		Thread.sleep(4000);
		screenAction.scrollUntilElementByXpath("//*[@text='Street address']");
		List<WebElement> listInput= driver.findElements(By.className("android.widget.EditText"));
		listInput.get(4).sendKeys(Address);
		Thread.sleep(4000);
		//screenAction.scrollUntilElementByXpath("//*[@text='Street address']").click();
		//inputFields.get(4).sendKeys(Address);
		///wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Done'] "))).click();
		//Thread.sleep(4000);
		driver.findElement(By.xpath("//android.widget.EditText[@text='Nama jalan ']")).sendKeys(Address);
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='CONTINUE']").click();
		
		//wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='DONE']"))).isDisplayed();
		//List<WebElement> listInput= driver.findElements(By.className("android.widget.EditText"));
		//listInput.get(6).sendKeys(RT);
		
		
		//wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Available balance')] | //*[contains(@text,'Saldo tersedia')]"))).isDisplayed();

	}
	/*
	public void AddressIndigo(String Province,String City,String District,String SubDistrict,String RT,String RW,String StreetAddres)
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Hi, where is your current address'] | //*[@text='Hi, di mana Anda tinggal sekarang?'] "))).isDisplayed();
		
		List<WebElement> listInput= driver.findElements(By.className("android.widget.EditText"));
		
		listInput.get(0).sendKeys(Province);
		listInput.get(1).sendKeys(City);
		listInput.get(2).sendKeys(District);
		listInput.get(3).sendKeys(SubDistrict);
		///listInput.get(4).sendKeys(PostalCode);
		listInput.get(5).sendKeys(RT);
		listInput.get(6).sendKeys(RW);
		listInput.get(7).sendKeys(StreetAddres);
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='Continue']").click();
		
	}
	/*public void PekerjaanIndigo()
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Apa yang Anda lakukan untuk hidup'] | //*[@text='What do you for living'] "))).isDisplayed();
		
		List<WebElement> listInput= driver.findElements(By.className("android.widget.EditText"));
		
		listInput.get(0).sendKeys(Province);
		listInput.get(1).sendKeys(City);
		listInput.get(2).sendKeys(District);
		listInput.get(3).sendKeys(SubDistrict);
		listInput.get(4).sendKeys(PostalCode);
		listInput.get(5).sendKeys(RT);
		listInput.get(6).sendKeys(RW);
		listInput.get(7).sendKeys(StreetAddres);
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='Continue']").click();
		
	}
	
	
	public void SGresult() {
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Transaction Number')] | //*[contains(@text,'Nomor transaksi')]"))).isDisplayed();
		wait65.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		screenAction.scrollUntilElementByXpath("//*[@text='DONE'] | //*[@text='SELESAI']").click();

	}
	*/
}
