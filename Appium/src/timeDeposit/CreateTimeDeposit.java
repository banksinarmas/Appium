package timeDeposit;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.EasyPin_component;
import components.TimeDeposit_component;
import framework.DeviceSetup;
import framework.LoadProperties;

public class CreateTimeDeposit extends DeviceSetup {

	private EasyPin_component easyPin_comp;
	private TimeDeposit_component timeDeposit_comp;

	private String username,easyPin,fromAccountType,fromAccount,amount,term,tdType;

	public CreateTimeDeposit() throws IOException {

		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_AMOUNT"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_TERM"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_TYPE"));		
	}

	public CreateTimeDeposit(String fromAccountType,String amount,String term,String tdType) throws IOException {
		this(DEFAULT_PROPERTIES.getProperty("DEF_AUTOMATION_USERNAME"),fromAccountType,amount,term,tdType);
	}

	public CreateTimeDeposit(String username,String fromAccountType,String amount,String term,String tdType) throws IOException {

		super(false,username);
		this.username=username;

		Properties prop = LoadProperties.getUserProperties(this.username);
		this.easyPin=prop.getProperty("EASYPIN");
		this.fromAccountType=fromAccountType;
		this.fromAccount=prop.getProperty(fromAccountType);
		this.amount=amount;
		this.term=term;
		this.tdType=tdType;

	}

	@BeforeClass
	private void loadComponent(){

		easyPin_comp= new EasyPin_component(driver);
		timeDeposit_comp= new TimeDeposit_component(driver);
	}

	@Test
	private void Test01_Login() throws Exception
	{	

		easyPin_comp.loginEasyPin(easyPin);
	}

	@Test(dependsOnMethods="Test01_Login")
	private void Test02_Time_Deposit_Dashboard() throws Exception
	{

		timeDeposit_comp.dashboard();
	}

	@Test(dependsOnMethods="Test02_Time_Deposit_Dashboard")
	private void Test03_Create_Time_Deposit_Page() throws Exception
	{

		timeDeposit_comp.createTimeDeposit(fromAccount, amount, term, tdType);
	}

	@Test(dependsOnMethods="Test03_Create_Time_Deposit_Page")
	private void Test04_Term_and_Condition_Page() throws Exception
	{

		timeDeposit_comp.termAndCondition();
	}

	@Test(dependsOnMethods="Test04_Term_and_Condition_Page")
	private void Test05_Summary_Page() throws Exception
	{

		timeDeposit_comp.summary();
	}

	@Test(dependsOnMethods="Test05_Summary_Page")
	private void Test06_EasyPin_Page() throws Exception
	{

		easyPin_comp.inputEasyPin(easyPin);
	}
	@Test(dependsOnMethods="Test06_EasyPin_Page")
	private void Test07_Result_Page() throws Exception
	{

		timeDeposit_comp.result(fromAccountType);
	}

}
