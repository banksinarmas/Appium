package billpayment;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import components.BillPayment_component;
import components.EasyPin_component;
import components.OTP_component;
import framework.DeviceCapabilities;
import framework.LoadProperties;

public class Block2_Baznas extends DeviceCapabilities{

	private String sourceAccount,subscriberNo,amount,desc,billerName;

	public Block2_Baznas() throws IOException {
		Properties prop = LoadProperties.getProperties("billpayment.properties");
		this.billerName=prop.getProperty("block2_billerName");
		this.sourceAccount=prop.getProperty("block2_sourceAccount");
		this.subscriberNo=prop.getProperty("block2_subscriberNo");
		this.amount=prop.getProperty("block2_amount");
		this.desc=prop.getProperty("block2_desc");
	}
	public Block2_Baznas(String billerName,String subscriberNo,String sourceAccount,String amount,String desc) {
		this.billerName=billerName;
		this.sourceAccount=sourceAccount;
		this.subscriberNo=subscriberNo;
		this.amount=amount;
		this.desc=desc;
	}

	@Test
	private void Test01_After_Login_Page() throws Exception
	{
		BillPayment_component.other_billerMenu(billerName);
	}

	@Test(dependsOnMethods="Test01_After_Login_Page")
	private void Test02_Inquiry_Page() throws Exception
	{		
		BillPayment_component.inputSubscriberNo(subscriberNo);
	}

	@Test(dependsOnMethods="Test02_Inquiry_Page")
	private void Test03_Select_Account_Page() throws Exception
	{
		BillPayment_component.block2_selectAccount(sourceAccount, amount, desc);
	}

	@Test(dependsOnMethods="Test03_Select_Account_Page")
	private void Test04_Summary_Page() throws Exception
	{
		BillPayment_component.summary();
	}

	@Test(dependsOnMethods="Test04_Summary_Page")
	private void Test05_Block2_Baznas_EasyPin_Page() throws Exception
	{
		if(Long.parseLong(amount)>5000000)
			OTP_component.input();
		else
			EasyPin_component.input(easyPin);

	}
	@Test(dependsOnMethods="Test05_Block2_Baznas_EasyPin_Page")
	private void Test06_Block2_Baznas_Result_Page() throws Exception
	{
		BillPayment_component.result();
	}

}
