package fundtransfer.multi;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import fundtransfer.TransferOtherbank;

public class Multi_TransferOtherbank {

	@Factory(dataProvider="otbank")
	private Object[] otbankCreateInstances(String username,String fromAccountType,String toAccountType,String transferMethod,String amount,String desc) throws IOException {
		return new Object[] {new TransferOtherbank(username,fromAccountType,toAccountType,transferMethod,amount,desc)};
	}

	@DataProvider(name="otbank")
	private static Object[][] otbankDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("FundTransfer/Multi/Multi_TransferOtherbank.txt");
		return dataArray;
	}
}
