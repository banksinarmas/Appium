package userprofile.single;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import userprofile.ChangeEasyPin;

public class Single_ChangeEasyPin {

	@Factory(dataProvider="changeEasyPin")
	private Object[] changeEasyPinCreateInstances(String username) throws IOException {
		return new Object[] {new ChangeEasyPin(username)};
	}

	@DataProvider(name="changeEasyPin")
	private static Object[][] changeEasyPinDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("UserProfile/Single/Single_ChangeEasyPin.txt");
		return dataArray;
	}
}
