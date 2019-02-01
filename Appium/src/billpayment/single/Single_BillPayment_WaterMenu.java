package billpayment.single;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import billpayment.BillPayment_WaterMenu;
import framework.LoadTestCase;

public class Single_BillPayment_WaterMenu {

	@Factory(dataProvider="bp_watermenu")
	private Object[] bpWaterMenuCreateInstances(String fromAccountType,String billerName,String subscriberNo,String desc) throws IOException {
		return new Object[] {new BillPayment_WaterMenu(fromAccountType,billerName,subscriberNo,desc)};
	}

	@DataProvider(name="bp_watermenu")
	private static Object[][] bpWaterMenuDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("BillPayment/Single/Single_BillPayment_WaterMenu.txt");
		return dataArray;
	}
}
