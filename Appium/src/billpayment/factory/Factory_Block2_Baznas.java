package billpayment.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import billpayment.Block2_Baznas;
import framework.LoadTestCase;

public class Factory_Block2_Baznas {
	
	@Factory(dataProvider="block2")
	private Object[] block2CreateInstances(String billerName, String subscriberNo,String sourceAccount,String amount,String desc) throws IOException {
		return new Object[] {new Block2_Baznas(billerName,billerName,sourceAccount,amount,desc)};
	}

	@DataProvider(name="block2")
	private static Object[][] block2DataProvider() throws FileNotFoundException {
		Object[][] dataArray = LoadTestCase.loadFromFile("BillPayment/Block3_Asuransi.txt",5);
		return dataArray;
	}
}
