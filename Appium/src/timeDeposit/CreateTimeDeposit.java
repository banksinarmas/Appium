package timeDeposit;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.EasyPin_component;
import components.TimeDeposit_component;
import framework.LoadProperties;
import framework.LockdownDevice;

public class CreateTimeDeposit extends LockdownDevice {

	private EasyPin_component easyPin_comp;
	private TimeDeposit_component timeDeposit_comp;
	
	private String sourceAccount,amount,term,tdType;
	
	private String FOLDER = "/TimeDeposit/Create/"+deviceID;

	public CreateTimeDeposit() throws IOException {
		
		super();
		Properties prop = LoadProperties.getProperties("timedeposit.properties");
		this.sourceAccount=prop.getProperty("sourceAccount");
		this.amount=prop.getProperty("amount");
		this.term=prop.getProperty("term");
		this.tdType=prop.getProperty("tdType");
		
	}
	
	public CreateTimeDeposit(String deviceID,int port,int systemPort,String sourceAccount,String amount,String term,String tdType) {
		
		super(deviceID,port,systemPort);
		this.sourceAccount=sourceAccount;
		this.amount=amount;
		this.term=term;
		this.tdType=tdType;
	}
	
	@BeforeClass
	private void loadComponent(){
		
		easyPin_comp= new EasyPin_component(driver);
		timeDeposit_comp= new TimeDeposit_component(driver);
		FOLDER=apkVersion+FOLDER;
	}
	
	@Test
	private void Login(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		easyPin_comp.loginEasyPin(easyPin);
	}
	
	@Test(dependsOnMethods="Login")
	private void After_Login_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		timeDeposit_comp.timeDepositMenu();
	}

	@Test(dependsOnMethods="After_Login_Page")
	private void Test01_Time_Deposit_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		timeDeposit_comp.createTimeDepositOnline(FOLDER,method.getName(),sourceAccount, amount, term, tdType);
	}

	@Test(dependsOnMethods="Test01_Time_Deposit_Page")
	private void Test02_Time_Deposit_TnC_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		timeDeposit_comp.termAndCondition(FOLDER,method.getName());
	}

	@Test(dependsOnMethods="Test02_Time_Deposit_TnC_Page")
	private void Test03_Time_Deposit_Summary_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		timeDeposit_comp.summary(FOLDER,method.getName());
	}

	@Test(dependsOnMethods="Test03_Time_Deposit_Summary_Page")
	private void Test04_Time_Deposit_EasyPin_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		easyPin_comp.input(FOLDER,method.getName(),easyPin);
	}
	@Test(dependsOnMethods="Test04_Time_Deposit_EasyPin_Page")
	private void Test05_Time_Deposit_Result_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		timeDeposit_comp.result(FOLDER,method.getName());
	}

}
