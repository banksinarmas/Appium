package billpayment.factory;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import billpayment.Block2_Baznas;
import framework.LoadTestCase;

public class Factory_Block2_Baznas {
	
	@Factory(dataProvider="block2")
	private Object[] block2CreateInstances(String username,String fromAccountType,String billerName,String subscriberNo,String amount,String desc) throws IOException {
		return new Object[] {new Block2_Baznas(username,fromAccountType,billerName,subscriberNo,amount,desc)};
	}

	@DataProvider(name="block2")
	private static Object[][] block2DataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("BillPayment/Block2_Baznas.txt");
		return dataArray;
	}
}
