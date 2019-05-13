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

public class SimasMerchant_component {

	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait30,wait65;
	private ScreenAction screenAction;

	public SimasMerchant_component(AndroidDriver<WebElement> driver) {
		this.driver=driver;

		wait10=new WebDriverWait(driver, 10);
		wait30=new WebDriverWait(driver, 30);
		wait65=new WebDriverWait(driver, 65);

		screenAction = new ScreenAction(driver);	
	}

	public void settingsMenu() {
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']")));
		wait65.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		driver.findElement(By.className("android.widget.TextView")).click();

		screenAction.scrollUntilElementByXpath("//*[@text='Simas Merchant'] | //*[@text='Simas QR Merchant']").click();
	}

	public void SimasMerchantDaftar(String NamaMerchant,String NoSIUP,String NoTDP,String NoNPWP,String DailySales,String Email,String NamaPemilik,String NIK,String TelpMerchant) {

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Simas QR Merchant'] | //*[@text='Merchant']"))).isDisplayed();
		screenAction.scrollUntilElementByXpath("//*[@text='TAMBAH SIMAS QR MERCHANT'] | //*[@text='ADD NEW BUSINESS/COMPANY']").click();
		//screenAction.scrollUntilElementByXpath("//*[@text='I AGREE'] | //*[@text='SAYA SETUJU']").click();

		//Merchant
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@contains='Beritahu kamu tentang ukuran bisnis Anda'] | //*[@text='']"))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Net Worth'] | //*[@text='Kekayaan bersih']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='<= Rp 50.000.000'] "))).click();
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@contains='Beritahu kamu tentang ukuran bisnis Anda'] | //*[@text='']"))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Penjualan per tahun'] | //*[@text='Sales per annum']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='<= Rp 300.000.000']"))).click();
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@contains='Beritahu kamu tentang ukuran bisnis Anda'] | //*[@text='']"))).isDisplayed();
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Jumlah karyawan'] | //*[@text='Numbers of employees']"))).click();
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='1-4']"))).click();
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@contains='Beritahu kamu tentang ukuran bisnis Anda'] | //*[@text='']"))).isDisplayed();
		try {
			Thread.sleep(1700);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='CONTINUE'] | //*[@text='LANJUTKAN']"))).click();
	}
	public void DetailPerusahaan(String NamaMerchant,String NoSIUP,String NoTDP,String NoNPWP,String DailySales,String Email,String NamaPemilik,String NIK,String TelpMerchant) throws InterruptedException
	{
		try {
			Thread.sleep(3700);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Tell us about your'] | //*[@text='Deskripsikan tentang']"))).isDisplayed();

		List<WebElement> listInput= driver.findElements(By.className("android.widget.EditText"));
		listInput.get(0).sendKeys(NamaMerchant);
		listInput.get(1).sendKeys(NoSIUP);
		listInput.get(2).sendKeys(NoTDP);
		listInput.get(3).sendKeys(NoNPWP);
		listInput.get(4).sendKeys(DailySales);

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Business line'] | //*[@text='Bidang usaha']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Makanan & minuman']"))).click();

		screenAction.scrollUntilElementByXpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']").isDisplayed();

		driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys(Email);
		driver.findElementsByClassName("android.widget.EditText").get(1).sendKeys(NamaPemilik);
		driver.findElementsByClassName("android.widget.EditText").get(2).sendKeys(NIK);
		driver.findElementsByClassName("android.widget.EditText").get(3).sendKeys(TelpMerchant);

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Account'] | //*[@text='Pilih akun']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='0008393087 • Tabungan Sinarmas-Passbook • DWI FEBRYANA']"))).click();

		screenAction.scrollUntilElementByXpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']").click();		
	}

	public void SimasToko(String NamaToko,String HpToko,String Alamat) {
		//Store
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Complete your store info'] | //*[@text='Lengkapi info store anda']"))).isDisplayed();

		/*List<WebElement> listInput= driver.findElements(By.className("android.widget.EditText"));

		listInput.get(0).sendKeys(NamaToko);
		listInput.get(1).sendKeys(HpToko);
		 */
		driver.findElementsByClassName("android.widget.EditText").get(0).sendKeys(NamaToko);
		driver.findElementsByClassName("android.widget.EditText").get(1).sendKeys(HpToko);


		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Province'] | //*[@text='Provinsi']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='JAKARTA']"))).click();

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Complete your store info'] | //*[@text='Lengkapi info store anda']"))).isDisplayed();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='City'] | //*[@text='Kota']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KODYA JAKARTA BARAT']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Complete your store info'] | //*[@text='Lengkapi info store anda']"))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='District'] | //*[@text='Kecamatan']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KEBON JERUK']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Complete your store info'] | //*[@text='Lengkapi info store anda']"))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Sub District'] | //*[@text='Kelurahan']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KEBON JERUK']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Complete your store info'] | //*[@text='Lengkapi info store anda']"))).isDisplayed();

		screenAction.scrollUntilElementByXpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']");

		driver.findElementsByClassName("android.widget.EditText").get(1).sendKeys(Alamat);

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Lokas Store/otlet'] | //*[@text='Store/outlet location']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Mall/shopping centre']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Ownership'] | //*[@text='Kepemilikan']"))).click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Milik sendiri']"))).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']"))).click();
	}	

	public void SimasKasir(String NamaKasir,String LoginKasir,String HpKasir) {

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Complete your cashier info'] | //*[@text='Lengkapi info kasir anda']"))).isDisplayed();

		List<WebElement> listInput= driver.findElements(By.className("android.widget.EditText"));
		listInput.get(0).sendKeys(NamaKasir);
		listInput.get(1).sendKeys(LoginKasir);
		listInput.get(2).sendKeys(HpKasir);

		screenAction.scrollUntilElementByXpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']").click();
	}

	public void KonfirmasiData() {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='informasi Bisnis/perusahaan'] | //*[@text='Business/Company info']"))).isDisplayed();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		screenAction.scrollUntilElementByXpath("//*[@text='KONFIRMASI'] | //*[@text='CONFIRM']").click();
	}
	public void result(String fromAccountType) {
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Reference number')] | //*[contains(@text,'Nomor ref')]"))).isDisplayed();
		wait65.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		screenAction.scrollUntilElementByXpath("//*[@text='DONE'] | //*[@text='SELESAI']").click();

		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(fromAccountType.contains("NORMAL"))
			Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@text,'success')] | //*[contains(@text,'berhasil')] ")).isDisplayed(), true);
		else 
			Assert.assertEquals(driver.findElement(By.xpath("//*[contains(@text,'failed')] | //*[contains(@text,'gagal')] ")).isDisplayed(), true);

	}
}
