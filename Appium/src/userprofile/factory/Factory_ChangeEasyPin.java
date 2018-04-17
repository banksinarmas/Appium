package userprofile.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadDeviceTest;
import userprofile.ChangeEasyPin;

public class Factory_ChangeEasyPin {

	@Factory(dataProvider="deviceList")
	private Object[] aetraCreateInstances(String deviceName,String udid,String systemPort) throws IOException {
		return new Object[] {new ChangeEasyPin(deviceName,udid,systemPort)};
	}

	@DataProvider(name="deviceList")
	private static Object[][] aetraDataProvider() throws FileNotFoundException {
		Object[][] dataArray = LoadDeviceTest.loadFromFile("deviceList.txt",3);
		return dataArray;
	}
}
