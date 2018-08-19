package timeDeposit;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import components.EasyPin_component;
import components.TimeDeposit_component;
import framework.DeviceCapabilities;
import framework.LoadProperties;

public class CreateTimeDeposit extends DeviceCapabilities {

	private String sourceAccount,amount,term,tdType;

	public CreateTimeDeposit() throws IOException {
		Properties prop = LoadProperties.getProperties("timedeposit.properties");
		this.sourceAccount=prop.getProperty("sourceAccount");
		this.amount=prop.getProperty("amount");
		this.term=prop.getProperty("term");
		this.tdType=prop.getProperty("tdType");
		
	}
	
	public CreateTimeDeposit(String sourceAccount,String amount,String term,String tdType) {
		this.sourceAccount=sourceAccount;
		this.amount=amount;
		this.term=term;
		this.tdType=tdType;
	}
	
	@Test
	private void Test01_After_Login_Page() throws Exception
	{
		TimeDeposit_component.timeDepositMenu();
	}

	@Test(dependsOnMethods="Test01_After_Login_Page")
	private void Test02_Time_Deposit_Page() throws Exception
	{
		TimeDeposit_component.createTimeDepositOnline(sourceAccount, amount, term, tdType);
	}

	@Test(dependsOnMethods="Test02_Time_Deposit_Page")
	private void Test03_Time_Deposit_TnC_Page() throws Exception
	{
		TimeDeposit_component.termAndCondition();
	}

	@Test(dependsOnMethods="Test03_Time_Deposit_TnC_Page")
	private void Test04_Time_Deposit_Summary_Page() throws Exception
	{
		TimeDeposit_component.summary();
	}

	@Test(dependsOnMethods="Test04_Time_Deposit_Summary_Page")
	private void Test05_Time_Deposit_EasyPin_Page() throws Exception
	{
		EasyPin_component.input(easyPin);

	}
	@Test(dependsOnMethods="Test05_Time_Deposit_EasyPin_Page")
	private void Test06_Time_Deposit_Result_Page() throws Exception
	{
		TimeDeposit_component.result();

	}

}
