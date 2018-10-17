package billpayment.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import billpayment.Block3_Asuransi;
import framework.LoadTestCase;

public class Factory_Block3_Asuransi {

	@Factory(dataProvider="block3")
	private Object[] block3CreateInstances(String billerName, String subscriberNo,String sourceAccount,String amount,String desc) throws IOException {
		return new Object[] {new Block3_Asuransi(billerName,billerName,sourceAccount,amount,desc)};
	}

	@DataProvider(name="block3")
	private static Object[][] block3DataProvider() throws FileNotFoundException {
		Object[][] dataArray = LoadTestCase.loadFromFile("BillPayment/Block3_Asuransi.txt",5);
		return dataArray;
	}
}