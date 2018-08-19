package fundtransfer;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import components.EasyPin_component;
import components.FundTransfer_component;
import components.OTP_component;
import framework.DeviceCapabilities;
import framework.LoadProperties;

public class Schedule_TransferOtherbank  extends DeviceCapabilities{

	private String sourceAccount,toAccount,amount,desc,method,recurrence,frequency;	
	
	public Schedule_TransferOtherbank() throws IOException {
		Properties prop=LoadProperties.getProperties("fundtransfer.properties");
		this.sourceAccount=prop.getProperty("schOtbankSourceAccount");
		this.toAccount=prop.getProperty("schOtbankToAccount");
		this.amount=prop.getProperty("schOtbankAmount");
		this.desc=prop.getProperty("schOtbankDesc");
		this.method=prop.getProperty("schOtbankDesc");
		this.recurrence=prop.getProperty("schOtbankRecurrence");
		this.frequency=prop.getProperty("schOtbankFrequency");
	}
	
	public Schedule_TransferOtherbank(String sourceAccount,String toAccount,String amount,String desc,String method,String recurrence,String frequency) throws IOException {
		this.sourceAccount=sourceAccount;
		this.toAccount=toAccount;
		this.amount=amount;
		this.desc=desc;	
		this.method=method;
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
		FundTransfer_component.selectAccountSchedule(sourceAccount, amount, desc);
	}

	
	@Test(dependsOnMethods="Test03_Select_Account_Page")
	public void Test04_Schedule_Page() throws Exception
	{
		FundTransfer_component.selectSchedule(recurrence,frequency);
	}
	
	@Test(dependsOnMethods="Test04_Schedule_Page")
	public void Test05_Transfer_Method_Page() throws Exception
	{
		FundTransfer_component.selectTransferMethod(method);

	}
	@Test(dependsOnMethods="Test05_Transfer_Method_Page")
	public void Test06_Summary_Page() throws Exception
	{
		FundTransfer_component.summarySchedule();
	}

	@Test(dependsOnMethods="Test06_Summary_Page")
	public void Test07_Schedule_Transfer_Otherbank_EasyPin_Page() throws Exception
	{
		if(Long.parseLong(amount)>5000000)
			OTP_component.input();
		else
			EasyPin_component.input(easyPin);

	}
	@Test(dependsOnMethods="Test07_Schedule_Transfer_Otherbank_EasyPin_Page")
	public void Test08_Schedule_Transfer_Otherbank_Result_Page() throws Exception
	{
		FundTransfer_component.result();
	}
	

}
