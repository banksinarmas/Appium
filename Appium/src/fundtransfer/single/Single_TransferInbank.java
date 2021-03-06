package fundtransfer.single;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import fundtransfer.TransferInbank;

public class Single_TransferInbank {

	
	@Factory(dataProvider="inbank" )
	private Object[] inbankCreateInstances(String fromAccountType,String toAccountType,String amount,String desc) throws IOException {
		return new Object[] {new TransferInbank(fromAccountType,toAccountType,amount,desc)};
	}

	@DataProvider(name="inbank")
	private static Object[][] inbankDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("FundTransfer/Single/Single_TransferInbank.txt");
		return dataArray;
	}
}
