package fundtransfer.single;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import fundtransfer.Schedule_TransferOtherbank;

public class Single_Schedule_TransferOtherbank {
	
	
	@Factory(dataProvider="schotbank")
	private Object[] schotbankCreateInstances(String fromAccountType,String toAccountType,String transferMethod,String amount,String desc,String recurrence,String frequency) throws IOException {
		return new Object[] {new Schedule_TransferOtherbank(fromAccountType,toAccountType,transferMethod,amount,desc,recurrence,frequency)};
	}

	@DataProvider(name="schotbank")
	private static Object[][] schotbankDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("FundTransfer/Single/Single_Schedule_TransferOtherbank.txt");
		return dataArray;
	}
}
