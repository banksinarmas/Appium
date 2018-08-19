package fundtransfer;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import components.EasyPin_component;
import components.FundTransfer_component;
import components.OTP_component;
import framework.DeviceCapabilities;
import framework.LoadProperties;

public class Schedule_TransferInbank extends DeviceCapabilities{
	
/*	String sourceAccount = "0008394903";
	String toAccount="0171029279";
	String easyPin="123456";
	String amount="1000";
	String desc="sch inbank";
	String recurrence="everyday";
	String frequency="5x";*/
	
	private String sourceAccount,toAccount,amount,desc,recurrence,frequency;	
	
	public Schedule_TransferInbank() throws IOException {
		Properties prop=LoadProperties.getProperties("fundtransfer.properties");
		this.sourceAccount=prop.getProperty("schInbankSourceAccount");
		this.toAccount=prop.getProperty("schInbankToAccount");
		this.amount=prop.getProperty("schInbankAmount");
		this.desc=prop.getProperty("schInbankDesc");
		this.recurrence=prop.getProperty("schInbankRecurrence");
		this.frequency=prop.getProperty("schInbankFrequency");
	}
	
	public Schedule_TransferInbank(String sourceAccount,String toAccount,String amount,String desc,String recurrence,String frequency) throws IOException {
		this.sourceAccount=sourceAccount;
		this.toAccount=toAccount;
		this.amount=amount;
		this.desc=desc;	
		this.recurrence=recurrence;
		this.frequency=frequency;
	}
	

	@Test
	public void Test01_After_Login_Page() throws Exception
	{
		FundTransfer_component.fundTransferMenu();
	}
	
	@Test(dependsOnMethods="Test01_After_Login_Page")
	public void Test02_Select_Payee_Page() throws Exception
	{
		FundTransfer_component.selectPayee(toAccount);
	}

	@Test(dependsOnMethods="Test02_Select_Payee_Page")
	public void Test03_Select_Account_Page() throws Exception
	{
		FundTransfer_component.selectAccountSchedule(sourceAccount,amount,desc);
	}

	
	@Test(dependsOnMethods="Test03_Select_Account_Page")
	public void Test04_Schedule_Page() throws Exception
	{
		
		FundTransfer_component.selectSchedule(recurrence,frequency);
	}
	
	@Test(dependsOnMethods="Test04_Schedule_Page")
	public void Test05_Summary_Page() throws Exception
	{
		FundTransfer_component.summarySchedule();
	}

	@Test(dependsOnMethods="Test05_Summary_Page")
	public void Test06_Schedule_Transfer_Inbank_EasyPin_Page() throws Exception
	{	
		if(Long.parseLong(amount)>5000000)
			OTP_component.input();
		else
			EasyPin_component.input(easyPin);

	}
	@Test(dependsOnMethods="Test06_Schedule_Transfer_Inbank_EasyPin_Page")
	public void Test07_Schedule_Transfer_Inbank_Result_Page() throws Exception
	{
		FundTransfer_component.result();
	}
	
}
