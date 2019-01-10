package fundtransfer.factory;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import fundtransfer.Schedule_TransferOtherbank;

public class Factory_Schedule_TransferOtherbank {

	@Factory(dataProvider="schotbank")
	private Object[] schotbankCreateInstances(String username,String fromAccountType,String toAccountType,String transferMethod,String amount,String desc,String recurrence,String frequency) throws IOException {
		return new Object[] {new Schedule_TransferOtherbank(username,fromAccountType,toAccountType,transferMethod,amount,desc,recurrence,frequency)};
	}

	@DataProvider(name="schotbank")
	private static Object[][] schotbankDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("FundTransfer/Schedule_TransferOtherbank.txt");
		return dataArray;
	}
}
