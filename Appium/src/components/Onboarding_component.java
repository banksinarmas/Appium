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
	private WebDriverWait wait5,wait10,wait15,wait20,wait60;
	private ScreenAction screenAction;

	public Onboarding_component(AndroidDriver<WebElement> driver) {

		this.driver=driver;
		wait5= new WebDriverWait(driver, 5);
		wait10 = new WebDriverWait(driver, 10);
		wait15 = new WebDriverWait(driver, 15);
		wait20 = new WebDriverWait(driver, 20);
		wait60 = new WebDriverWait(driver, 60);
			
		screenAction= new ScreenAction(driver);
		
	}

	public void landingPage() {

		try {
			wait10.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button2"))).click();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			wait15.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'SMS') or contains(@text,'location') or contains(@text,'lokasi')]"))).isDisplayed();
			driver.findElement(By.xpath("//*[@text='ALLOW'] | //*[@text='Allow'] | //*[@text='IZINKAN'] | //*[@text='Izinkan']")).click();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'SMS') or contains(@text,'location') or contains(@text,'lokasi')]"))).isDisplayed();
			driver.findElement(By.xpath("//*[@text='ALLOW'] | //*[@text='Allow'] | //*[@text='IZINKAN'] | //*[@text='Izinkan']")).click();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		try {
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button2"))).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text=\"Don't Show This Again\"] | //*[@text='Jangan Tampilkan Lagi']"))).click();
			driver.findElement(By.xpath("//*[@text='OK']")).click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button2"))).click();
			Thread.sleep(1500);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		screenAction.scrollUntilElementByXpath("//*[@text='SAYA MEMILIKI AKUN'] | //*[@text='I HAVE AN ACCOUNT']").click();
		
	}

	public void loginUsernamePassword(String username,String password) {

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Selamat Datang!'] | //*[@text='Welcome!']"))).isDisplayed();
		List<WebElement> credentials = driver.findElements(By.className("android.widget.EditText"));
		credentials.get(0).sendKeys(username);
		credentials.get(1).sendKeys(password);

		screenAction.scrollUntilElementByXpath("//*[@text='LOG IN']").click();

	}

	public void dashboardFreshDevice() {
		
		//Cardless guide
		/*
		for(int i =1;i<=4;i++) {		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			screenAction.capture(folder, filename+"_"+i);
			screenAction.scrollUntilElementByXpath("//*[@text='NEXT'] | //*[@text='Lanjut']").click();				
		}	
		screenAction.capture(folder, filename+"_"+5);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		screenAction.scrollUntilElementByXpath("//*[@text='DONE'] | //*[@text='SELESAI']").click();
		screenAction.capture(folder, filename+"_"+6);*/
		
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']"))).isDisplayed();
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

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
