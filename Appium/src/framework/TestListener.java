package framework;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.BaseTestMethod;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;

public class TestListener implements ITestListener{

	private Object instance;
	private String methodCase,username,apkVersion;

	private static final String SCREENSHOT_FOLDER="Screenshots";
	private static boolean SCREENSHOT_FLAG=false;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

		Object currentInstance=result.getInstance();
		if(instance==null||!currentInstance.equals(instance)) {

			this.instance = currentInstance;
			this.methodCase="";

			//Initialize case if instance is empty or different from last run instance
			try {
				setMethodCase();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Print called method to console output
		System.out.println(methodCase+result.getMethod().getMethodName());
		setMethodName(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		if(instance!=null && SCREENSHOT_FLAG==true) {
			AndroidDriver<WebElement> driver=((DeviceSetup)instance).getDriver();
			saveScreenshotOnDisk(driver, methodCase+result.getMethod().getMethodName());
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		if(instance!=null) {
			
			System.out.println("FAILED: "+methodCase+result.getMethod().getMethodName());
			AndroidDriver<WebElement> driver=((DeviceSetup)instance).getDriver();
			saveScreenshotFailedTest(driver);

			if(SCREENSHOT_FLAG==true)
				saveScreenshotOnDisk(driver, methodCase+result.getMethod().getMethodName());
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {		
		// TODO Auto-generated method stub
		setMethodName(result);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	//Set Case name stored in variable mCaseName
	private void setMethodCase() throws Exception{

		if(instance==null) throw new Exception("InstanceIsNullException");
		
		this.apkVersion=instance.getClass().getSuperclass().getDeclaredField("apkVersion").get(instance).toString();
		methodCase+="v"+apkVersion+"_CASE(";


		for(Field field : instance.getClass().getDeclaredFields()) {

			if (Modifier.isPrivate(field.getModifiers())) {
				field.setAccessible(true);
			}
			try {
				String fieldName=field.getName();
				Object  obj = field.get(instance);
				
				if(obj!=null) {
					if(instance==null) throw new Exception(fieldName+".ValueMissingExceptionIn");
				}
				String value=field.get(instance).toString();
				switch (fieldName)
				{
				case "username":
					this.username=value;
					methodCase+=",USER:"+value;
					break;
				case "billerName":
					methodCase+=",BILLER:"+value.toLowerCase();
					break;		
				case "fromAccountType":
					methodCase+=",FROM:"+value.substring(value.indexOf("_")+1).toLowerCase();
					break;
				case "toAccountType":
					methodCase+=",TO:"+value.substring(value.indexOf("_")+1).toLowerCase();
					break;
				case "transferMethod":
					methodCase+=",METHOD:"+value;
					break;				
				case "tdType":
					methodCase+=",TYPE:"+value;
					break;	
				default:
					break;
				}

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		methodCase+=")_";
		methodCase=methodCase.replace("(,", "(");

	}

	//Set custom method Name in testng report
	private void setMethodName(ITestResult result) {
		try {
			BaseTestMethod baseTestMethod = (BaseTestMethod) result.getMethod();
			Field f = baseTestMethod.getClass().getSuperclass().getDeclaredField("m_methodName");
			f.setAccessible(true);
			f.set(baseTestMethod, methodCase+result.getMethod().getMethodName());
		} catch (Exception e) {

			Reporter.log("Exception : " + e.getMessage());
		}
	}


	@Attachment(value = "Screenshot on failure", type = "image/png")
	private byte[] saveScreenshotFailedTest(AndroidDriver<WebElement> driver) {

		return driver.getScreenshotAs(OutputType.BYTES);
	}

	private void saveScreenshotOnDisk(AndroidDriver<WebElement> driver,String filename) {

		LocalDateTime currentDateTime = LocalDateTime.now();
		File scrFile = driver.getScreenshotAs(OutputType.FILE);

		filename=filename.replace(":", "_");
		try {
			FileUtils.copyFile(scrFile, new File(SCREENSHOT_FOLDER+"/"+apkVersion+"/"+username+"/"+currentDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+"/"+currentDateTime.format(DateTimeFormatter.ofPattern("HHmmss"))+"_"+filename+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}


}
