package userprofile;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.CardlessWithdrawal_component;
import components.EasyPin_component;
import framework.DeviceCapabilities;
import framework.LoadProperties;

public class CardlessWithdrawal extends DeviceCapabilities{

	private String sourceAccount,phoneNo,amount,desc;	

	@BeforeClass
	private void config() throws IOException {
		Properties prop = LoadProperties.getProperties("cardlesswithdrawal.properties");
		sourceAccount=prop.getProperty("sourceAccount");
		phoneNo=prop.getProperty("phoneNo");
		amount=prop.getProperty("amount");
		desc=prop.getProperty("desc");
	}

	@Test
	public void Test01_After_Login_Page() throws Exception
	{
		CardlessWithdrawal_component.cardlessWdrMenu();
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
	public void Test05_CardlessWdr_EasyPin_Page() throws Exception
	{
		EasyPin_component.input(easyPin);

	}
	@Test(dependsOnMethods="Test05_Transfer_Inbank_EasyPin_Page")
	public void Test06_CardlessWdr_Result_Page() throws Exception
	{
		CardlessWithdrawal_component.result();
	}

}
