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
	private WebDriverWait wait5,wait10,wait15,wait20,wait65;
	private ScreenAction screenAction;

	public Onboarding_component(AndroidDriver<WebElement> driver) {

		this.driver=driver;
		wait5= new WebDriverWait(driver, 5);
		wait10 = new WebDriverWait(driver, 10);
		wait15 = new WebDriverWait(driver, 15);
		wait20 = new WebDriverWait(driver, 20);
		wait65 = new WebDriverWait(driver, 65);

		screenAction= new ScreenAction(driver);
		
	}

	public void landingPage() {

		try {
			wait15.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.packageinstaller:id/permission_allow_button"))).click();
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			wait5.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.packageinstaller:id/permission_allow_button"))).click();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text=\"Don't Show This Again\"] | //*[@text='Jangan Tampilkan Lagi']"))).click();
			driver.findElement(By.xpath("//*[@text='OK']")).click();
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
		
		wait65.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']"))).isDisplayed();
		wait65.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public void logout() throws Exception
	{	

		driver.findElement(By.className("android.widget.TextView")).click();
		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='KELUAR'] | //*[@text='LOG OUT']"))).click();
		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text ='LOG IN']"))).isDisplayed();
	}
}
