package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class Bahasa_component {

	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait30,wait65;
	private ScreenAction screenAction;

	public Bahasa_component(AndroidDriver<WebElement> driver) {
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

		screenAction.scrollUntilElementByXpath("//*[@text='Settings'] | //*[@text='Pengaturan']").click();

	}

	public void BahasaMenu() {

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Pilihan bahasa'] | //*[@text='Language']"))).click();
		//driver.findElement(By.xpath("//*[@text="Change Password"]"))
		//driver.findElement(By.xpath("//*[@text='Change Password']")).click();

	}

	public void Bahasa(String BahasaIndonesia) throws InterruptedException {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Language'] | //*[@text='Pilihan bahasa']"))).isDisplayed();

		boolean isBahasa = false;
		if(driver.findElements(By.xpath("//*[@text='Language']")).size()>0) 
			wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Bahasa Indonesia']"))).click();

		else {
			wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='English']"))).click();
			isBahasa = true;
		}
		driver.pressKeyCode(AndroidKeyCode.BACK);

		Thread.sleep(1000);
		driver.pressKeyCode(AndroidKeyCode.BACK);


		/*wait30.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/alertTitle"))).isDisplayed();
		driver.findElement(By.id("android:id/button1")).click();

		wait30.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));*/
		if(isBahasa) 
			Assert.assertEquals(wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME']"))).isDisplayed(), true);
		else
			Assert.assertEquals(wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='BERANDA']"))).isDisplayed(), true);

	}
}


