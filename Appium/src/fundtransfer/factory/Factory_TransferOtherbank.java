package fundtransfer.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import fundtransfer.TransferOtherbank;

public class Factory_TransferOtherbank {
	
	@Factory(dataProvider="otbank")
	private Object[] otbankCreateInstances(String method,String sourceAccount, String toAccount,String amount,String desc) throws IOException {
		return new Object[] {new TransferOtherbank(method,sourceAccount,toAccount,amount,desc)};
	}

	@DataProvider(name="otbank")
	private static Object[][] otbankDataProvider() throws FileNotFoundException {
		Object[][] dataArray = LoadTestCase.loadFromFile("FundTransfer/TransferOtherbank.txt",5);
		return dataArray;
	}
}
