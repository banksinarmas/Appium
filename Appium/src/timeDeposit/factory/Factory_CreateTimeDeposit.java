package timeDeposit.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import timeDeposit.CreateTimeDeposit;

public class Factory_CreateTimeDeposit {

	@Factory(dataProvider="createTD")
	private Object[] createTimeDepositCreateInstances(String sourceAccount, String amount,String term,String tdType) throws IOException {
		return new Object[] {new CreateTimeDeposit(sourceAccount,amount,term,tdType)};
	}

	@DataProvider(name="createTD")
	private static Object[][] createTimeDepositDataProvider() throws FileNotFoundException {
		Object[][] dataArray = LoadTestCase.loadFromFile("TimeDeposit/CreateTimeDeposit.txt",4);
		return dataArray;
	}
}
