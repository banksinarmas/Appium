package cardlessWithdrawal;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.CardlessWithdrawal_component;
import components.EasyPin_component;
import framework.LoadProperties;
import framework.LockdownDevice;

public class CardlessWithdrawal  extends LockdownDevice{

	private String sourceAccount,phoneNo,amount,desc;	
	private EasyPin_component easyPin_comp;
	private CardlessWithdrawal_component cardlessWithdrawal_comp;
	
	private final String FOLDER = "CardlessWithdrawal/"+deviceID;

	public CardlessWithdrawal() throws IOException {
		super();
		Properties prop = LoadProperties.getProperties("cardlesswithdrawal.properties");
		sourceAccount=prop.getProperty("sourceAccount");
		phoneNo=prop.getProperty("phoneNo");
		amount=prop.getProperty("amount");
		desc=prop.getProperty("desc");	
	}
	
	public CardlessWithdrawal(String deviceID,int port,int systemPort,String sourceAccount,String phoneNo,String amount,String desc)
	{
		super(deviceID,port,systemPort);
		this.sourceAccount=sourceAccount;
		this.phoneNo=phoneNo;
		this.amount=amount;
		this.desc=desc;
	}
	
	@BeforeClass
	private void loadComponent() {
		easyPin_comp= new EasyPin_component(driver);
		cardlessWithdrawal_comp=new CardlessWithdrawal_component(driver);
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
		cardlessWithdrawal_comp.cardlessWdrMenu();
	}

	@Test(dependsOnMethods="After_Login_Page")
	public void Test01_Select_Payee_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		cardlessWithdrawal_comp.selectPhoneNo(FOLDER,method.getName(),phoneNo);
	}

	@Test(dependsOnMethods="Test01_Select_Payee_Page")
	public void Test02_Select_Account_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		cardlessWithdrawal_comp.selectAccount(FOLDER,method.getName(),sourceAccount, amount, desc);
	}

	@Test(dependsOnMethods="Test02_Select_Account_Page")
	public void Test03_Summary_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		cardlessWithdrawal_comp.summary(FOLDER,method.getName());
	}

	@Test(dependsOnMethods="Test03_Summary_Page")
	public void Test04_CardlessWdr_EasyPin_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		easyPin_comp.input(FOLDER,method.getName(),easyPin);
	}
	
	@Test(dependsOnMethods="Test04_CardlessWdr_EasyPin_Page")
	public void Test05_CardlessWdr_Result_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		cardlessWithdrawal_comp.result(FOLDER,method.getName());
	}

}
