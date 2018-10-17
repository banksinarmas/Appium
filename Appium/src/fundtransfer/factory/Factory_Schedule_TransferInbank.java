package fundtransfer.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import fundtransfer.Schedule_TransferInbank;

public class Factory_Schedule_TransferInbank {
	
	@Factory(dataProvider="schinbank")
	private Object[] schinbankCreateInstances(String deviceID,String port,String systemPort,String sourceAccount, String toAccount,String amount,String desc,String recurrence,String frequency) throws IOException {
		return new Object[] {new Schedule_TransferInbank(deviceID,Integer.parseInt(port),Integer.parseInt(systemPort),sourceAccount,toAccount,amount,desc,recurrence,frequency)};
	}

	@DataProvider(name="schinbank")
	private static Object[][] schinbankDataProvider() throws FileNotFoundException {
		Object[][] dataArray = LoadTestCase.loadFromFile("FundTransfer/Schedule_TransferInbank.txt",9);
		return dataArray;
	}
}
