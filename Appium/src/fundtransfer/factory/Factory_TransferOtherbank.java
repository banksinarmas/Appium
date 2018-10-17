package fundtransfer.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import fundtransfer.TransferOtherbank;

public class Factory_TransferOtherbank {
	
	@Factory(dataProvider="otbank")
	private Object[] otbankCreateInstances(String deviceID,String port,String systemPort,String transferMethod,String sourceAccount, String toAccount,String amount,String desc) throws IOException {
		return new Object[] {new TransferOtherbank(deviceID,Integer.parseInt(port),Integer.parseInt(systemPort),transferMethod,sourceAccount,toAccount,amount,desc)};
	}

	@DataProvider(name="otbank")
	private static Object[][] otbankDataProvider() throws FileNotFoundException {
		Object[][] dataArray = LoadTestCase.loadFromFile("FundTransfer/TransferOtherbank.txt",8);
		return dataArray;
	}
}
