package userprofile;

import org.testng.annotations.Test;

import components.CardlessWithdrawal_component;
import components.EasyPin_component;
import components.Onboarding_component;
import framework.DeviceCapabilities;

public class CardlessWithdrawal extends DeviceCapabilities{

	String easyPin="123456";
	String sourceAccount="0008394903";
	String amount="100000";
	String desc="cardlesswdr";
	String phoneNo="087783088806";
	
	@Test
	public void Test00_Login_Page() throws Exception
	{
		Onboarding_component.loginEasyPin(easyPin);
	}

	@Test(dependsOnMethods="Test00_Login_Page")
	public void Test01_After_Login_Page() throws Exception
	{
		CardlessWithdrawal_component.cardlessMenu();
	}
	
	@Test(dependsOnMethods="Test01_After_Login_Page")
	public void Test02_Select_Payee_Page() throws Exception
	{
		CardlessWithdrawal_component.selectPhoneNo(phoneNo);
	}

	@Test(dependsOnMethods="Test02_Select_Payee_Page")
	public void Test03_Select_Account_Page() throws Exception
	{
		CardlessWithdrawal_component.selectAccount(sourceAccount, amount, desc);
	}
	
	@Test(dependsOnMethods="Test03_Select_Account_Page")
	public void Test04_Summary_Page() throws Exception
	{
		CardlessWithdrawal_component.summary();
	}

	@Test(dependsOnMethods="Test04_Summary_Page")
	public void Test05_Transfer_Inbank_EasyPin_Page() throws Exception
	{
		EasyPin_component.input(easyPin);

	}
	@Test(dependsOnMethods="Test05_Transfer_Inbank_EasyPin_Page")
	public void Test06_Transfer_Inbank_Result_Page() throws Exception
	{
		CardlessWithdrawal_component.result();
	}

}
