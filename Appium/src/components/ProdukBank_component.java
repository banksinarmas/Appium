package components;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class ProdukBank_component {
	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait5,wait10,wait15,wait30,wait65;
	private ScreenAction screenAction;
	
	public ProdukBank_component(AndroidDriver<WebElement> driver) {
		this.driver=driver;

		wait5 = new WebDriverWait(driver,5);
		wait15 = new WebDriverWait(driver,15);
		wait10 = new WebDriverWait(driver,10);
		wait30 = new WebDriverWait(driver,30);
		wait65 = new WebDriverWait(driver,65);
		
		screenAction=new ScreenAction(driver);
	}	
	
	public void BukaProdukBank() {
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']")));
		wait65.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		driver.findElement(By.className("android.widget.TextView")).click();
		screenAction.scrollUntilElementByXpath("//*[@text='Open bank product'] | //*[@text='Buka produk bank']").click();
	}
	
	public void CreditCardMenu() {
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pilihan Produk'] | //*[@text='Product Selections']")));
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Credit Card')] | //*[@text='Kartu Kredit']"))).click();
	}
	
	public void CCOramiMenu()
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Pilih kartu kredit Anda')] | //*[@text='Choose your Credit Card'] "))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Kartu kredit Orami')] | //*[@text='Orami Credit Card']"))).click();
	}
	
	public void CCIndigoMenu()
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Pilih kartu kredit Anda')] | //*[@text='Choose your Credit Card'] "))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Kartu kredit Indigo')] | //*[@text='Indigo Credit Card']"))).click();
	}
	
	public void CCAlfamartMenu()
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Pilih kartu kredit Anda')] | //*[@text='Choose your Credit Card'] "))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Kartu kredit Alfamart')] | //*[@text='Alfamart Credit Card']"))).click();
		
	}
	
	public void TNC ()
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='SAYA SETUJU'] | //*[@text='I AGREE']"))).click();
	}
	
	public void Email()
	{
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='CONTINUE'] | //*[@text='BERIKUTNYA']"))).click();
	}
	
	public void Address(String RT, String RW, String Address) throws InterruptedException
	{
		wait15.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Hi, where is your current address'] | //*[@text='Hai, di mana Anda tinggal sekarang?'] "))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Province'] | //*[@text='Provinsi']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='BANTEN']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Hi, where is your current address'] | //*[@text='Hai, di mana Anda tinggal sekarang?'] "))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='City'] | //*[@text='Kota']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KAB. SERANG']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Hi, where is your current address'] | //*[@text='Hai, di mana Anda tinggal sekarang?'] "))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='District'] | //*[@text='Kecamatan']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='ANYAR']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Hi, where is your current address'] | //*[@text='Hai, di mana Anda tinggal sekarang?'] "))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Sub District'] | //*[@text='Kelurahan']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='ANYAR']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Hi, where is your current address'] | //*[@text='Hai, di mana Anda tinggal sekarang?'] "))).isDisplayed();
		screenAction.scrollUntilElementByXpath("//*[@text='RT']");
		List<WebElement> listInput= driver.findElements(By.className("android.widget.EditText"));
		listInput.get(2).sendKeys(RT);
		listInput.get(3).sendKeys(RW);
		
		screenAction.scrollUntilElementByXpath("//*[@text='Nama jalan'] | //*[@text='Street address']");
		driver.findElementsByClassName("android.widget.EditText").get(4).sendKeys(Address);
		
		//Thread.sleep(4000);
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='CONTINUE']").click();
	}
	
	public void Pekerjaan(String Gaji) throws InterruptedException 
	{
		// input pekerjaan
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='What do you do for living?'] | //*[@text='Apa yang Anda lakukan untuk hidup']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Choose your profession'] | //*[@text='Pilih profesi Anda']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pegawai Perusahaan Swasta']"))).click();
		//input gaji
		screenAction.scrollUntilElementByXpath("//*[@text='Monthly income'] | //*[@text='Pendapatan per bulan']");
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='What do you do for living?'] | //*[@text='Apa yang Anda lakukan untuk hidup']"))).isDisplayed();
		driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys(Gaji);
		//input sumber dana
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='What do you do for living?']| //*[@text='Apa yang Anda lakukan untuk hidup']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Source of fund'] | //*[@text='Sumber pendapatan']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Gaji / Salary']"))).click();
		//input jumlah tanggungan
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='What do you do for living?']| //*[@text='Apa yang Anda lakukan untuk hidup']"))).isDisplayed();
		//screenAction.scrollUntilElementByXpath("//*[@text='Number of Dependant'] | //*[@text='Jumlah tanggungan']");
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='CONTINUE']").click();		
	}
	public void PekerjaanIndigo(String Gaji) throws InterruptedException 
	{
		// input pekerjaan
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='What do you do for living?'] | //*[@text='Apa yang Anda lakukan untuk hidup']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Choose your profession'] | //*[@text='Pilih profesi Anda']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Wiraswasta/Wirausaha']"))).click();
		//input gaji
		screenAction.scrollUntilElementByXpath("//*[@text='Monthly income'] | //*[@text='Pendapatan per bulan']");
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='What do you do for living?'] | //*[@text='Apa yang Anda lakukan untuk hidup']"))).isDisplayed();
		driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys(Gaji);
		//input sumber dana
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='What do you do for living?']| //*[@text='Apa yang Anda lakukan untuk hidup']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Source of fund'] | //*[@text='Sumber pendapatan']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Gaji / Salary']"))).click();
		//input jumlah tanggungan
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='What do you do for living?']| //*[@text='Apa yang Anda lakukan untuk hidup']"))).isDisplayed();
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='CONTINUE']").click();		
	}
	
	public void DetailPekerjaan(String Title, String Industry, String Company, String CAddress, String CNumber, String RT, String RW)
	{
		// input work title
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us about your work'] | //*[@text='Ceritakan pekerjaan Anda']"))).isDisplayed();
		List<WebElement> listInput= driver.findElements(By.className("android.widget.EditText"));
		listInput.get(0).sendKeys(Title);
		driver.findElement(By.xpath("//*[@text='Work position'] | //*[@text='Jabatan pekerjaan']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Manager']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us about your work']| //*[@text='Ceritakan pekerjaan Anda']"))).isDisplayed();
		listInput.get(1).sendKeys(Industry);
		listInput.get(2).sendKeys(Company);
		listInput.get(3).sendKeys(CAddress);
		listInput.get(4).sendKeys(CNumber);
				
		screenAction.scrollUntilElementByXpath("//*[@text='CONTINUE'] | //*[@text='BERIKUTNYA']");
		
		//pilih province
		driver.findElement(By.xpath("//*[@text='Province'] | //*[@text='Provinsi']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='JAKARTA']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pendaftaran'] | //*[@text='Registration']"))).isDisplayed();
		//pilih City
		driver.findElement(By.xpath("//*[@text='City'] | //*[@text='Kota']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KODYA JAKARTA BARAT']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pendaftaran'] | //*[@text='Registration']"))).isDisplayed();
		//pilih District
		driver.findElement(By.xpath("//*[@text='District'] | //*[@text='Kecamatan']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KALIDERES']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pendaftaran'] | //*[@text='Registration']"))).isDisplayed();
		//pilih subdisctrict
		driver.findElement(By.xpath("//*[@text='Sub District'] | //*[@text='Kelurahan']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KALIDERES']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pendaftaran'] | //*[@text='Registration']"))).isDisplayed();
		//input RT
		screenAction.scrollUntilElementByXpath("//*[@text='RT']");
		driver.findElementsByClassName("android.widget.EditText").get(3).sendKeys(RT);
		driver.findElementsByClassName("android.widget.EditText").get(4).sendKeys(RW);
		
		//Thread.sleep(4000);
		
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='CONTINUE']").click();	
	}
	
	public void DetailCreditCard(String Nama, String CreditLimit, String JumlahKartuKredit)
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Provide details for your new Credit Card'] | //*[@text='Detail kartu kredit yang Anda inginkan']"))).isDisplayed();
		//input nama 
		driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys(Nama);
		//input credit limit
		driver.findElementsByClassName("android.widget.EditText").get(1).sendKeys(CreditLimit);
		driver.findElement(By.xpath("//*[@text='Status alamat sekarang'] | //*[@text='What is your current address status?'] ")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Rumah milik sendiri / Home owner']"))).click();
		//input stay at current address
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Provide details for your new Credit Card'] | //*[@text='Detail kartu kredit yang Anda inginkan']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Tinggal di alamat sekarang mulai'] | //*[@text='Stay at current address since']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='2019']"))).click();
		driver.findElement(By.xpath("//*[@text='2017']")).click();
		driver.findElement(By.xpath("//*[@text='OK']")).click();
		//input jumlah kartu kredit
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pendaftaran'] | //*[@text='Registration']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='How many credit card(s) do you have?'] | //*[@text='Berapa banyak kartu kredit yang Anda miliki?']"));
		driver.findElementsByClassName("android.widget.EditText").get(3).sendKeys(JumlahKartuKredit);
		//sumber dana pembayaran
		//wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pendaftaraan'] | //*[@text='Registration']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Source of payment'] | //*[@text='Sumber pembayaran']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Source of payment']| //*[@text='Sumber pembayaran']"))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'0008391157')]"))).click();
		//driver.findElement(By.xpath("//*[contain(@text='0008391157')]")).click();
		//jenis pembayaran
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pendaftaran'] | //*[@text='Registration']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Metode pembayaran'] | //*[@text='Payment method']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'penuh')]"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pendaftaran'] | //*[@text='Registration']"))).isDisplayed();
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='CONTINUE']").click();	
	}
	
	public void TaxNONPWP()
	{
		
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Registration'] | //*[@text='Pendaftaran']"))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'have NPWP')]"))).click();
		//driver.findElement(By.xpath("//*[@text='I don't have NPWP']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Reason')]"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Reason')]")));
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='NPWP sedang dalam proses pengurusan / NPWP is on progress']"))).click();
		//driver.findElement(By.xpath("//*[@text='NPWP sedang dalam proses pengurusan / NPWP is on progress']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Registration'] | //*[@text='Pendaftaran']"))).isDisplayed();
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='CONTINUE']").click();	
		
	}
	public void TaxNPWP(String NPWP)
	{
		
		//punya NPWP
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Registration'] | //*[@text='Pendaftaran']"))).isDisplayed();
		List<WebElement> listInput= driver.findElements(By.className("android.widget.EditText"));
		listInput.get(0).sendKeys(NPWP);
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='CONTINUE']").click();
		
	}
	
	public void takephototax() 
	{
		
		try {
			wait15.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.packageinstaller:id/permission_allow_button"))).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.packageinstaller:id/permission_allow_button"))).click();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contain(@text,\"take pictures and record\")] | //*[@text='Jangan Tampilkan Lagi']")));
			driver.findElement(By.xpath("//*[@text='ALLOW']")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='TAKE PHOTO']")));
		List<WebElement> takephoto =driver.findElements(By.xpath("//android.widget.TextView[contains(@text,'TAKE PHOTO')]"));
		takephoto.get(0).click();
		//driver.findElement(By.xpath("//*[@text='TAKE PHOTO']")).click();
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Do you want to upload this photo?']"))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='CONTINUE']"))).click();
	} 
	
	public void Business() throws InterruptedException
	{
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us more about your business']"))).isDisplayed();
		//input status pekerjaan
		driver.findElement(By.xpath("//*[@text='Status']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Full Time Employment']"))).click();
		//input start work
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us more about your business']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Start to work']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='2019']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='2016']"))).click();
		driver.findElement(By.xpath("//*[@text='OK']")).click();
		//input start work
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us more about your business']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Use/take photo of your latest payslip']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Select a Photo']")));
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Take')]"))).click();
		try {
			wait15.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.packageinstaller:id/permission_allow_button"))).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.packageinstaller:id/permission_allow_button"))).click();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contain(@text,\"access photos\")] | //*[@text='ALLOW']")));
			driver.findElement(By.xpath("//*[@text='ALLOW']")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		driver.findElement(By.xpath("//*[@content-desc='Shutter'] | //*[@text='Shutter']")).click();
		// perlu tambahan untuk choose file from galery
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'OK')]"))).click();
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us more about your business']"))).isDisplayed();
		Thread.sleep(1000);
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='CONTINUE']").click();	
				
	}
	public void Wiraswasta() throws InterruptedException
	{
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us more about your work']"))).isDisplayed();
		//input start work
		driver.findElement(By.xpath("//*[@text='Start business']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='2019']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='2016']"))).click();
		driver.findElement(By.xpath("//*[@text='OK']")).click();
		//last month
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us more about your work']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Last month']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Select a Photo']")));
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Take')]"))).click();
		driver.findElement(By.xpath("//*[@content-desc='Shutter'] | //*[@text='Shutter']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'OK')]"))).click();
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us more about your work']"))).isDisplayed();
		Thread.sleep(1000);
		//last 2 month
		driver.findElement(By.xpath("//*[@text='Last 2 months']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Select a Photo']")));
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Take')]"))).click();
		driver.findElement(By.xpath("//*[@content-desc='Shutter'] | //*[@text='Shutter']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'OK')]"))).click();
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us more about your work']"))).isDisplayed();
		Thread.sleep(1000);
		//last 3 month
		driver.findElement(By.xpath("//*[@text='Last 3 months']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Select a Photo']")));
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Take')]"))).click();
		driver.findElement(By.xpath("//*[@content-desc='Shutter'] | //*[@text='Shutter']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'OK')]"))).click();
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us more about your work']"))).isDisplayed();
		Thread.sleep(1000);
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='CONTINUE']").click();	
				
	}
	public void BusinessGalery() throws InterruptedException
	{
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us more about your business']"))).isDisplayed();
		//input status pekerjaan
		driver.findElement(By.xpath("//*[@text='Status']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Full Time Employment']"))).click();
		//input start work
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us more about your business']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Start to work']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='2019']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='2016']"))).click();
		driver.findElement(By.xpath("//*[@text='OK']")).click();
		//input start work
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us more about your business']"))).isDisplayed();
		driver.findElement(By.xpath("//*[@text='Use/take photo of your latest payslip']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Select a Photo']")));
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Choose')]"))).click();
		try {
			wait15.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.packageinstaller:id/permission_allow_button"))).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.packageinstaller:id/permission_allow_button"))).click();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contain(@text,\"access photos\")] | //*[@text='ALLOW']")));
			driver.findElement(By.xpath("//*[@text='ALLOW']")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView[@text,'GALLERY']"))).isDisplayed();
		List<WebElement> galery =driver.findElements(By.xpath("//android.view.View"));
		galery.get(3).click();
		//wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text,'Pictures']"))).click();
		Thread.sleep(1000);
		//List<WebElement> galery =driver.findElements(By.xpath("//android.widget.TextView[@text,'PICTURES']"));
		galery.get(3).click();
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us more about your business']"))).isDisplayed();
		Thread.sleep(1000);
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='CONTINUE']").click();	
				
	}
	
	public void Emergency(String FullName, String Relasi, String PhoneNumber, String RT, String RW, String Address) throws InterruptedException
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Emergency contact details']"))).isDisplayed();
		Thread.sleep(4000);
		List<WebElement> listInput= driver.findElements(By.className("android.widget.EditText"));
		listInput.get(0).sendKeys(FullName);
		//driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys(FullName);
		//input relasi
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Hubungan'] | //*[@text='Relationship']"))).click();
		//driver.findElement(By.xpath("//*[@text='Hubungan'] | //*[@text='Relationship']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Parent/Orang Tua']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Emergency contact details']"))).isDisplayed();
		listInput.get(1).sendKeys(PhoneNumber);
		
		//input provinsi
		driver.findElement(By.xpath("//*[@text='Provinsi'] | //*[@text='Province']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='BANTEN']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Registration'] | //*[@text='Pendaftaran']"))).isDisplayed();
		
		//input city
		driver.findElement(By.xpath("//*[@text='City'] | //*[@text='Kota']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KAB. TANGERANG']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Registration'] | //*[@text='Pendaftaran']"))).isDisplayed();
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='CONTINUE']");	
		
		//input kecamatan
		driver.findElement(By.xpath("//*[@text='District'] | //*[@text='Kecamatan']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='CIKUPA']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Registration'] | //*[@text='Pendaftaran']"))).isDisplayed();
		
		//input subdistrict
		driver.findElement(By.xpath("//*[@text='Sub District'] | //*[@text='Kelurahan']")).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='BOJONG']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Registration'] | //*[@text='Pendaftaran']"))).isDisplayed();
		//screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='CONTINUE']").click();	
		//Thread.sleep(4000);
		//input RTRW
		//listInput.get(1).sendKeys(RT);
		driver.findElementsByClassName("android.widget.EditText").get(1).sendKeys(RT);
		driver.findElementsByClassName("android.widget.EditText").get(2).sendKeys(RW);
		//listInput.get(2).sendKeys(RW);
		
		//input address
		//screenAction.scrollUntilElementByXpath("//*[@text='Nama jalan'] | //*[@text='Street address']");
		driver.findElementsByClassName("android.widget.EditText").get(3).sendKeys(Address);
		//listInput.get(3).sendKeys(Address);
		
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='CONTINUE']").click();	
					
	}
	
	public void DeliveryCard()
	{
		
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Where do you want us to deliver your card?'] | //*[@text='Registration ']"))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Work address']"))).click();
		screenAction.scrollUntilElementByXpath("//*[@text='BERIKUTNYA'] | //*[@text='CONTINUE']").click();	
	
	}
	
	public void Result()
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Your ticket code']"))).isDisplayed();
		screenAction.scrollUntilElementByXpath("//*[@text='DONE']").click();	
		//wait15.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='DONE'] | //*[@text='BERIKUTNYA']"))).click();
	}
}
