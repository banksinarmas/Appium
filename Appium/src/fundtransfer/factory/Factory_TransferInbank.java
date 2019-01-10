package fundtransfer.factory;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import fundtransfer.TransferInbank;

public class Factory_TransferInbank {

	@Factory(dataProvider="inbank" )
	private Object[] inbankCreateInstances(String username,String fromAccountType,String toAccountType,String amount,String desc) throws IOException {
		return new Object[] {new TransferInbank(username,fromAccountType,toAccountType,amount,desc)};
	}

	@DataProvider(name="inbank")
	private static Object[][] inbankDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("FundTransfer/test.txt");
		return dataArray;
	}

}
