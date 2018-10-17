package fundtransfer.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import fundtransfer.Schedule_TransferOtherbank;

public class Factory_Schedule_TransferOtherbank {

	@Factory(dataProvider="schotbank")
	private Object[] schotbankCreateInstances(String deviceID,String port,String systemPort,String sourceAccount, String toAccount,String amount,String desc,String transferMethod,String recurrence,String frequency) throws IOException {
		return new Object[] {new Schedule_TransferOtherbank(deviceID,Integer.parseInt(port),Integer.parseInt(systemPort),sourceAccount,toAccount,amount,desc,transferMethod,recurrence,frequency)};
	}

	@DataProvider(name="schotbank")
	private static Object[][] schotbankDataProvider() throws FileNotFoundException {
		Object[][] dataArray = LoadTestCase.loadFromFile("FundTransfer/Schedule_TransferOtherbank.txt",10);
		return dataArray;
	}
}
