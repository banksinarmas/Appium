package timeDeposit.single;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import timeDeposit.CreateTimeDeposit;

public class Single_CreateTimeDeposit {
	
	@Factory(dataProvider="createTD")
	private Object[] createTimeDepositCreateInstances(String fromAccountType,String amount,String term,String tdType) throws IOException {
		return new Object[] {new CreateTimeDeposit(fromAccountType,amount,term,tdType)};
	}

	@DataProvider(name="createTD")
	private static Object[][] createTimeDepositDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("TimeDeposit/Single/Single_CreateTimeDeposit.txt");
		return dataArray;
	}
}
