package framework;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
 
 
public class ThreadLocalDriver {
 
    private static ThreadLocal<AndroidDriver<WebElement>> tlDriver = new ThreadLocal<>();
 
    //OB: Setting AndroidDriver to ThreadLocal driver 
    public synchronized void setTLDriver(AndroidDriver<WebElement> driver) {
        tlDriver.set(driver);
    }
 
    public synchronized AndroidDriver<WebElement> getTLDriver() {
        return tlDriver.get();
    }
}