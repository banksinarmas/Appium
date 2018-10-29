package billpayment.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import billpayment.Block2_Baznas;
import framework.LoadTestCase;

public class Factory_Block2_Baznas {
	
	@Factory(dataProvider="block2")
	private Object[] block2CreateInstances(String deviceID,String port,String systemPort,String billerName, String subscriberNo,String sourceAccount,String amount,String desc) throws IOException {
		return new Object[] {new Block2_Baznas(deviceID,Integer.parseInt(port),Integer.parseInt(systemPort),billerName,subscriberNo,sourceAccount,amount,desc)};
	}

	@DataProvider(name="block2")
	private static Object[][] block2DataProvider() throws FileNotFoundException {
		Object[][] dataArray = LoadTestCase.loadFromFile("BillPayment/Block2_Baznas.txt",8);
		return dataArray;
	}
}
