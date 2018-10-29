package onboarding.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import onboarding.Login_FreshDevice;

public class Factory_Login_FreshDevice {

	@Factory(dataProvider="login_freshDevice")
	private Object[] loginFreshDeviceCreateInstances(String deviceID,String port,String systemPort,String username, String password,String easyPin) throws IOException {
		return new Object[] {new Login_FreshDevice(deviceID, Integer.parseInt(port), Integer.parseInt(systemPort), username, password, easyPin)};
	}

	@DataProvider(name="login_freshDevice")
	private static Object[][] loginFreshDeviceDataProvider() throws FileNotFoundException {
		Object[][] dataArray = LoadTestCase.loadFromFile("Onboarding/Login_FreshDevice.txt",6);
		return dataArray;
	}
}
