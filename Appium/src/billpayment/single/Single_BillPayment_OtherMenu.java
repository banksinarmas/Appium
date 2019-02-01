package billpayment.single;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import billpayment.BillPayment_OtherMenu;
import framework.LoadTestCase;

public class Single_BillPayment_OtherMenu {

	@Factory(dataProvider="bp_othermenu")
	private Object[] bpOtherMenuCreateInstances(String fromAccountType,String billerName,String subscriberNo,String amount,String desc) throws IOException {
		return new Object[] {new BillPayment_OtherMenu(fromAccountType,billerName,subscriberNo,amount,desc)};
	}

	@DataProvider(name="bp_othermenu")
	private static Object[][] bpOtherMenuDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("BillPayment/Single/Single_BillPayment_OtherMenu.txt");
		return dataArray;
	}
}
