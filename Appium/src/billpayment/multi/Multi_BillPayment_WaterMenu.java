package billpayment.multi;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import billpayment.BillPayment_WaterMenu;
import framework.LoadTestCase;

public class Multi_BillPayment_WaterMenu {

	@Factory(dataProvider="bp_watermenu")
	private Object[] bpWaterMenuCreateInstances(String username,String fromAccountType,String billerName,String subscriberNo,String desc) throws IOException {
		return new Object[] {new BillPayment_WaterMenu(username,fromAccountType,billerName,subscriberNo,desc)};
	}

	@DataProvider(name="bp_watermenu")
	private static Object[][] bpWaterMenuDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("BillPayment/Multi/Multi_BillPayment_WaterMenu.txt");
		return dataArray;
	}
}
