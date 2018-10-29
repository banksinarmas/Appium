package userprofile.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import userprofile.ChangeEasyPin;

public class Factory_ChangeEasyPin {

	@Factory(dataProvider="changeEasyPin")
	private Object[] changeEasyPinCreateInstances(String deviceID,String port,String systemPort,String newEasyPin,String currentPassword) throws IOException {
		return new Object[] {new ChangeEasyPin(deviceID,Integer.parseInt(port),Integer.parseInt(systemPort),newEasyPin,currentPassword)};
	}

	@DataProvider(name="changeEasyPin")
	private static Object[][] changeEasyPinDataProvider() throws FileNotFoundException {
		Object[][] dataArray = LoadTestCase.loadFromFile("UserProfile/ChangeEasyPin.txt",5);
		return dataArray;
	}
}
