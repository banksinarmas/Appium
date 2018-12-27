package framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class AndroidAPK {

	private String deviceID;
	private int port,systemPort;
	private final long TIMEOUT=600000;
	private boolean mustInstall=true;

	public AndroidAPK(String deviceID,int port,int systemPort) {
		this.deviceID=deviceID;
		this.port=port;
		this.systemPort=systemPort;
	}

	public void download() throws IOException, InterruptedException {
		adbCommand("cmd /c adb -s "+deviceID+" shell \"cd sdcard/Download && rm app-release.apk \"");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName","Emulator");
		capabilities.setCapability("udid",deviceID);
		capabilities.setCapability("systemPort",systemPort);
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("appPackage","com.google.android.gm");
		capabilities.setCapability("appActivity",".ConversationListActivityGmail");
		capabilities.setCapability("automationName","uiautomator2");
		capabilities.setCapability("newCommandTimeout", 360);
		AndroidDriver<WebElement> driver =  new AndroidDriver<WebElement>(new URL("http://127.0.0.1:"+port+"/wd/hub"),capabilities);

		WebDriverWait wait10 = new WebDriverWait(driver,10);
		WebDriverWait wait30 = new WebDriverWait(driver,30);

		WebElement latestVersionElement= wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@content-desc,'Android app') and not(contains(@content-desc,'UAT'))] ")));
		String latestVersion= latestVersionElement.getAttribute("content-desc");
		latestVersion=latestVersion.substring(latestVersion.indexOf("1.0"),latestVersion.indexOf(" Android"));
		String currentVersion=getApkVersion(deviceID);

		if(currentVersion==null || !currentVersion.equals(latestVersion)) {
			
			System.out.println("Download will begin soon for apk v"+latestVersion);
		
			latestVersionElement.click();
			wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@content-desc='click here'] | //*[@text='click here']"))).click();
			wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@resource-id='download-button']"))).click();

			try {
				wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='OK']"))).click();
				Thread.sleep(5000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else {
			mustInstall=false;
			System.out.println("Device "+deviceID+" already has the latest apk installed v"+currentVersion);

		}
		
		adbCommand("cmd /c adb -s "+deviceID+" shell pm uninstall io.appium.uiautomator2.server");
		adbCommand("cmd /c adb -s "+deviceID+" shell pm uninstall io.appium.uiautomator2.server.test");
		

	}

	public void install() throws InterruptedException, IOException {

		if(mustInstall) {
			long startTime = System.currentTimeMillis();
			boolean isApkFound=false;

			System.out.print("Download in progress");

			while(System.currentTimeMillis()-startTime <TIMEOUT ){
				System.out.print(".");
				String downloadedApk=adbCommand("cmd /c adb -s "+deviceID+" shell \"cd sdcard/Download && ls app-release.apk\"");
				if(downloadedApk!=null && downloadedApk.equals("app-release.apk")) {
					System.out.println("\nInstalling app-release.apk");
					System.out.println(adbCommand("cmd /c adb -s "+deviceID+" shell \"cd sdcard/Download && pm install -r app-release.apk \""));
					isApkFound=true;
					break;
				}
			}

			if(isApkFound==false){
				System.out.println("app-release.apk is not found");
			}	
		}
		
	}

	public static String getApkVersion(String deviceID) throws IOException {

		String version = adbCommand("cmd /c adb -s "+deviceID+" shell dumpsys package com.ubyapp | findstr /R /C:\"versionName\"");
		try {
			version=version.substring(version.indexOf("1.0"));
		} catch (Exception e) {
			// TODO: handle exception
		}

		return version;	
	}

	public static String adbCommand(String command) throws IOException {		
		return new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(command).getInputStream())).readLine();			
	}

}
