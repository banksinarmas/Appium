package billpayment.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import billpayment.Block1_Water;
import framework.LoadTestCase;

public class Factory_Block1_Water {

	@Factory(dataProvider="block1")
	private Object[] block1CreateInstances(String billerName, String subscriberNo,String sourceAccount) throws IOException {
		return new Object[] {new Block1_Water(billerName,billerName,sourceAccount)};
	}

	@DataProvider(name="block1")
	private static Object[][] block1DataProvider() throws FileNotFoundException {
		Object[][] dataArray = LoadTestCase.loadFromFile("BillPayment/Block1_Water.txt",3);
		return dataArray;
	}
}
