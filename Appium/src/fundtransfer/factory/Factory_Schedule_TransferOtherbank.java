package fundtransfer.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import fundtransfer.Schedule_TransferOtherbank;

public class Factory_Schedule_TransferOtherbank {

	@Factory(dataProvider="schotbank")
	private Object[] schotbankCreateInstances(String sourceAccount, String toAccount,String amount,String desc,String method,String recurrence,String frequency) throws IOException {
		return new Object[] {new Schedule_TransferOtherbank(sourceAccount,toAccount,amount,desc,method,recurrence,frequency)};
	}

	@DataProvider(name="schotbank")
	private static Object[][] schotbankDataProvider() throws FileNotFoundException {
		Object[][] dataArray = LoadTestCase.loadFromFile("FundTransfer/Schedule_TransferOtherbank.txt",7);
		return dataArray;
	}
}
