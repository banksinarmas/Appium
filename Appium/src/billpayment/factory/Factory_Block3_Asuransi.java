package billpayment.factory;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import billpayment.Block3_Asuransi;
import framework.LoadTestCase;

public class Factory_Block3_Asuransi {

	@Factory(dataProvider="block3")
	private Object[] block3CreateInstances(String username,String fromAccountType,String billerName,String subscriberNo,String amount,String desc) throws IOException {
		return new Object[] {new Block3_Asuransi(username,fromAccountType,billerName,subscriberNo,amount,desc)};
	}

	@DataProvider(name="block3")
	private static Object[][] block3DataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("BillPayment/Block3_Asuransi.txt");
		return dataArray;
	}
}
