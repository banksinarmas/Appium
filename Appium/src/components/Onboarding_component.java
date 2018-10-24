package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;


public class Onboarding_component {

	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait5,wait10,wait20,wait60;
	private ScreenAction screenAction;

	public Onboarding_component(AndroidDriver<WebElement> driver) {

		this.driver=driver;
		wait5= new WebDriverWait(driver, 5);
		wait10 = new WebDriverWait(driver, 10);
		wait20 = new WebDriverWait(driver, 20);
		wait60 = new WebDriverWait(driver, 60);
		
		screenAction= new ScreenAction(driver);
		
	}

	public void landingPage(String folder,String filename) {

		try {
			wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='ALLOW']"))).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text=\"Don't Show This Again\"] | //*[@text='Jangan Tampilkan Lagi']"))).click();
			driver.findElement(By.xpath("//*[@text='OK']")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			wait10.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button2"))).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='SAYA MEMILIKI AKUN'] | //*[@text='I HAVE AN ACCOUNT']").click();
		
	}

	public void loginUsernamePassword(String folder,String filename,String username,String password) {

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Selamat Datang!'] | //*[@text='Welcome!']"))).isDisplayed();
		List<WebElement> credentials = driver.findElements(By.className("android.widget.EditText"));
		credentials.get(0).sendKeys(username);
		credentials.get(1).sendKeys(password);

		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='LOG IN']").click();

	}
	public void inputOTP(String folder,String filename) throws Exception
	{
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'SMS')]"))).isDisplayed();
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.className("android.widget.EditText"))).sendKeys("123456");
	
		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']").click();

	}
	public void createEasyPin(String folder,String filename,String easyPin) throws Exception
	{
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Create EasyPIN'] | //*[@text='Buat EasyPIN']"))).isDisplayed();
		driver.findElement(By.className("android.widget.EditText")).sendKeys("123456");
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Konfirmasi EasyPIN'] | //*[@text='Confirm EasyPIN']"))).isDisplayed();
		driver.findElement(By.className("android.widget.EditText")).sendKeys("123456");

		screenAction.capture(folder, filename);
		screenAction.scrollUntilElementByXpath("//*[@text='LANJUTKAN'] | //*[@text='CONTINUE']").click();

	}
	public void dashboardFreshDevice(String folder,String filename) {

		for(int i =1;i<=4;i++) {		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			screenAction.capture(folder, filename+"_"+i);
			screenAction.scrollUntilElementByXpath("//*[@text='Next'] | //*[@text='Lanjut']").click();				
		}	
		screenAction.capture(folder, filename+"_"+5);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		screenAction.scrollUntilElementByXpath("//*[@text='DONE'] | //*[@text='SELESAI']").click();
		screenAction.capture(folder, filename+"_"+6);

	}

	public void logout() throws Exception
	{	
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']"))).isDisplayed();

		try {
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Menyerahkan'] | //*[@text='Submit']"))).click();	
			wait10.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button1"))).click();
			wait20.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		} catch (Exception e) {
			// TODO: handle exception
		}

		driver.findElement(By.className("android.widget.TextView")).click();
		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KELUAR'] | //*[@text='LOG OUT']"))).click();
		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text ='LOG IN']"))).isDisplayed();
	}
}
