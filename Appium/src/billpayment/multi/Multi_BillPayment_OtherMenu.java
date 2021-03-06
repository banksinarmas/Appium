package billpayment.multi;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import billpayment.BillPayment_OtherMenu;
import framework.LoadTestCase;

public class Multi_BillPayment_OtherMenu {

	@Factory(dataProvider="bp_othermenu")
	private Object[] bpOtherMenuCreateInstances(String username,String fromAccountType,String billerName,String subscriberNo,String amount,String desc) throws IOException {
		return new Object[] {new BillPayment_OtherMenu(username,fromAccountType,billerName,subscriberNo,amount,desc)};
	}

	@DataProvider(name="bp_othermenu")
	private static Object[][] bpOtherMenuDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("BillPayment/Multi/Multi_BillPayment_OtherMenu.txt");
		return dataArray;
	}
}
