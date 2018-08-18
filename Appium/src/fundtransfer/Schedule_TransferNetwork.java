package fundtransfer;

import org.testng.annotations.Test;

import components.EasyPin_component;
import components.FundTransfer_component;
import components.Onboarding_component;
import framework.DeviceCapabilities;

public class Schedule_TransferNetwork extends DeviceCapabilities{

	String sourceAccount = "0008394903";
	String toAccount="001001001";
	String easyPin="123456";
	String amount="10000";
	String desc="sch network";
	String method="network";
	String recurrence="once every two weeks";
	String frequency="3x";

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
	public void Test07_Schedule_Transfer_SKN_EasyPin_Page() throws Exception
	{
		EasyPin_component.input(easyPin);

	}
	@Test(dependsOnMethods="Test07_Schedule_Transfer_SKN_EasyPin_Page")
	public void Test08_Schedule_Transfer_SKN_Result_Page() throws Exception
	{
		FundTransfer_component.result();
	}
}
