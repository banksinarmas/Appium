package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;

public class SimasGold_component {
	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait65,wait30;
	private ScreenAction screenAction;
	
	public SimasGold_component(AndroidDriver<WebElement> driver) {
		this.driver=driver;
		
		wait10=new WebDriverWait(driver, 10);
		wait30=new WebDriverWait(driver, 30);
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
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Saving Account')] | //*[@text='Rekening Tabungan']"))).click();
	}
	
	public void SavingAccountMenu()
	{
		//wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Rekening Tabungan')] | //*[@text='Rekening Tabungan")));
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Tabungan Simas Gold')] | //*[@text='Simas Gold Savings'] "))).click();
	}
	
	public void TNCSimasGold ()
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='SAYA SETUJU'] | //*[@text='I AGREE']"))).click();
	}
	
	public void inputSimasGold ()
	{
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='CONTINUE'] | //*[@text='BERIKUTNYA']"))).click();
	}
	
	public void PembayaranSimasGold()
	{
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Registration'] | //*[@text='Pendaftaran']")));
		screenAction.scrollUntilElementByXpath("//*[@text='Select source account'] | //*[@text='Pilih sumber dana']").click();
		screenAction.scrollUntilElementByXpath("//*[@text='0008391157']").click();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Registration'] | //*[@text='Pendaftaran']")));
		screenAction.scrollUntilElementByXpath("//*[@text='BUKA'] | //*[@text='CREATE']").click();
	// butuh halaman easypin
	}
	
	public void SGresult() {
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Transaction Number')] | //*[contains(@text,'Nomor transaksi')]"))).isDisplayed();
		wait65.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		screenAction.scrollUntilElementByXpath("//*[@text='DONE'] | //*[@text='SELESAI']").click();

	}
	
}
