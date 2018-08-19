package billpayment;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import components.BillPayment_component;
import components.EasyPin_component;
import framework.DeviceCapabilities;
import framework.LoadProperties;

public class Block1_Water extends DeviceCapabilities{
	
	private String billerName,subscriberNo,sourceAccount;

	public Block1_Water() throws IOException {
		Properties prop = LoadProperties.getProperties("billpayment.properties");
		billerName=prop.getProperty("block1_billerName");
		subscriberNo=prop.getProperty("block1_subscriberNo");
		sourceAccount=prop.getProperty("block1_sourceAccount");
	}
	
	public Block1_Water(String billerName,String subscriberNo,String sourceAccount) {
		this.billerName=billerName;
		this.subscriberNo=subscriberNo;
		this.sourceAccount=sourceAccount;
	}
	
	@Test
	private void Test01_After_Login_Page() throws Exception
	{
		BillPayment_component.waterPayment_billerMenu();
	}

	@Test(dependsOnMethods="Test01_After_Login_Page")
	private void Test02_Inquiry_Page() throws Exception
	{		
		BillPayment_component.waterPayment_selectMerchantSubscriberNo(billerName, subscriberNo);
	}

	@Test(dependsOnMethods="Test02_Inquiry_Page")
	private void Test03_Select_Account_Page() throws Exception
	{
		BillPayment_component.block1_selectAccount(sourceAccount);
	}

	@Test(dependsOnMethods="Test03_Select_Account_Page")
	private void Test04_Summary_Page() throws Exception
	{
		BillPayment_component.summary();
	}

	@Test(dependsOnMethods="Test04_Summary_Page")
	private void Test05_Block1_Water_EasyPin_Page() throws Exception
	{
		EasyPin_component.input(easyPin);

	}
	@Test(dependsOnMethods="Test05_Block1_Water_EasyPin_Page")
	private void Test06_Block1_Water_Result_Page() throws Exception
	{
		BillPayment_component.result();
		
	}

}
