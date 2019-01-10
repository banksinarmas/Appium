package timeDeposit;

import java.io.IOException;
import java.lang.reflect.Method;
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
	
	private String easyPin,fromAccountType,fromAccount,amount,term,tdType;
	
	public CreateTimeDeposit() throws IOException {
		
		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_AMOUNT"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_TERM"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_TYPE"));
			
	}
	
	public CreateTimeDeposit(String username,String fromAccountType,String amount,String term,String tdType) throws IOException {
		
		super(false,username);
		
		Properties prop = LoadProperties.getUserProperties(username);
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
	private void Test01_Login(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		easyPin_comp.loginEasyPin(easyPin);
	}
	
	@Test(dependsOnMethods="Test01_Login")
	private void Test02_After_Login_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		timeDeposit_comp.timeDepositMenu();
	}

	@Test(dependsOnMethods="Test02_After_Login_Page")
	private void Test03_Time_Deposit_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		timeDeposit_comp.createTimeDepositOnline(fromAccount, amount, term, tdType);
	}

	@Test(dependsOnMethods="Test03_Time_Deposit_Page")
	private void Test04_Time_Deposit_TnC_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		timeDeposit_comp.termAndCondition();
	}

	@Test(dependsOnMethods="Test04_Time_Deposit_TnC_Page")
	private void Test05_Time_Deposit_Summary_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		timeDeposit_comp.summary();
	}

	@Test(dependsOnMethods="Test05_Time_Deposit_Summary_Page")
	private void Test06_Time_Deposit_EasyPin_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		easyPin_comp.inputEasyPin(easyPin);
	}
	@Test(dependsOnMethods="Test06_Time_Deposit_EasyPin_Page")
	private void Test07_Time_Deposit_Result_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		timeDeposit_comp.result(fromAccountType);
	}

}
