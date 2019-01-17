package fundtransfer;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.EasyPin_component;
import components.FundTransfer_component;
import components.OTP_component;
import framework.DeviceSetup;
import framework.LoadProperties;

public class TransferInbank  extends DeviceSetup{

	private EasyPin_component easyPin_comp;
	private OTP_component otp_comp;
	private FundTransfer_component fundTransfer_comp;

	private String username,easyPin,fromAccountType,fromAccount,toAccountType,toAccount,amount,desc;

	public TransferInbank() throws IOException {			
		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_TO_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_INBANK_AMOUNT"),
				DEFAULT_PROPERTIES.getProperty("DEF_INBANK_DESC"));
	}

	public TransferInbank(String username,String fromAccountType,String toAccountType,String amount,String desc) throws IOException {
		super(false,username);
		
		this.username=username;	
		Properties prop = LoadProperties.getUserProperties(this.username);
		this.easyPin=prop.getProperty("EASYPIN");
		this.fromAccountType=fromAccountType;
		this.fromAccount=prop.getProperty(this.fromAccountType);
		this.toAccountType=toAccountType;
		this.toAccount=prop.getProperty(this.toAccountType);
		this.amount=amount;
		this.desc=desc;	
	}

	
	@BeforeClass
	private void loadComponent(){
		easyPin_comp= new EasyPin_component(driver);
		otp_comp=new OTP_component(driver);
		fundTransfer_comp= new FundTransfer_component(driver);
	}
	
	@Test
	private void Test01_Login() throws Exception
	{		
		easyPin_comp.loginEasyPin(easyPin);
	}

	@Test(dependsOnMethods="Test01_Login")
	private void Test02_After_Login_Page() throws Exception
	{	
		fundTransfer_comp.fundTransferMenu();
	}
	@Test(dependsOnMethods="Test02_After_Login_Page")
	private void Test03_Select_Payee_Page() throws Exception
	{
		fundTransfer_comp.selectPayee(toAccount);
		fundTransfer_comp.getPayeeName(DEFAULT_PROPERTIES.getProperty("DEF_INBANK_CODE"));
	}
	@Test(dependsOnMethods="Test03_Select_Payee_Page")
	private void Test04_Select_Account_Page() throws Exception
	{	
		fundTransfer_comp.selectAccount(fromAccount, amount, desc);
	}
	@Test(dependsOnMethods="Test04_Select_Account_Page")
	private void Test05_Summary_Page() throws Exception
	{	
		fundTransfer_comp.summary();
	}
	@Test(dependsOnMethods="Test05_Summary_Page")
	private void Test06_Transfer_Inbank_EasyPin_Page() throws Exception
	{
		if(Long.parseLong(amount)>5000000 || fundTransfer_comp.isNewPayee())
			otp_comp.inputOTP();
		else
			easyPin_comp.inputEasyPin(easyPin);
	}
	@Test(dependsOnMethods="Test06_Transfer_Inbank_EasyPin_Page")
	private void Test07_Transfer_Inbank_Result_Page() throws Exception
	{	
		fundTransfer_comp.result(fromAccountType);
	}

}
