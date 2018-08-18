package fundtransfer;

import org.testng.annotations.Test;

import components.EasyPin_component;
import components.FundTransfer_component;
import components.Onboarding_component;
import framework.DeviceCapabilities;

public class Schedule_TransferInbank extends DeviceCapabilities{
	
	String sourceAccount = "0008394903";
	String toAccount="0171029279";
	String easyPin="123456";
	String amount="1000";
	String desc="sch inbank";
	String recurrence="everyday";
	String frequency="5x";
	
	@Test
	public void Test00_Login_Page() throws Exception
	{
		Onboarding_component.loginEasyPin(easyPin);		
	}

	@Test(dependsOnMethods="Test00_Login_Page")
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
		EasyPin_component.input(easyPin);

	}
	@Test(dependsOnMethods="Test06_Schedule_Transfer_Inbank_EasyPin_Page")
	public void Test07_Schedule_Transfer_Inbank_Result_Page() throws Exception
	{
		FundTransfer_component.result();
	}
	
}
