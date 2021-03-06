package timeDeposit.multi;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import timeDeposit.CreateTimeDeposit;

public class Multi_CreateTimeDeposit {

	@Factory(dataProvider="createTD")
	private Object[] createTimeDepositCreateInstances(String username,String fromAccountType,String amount,String term,String tdType) throws IOException {
		return new Object[] {new CreateTimeDeposit(username,fromAccountType,amount,term,tdType)};
	}

	@DataProvider(name="createTD")
	private static Object[][] createTimeDepositDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("TimeDeposit/Multi/Multi_CreateTimeDeposit.txt");
		return dataArray;
	}
}
