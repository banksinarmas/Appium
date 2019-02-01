package cardlesswithdrawal.single;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import cardlesswithdrawal.CardlessWithdrawal;
import framework.LoadTestCase;

public class Single_CardlessWithdrawal {

	
	@Factory(dataProvider="cardlessWdr")
	private Object[] cardlessCreateInstances(String fromAccountType,String amount,String desc) throws IOException {
		return new Object[] {new CardlessWithdrawal(fromAccountType,amount,desc)};
	}

	@DataProvider(name="cardlessWdr")
	private static Object[][] cardlessDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("CardlessWithdrawal/Single/Single_CardlessWithdrawal.txt");
		return dataArray;
	}

}
