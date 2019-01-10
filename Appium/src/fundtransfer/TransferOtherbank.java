package fundtransfer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.EasyPin_component;
import components.FundTransfer_component;
import components.OTP_component;
import framework.DeviceSetup;
import framework.LoadProperties;

public class TransferOtherbank extends DeviceSetup{

	private EasyPin_component easyPin_comp;
	private OTP_component otp_comp;
	private FundTransfer_component fundTransfer_comp;
	
	private String easyPin,fromAccountType,fromAccount,toAccount,amount,desc,transferMethod;	
	
	public TransferOtherbank() throws IOException {
		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_TO_ACCOUNT_OTBANK_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_OTBANK_METHOD"),
				DEFAULT_PROPERTIES.getProperty("DEF_OTBANK_AMOUNT"),
				DEFAULT_PROPERTIES.getProperty("DEF_OTBANK_DESC"));
	}
	
	public TransferOtherbank(String username,String fromAccountType,String toAccountType,String transferMethod,String amount,String desc) throws IOException {	
		super(false,username);
		Properties prop = LoadProperties.getUserProperties(username);
		this.easyPin=prop.getProperty("EASYPIN");
		this.fromAccountType=fromAccountType;
		this.fromAccount=prop.getProperty(fromAccountType);
		this.toAccount=prop.getProperty(toAccountType);
		this.amount=amount;
		this.desc=desc;	
		this.transferMethod=transferMethod;
		
	}
	
	@BeforeClass
	private void loadComponent(){
		
		easyPin_comp= new EasyPin_component(driver);
		otp_comp=new OTP_component(driver);
		fundTransfer_comp= new FundTransfer_component(driver);	
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
		fundTransfer_comp.fundTransferMenu();
	}
	@Test(dependsOnMethods="Test02_After_Login_Page")
	private void Test03_Select_Payee_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectPayee(toAccount);
		fundTransfer_comp.getPayeeName(DEFAULT_PROPERTIES.getProperty("DEF_OTBANK_CODE"));
	}
	
	@Test(dependsOnMethods="Test03_Select_Payee_Page")
	private void Test04_Select_Account_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectAccount(fromAccount, amount, desc);
	}

	@Test(dependsOnMethods="Test04_Select_Account_Page")
	private void Test05_Transfer_Method_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectTransferMethod(transferMethod);
	}
	
	@Test(dependsOnMethods="Test05_Transfer_Method_Page")
	private void Test06_Summary_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.summary();
	}

	@Test(dependsOnMethods="Test06_Summary_Page")
	private void Test07_Transfer_Otherbank_EasyPin_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		if(Long.parseLong(amount)>5000000 || fundTransfer_comp.isNewPayee())
			otp_comp.inputOTP();
		else
			easyPin_comp.inputEasyPin(easyPin);
	}
	
	@Test(dependsOnMethods="Test07_Transfer_Otherbank_EasyPin_Page")
	private void Test08_Transfer_Otherbank_Result_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.result(fromAccountType);
	}	
}
