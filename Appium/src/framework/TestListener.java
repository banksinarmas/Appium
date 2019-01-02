package framework;

import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.appium.java_client.android.AndroidDriver;

public class TestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {

		Object obj = result.getInstance();
		String className=obj.getClass().getSuperclass().getName();

		AndroidDriver<WebElement> driver = null;
		if(className.equals("framework.LockdownDevice"))
			driver=((LockdownDevice)obj).getDriver();
		else if(className.equals("framework.FreshDevice"))
			driver=((FreshDevice)obj).driver;

		if(driver!=null)
			ScreenAction.saveScreenshotFailedTest(driver, result.getMethod().getMethodName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub



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

}
