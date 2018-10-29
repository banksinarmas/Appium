package cardlessWithdrawal.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import cardlessWithdrawal.CardlessWithdrawal;
import framework.LoadTestCase;

public class Factory_CardlessWithdrawal {

	@Factory(dataProvider="cardlessWdr")
	private Object[] cardlessCreateInstances(String deviceID,String port, String systemPort,String sourceAccount, String toAccount,String amount,String desc) throws IOException {
		return new Object[] {new CardlessWithdrawal(deviceID,Integer.parseInt(port),Integer.parseInt(systemPort),sourceAccount,toAccount,amount,desc)};
	}

	@DataProvider(name="cardlessWdr")
	private static Object[][] cardlessDataProvider() throws FileNotFoundException {
		Object[][] dataArray = LoadTestCase.loadFromFile("CardlessWithdrawal/CardlessWithdrawal.txt",7);
		return dataArray;
	}



}
