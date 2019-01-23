package fundtransfer.multi;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import fundtransfer.Schedule_TransferInbank;

public class Multi_Schedule_TransferInbank {

	@Factory(dataProvider="schinbank")
	private Object[] schinbankCreateInstances(String username,String fromAccountType,String toAccountType,String amount,String desc,String recurrence,String frequency) throws IOException {
		return new Object[] {new Schedule_TransferInbank(username,fromAccountType,toAccountType,amount,desc,recurrence,frequency)};
	}

	@DataProvider(name="schinbank")
	private static Object[][] schinbankDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("FundTransfer/Multi/Multi_Schedule_TransferInbank.txt");
		return dataArray;
	}
}
