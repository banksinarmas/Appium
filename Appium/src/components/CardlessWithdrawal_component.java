package components;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.DeviceCapabilities;

public class CardlessWithdrawal_component extends DeviceCapabilities{

	
	public static void cardlessWdrMenu() {
		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='HOME'] | //*[@text='BERANDA']")));
		
		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.className("android.widget.TextView")).click();

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Cardless withdrawal'] | //*[@text='Tarik tunai tanpa kartu']"))).click();
		
	}
	

	public static void selectPhoneNo(String phoneNo) {

		FundTransfer_component.selectPayee(phoneNo);
	}
	
	public static void selectAccount(String sourceAccount,String amount,String desc) {

		FundTransfer_component.selectAccount(sourceAccount, amount, desc);
	}
	
	public static void summary() {

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Summary'] | //*[@text='Ringkasan']")));

		driver.findElement(By.xpath("//*[@text='Penarikan'] | //*[@text='Withdrawal']")).click();

	}
	
	public static void result() {

		wait60.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text,'Cardless Withdrawal')]"))).isDisplayed();

		wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.className("android.widget.ProgressBar")));

		List<WebElement> result = driver.findElements(By.xpath("//*[@text='OK']"));
		while (!(result.size()>0)){
			driver.swipe(50, 1500, 50, 200, 3000);
			result  = driver.findElements(By.xpath("//*[@text='OK']"));
		}

		if(result.size()>0)result.get(0).click();

	}
}
