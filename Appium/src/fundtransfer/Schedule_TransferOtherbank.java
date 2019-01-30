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

public class Schedule_TransferOtherbank extends DeviceSetup {

	private EasyPin_component easyPin_comp;
	private OTP_component otp_comp;
	private FundTransfer_component fundTransfer_comp;
	
	private String username,easyPin,fromAccountType,fromAccount,toAccountType,toAccount,amount,desc,transferMethod,recurrence,frequency;	
	
	public Schedule_TransferOtherbank() throws IOException {
		
		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_TO_ACCOUNT_OTBANK_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_OTBANK_METHOD"),
				DEFAULT_PROPERTIES.getProperty("DEF_OTBANK_AMOUNT"),
				DEFAULT_PROPERTIES.getProperty("DEF_OTBANK_DESC"),
				DEFAULT_PROPERTIES.getProperty("DEF_SCHEDULE_RECURRENCE"),
				DEFAULT_PROPERTIES.getProperty("DEF_SCHEDULE_FREQUENCY"));
	}
	
	public Schedule_TransferOtherbank(String username,String fromAccountType,String toAccountType,String transferMethod,String amount,String desc,String recurrence,String frequency) throws IOException {
		
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
		this.transferMethod=transferMethod;
		this.recurrence=recurrence;
		this.frequency=frequency;
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
	private void Test02_FundTransfer_Menu() throws Exception
	{	
		
		fundTransfer_comp.fundTransferMenu();
	}	

	@Test(dependsOnMethods="Test02_FundTransfer_Menu")
	public void Test03_Select_Payee_Page() throws Exception
	{
		
		fundTransfer_comp.selectPayee(toAccount);
	}

	@Test(dependsOnMethods="Test03_Select_Payee_Page")
	public void Test04_Select_Account_Page() throws Exception
	{
		
		fundTransfer_comp.selectAccountSchedule(fromAccount, amount, desc);
	}

	
	@Test(dependsOnMethods="Test04_Select_Account_Page")
	public void Test05_Schedule_Page() throws Exception
	{
		
		fundTransfer_comp.selectSchedule(recurrence,frequency);
	}
	
	@Test(dependsOnMethods="Test05_Schedule_Page")
	public void Test06_Transfer_Method_Page() throws Exception
	{
		
		fundTransfer_comp.selectTransferMethod(transferMethod);

	}
	@Test(dependsOnMethods="Test06_Transfer_Method_Page")
	public void Test07_Summary_Page() throws Exception
	{
		
		fundTransfer_comp.summarySchedule();
	}

	@Test(dependsOnMethods="Test07_Summary_Page")
	public void Test08_EasyPin_Page() throws Exception
	{
		
		if(Long.parseLong(amount)>Long.parseLong(appConfig.get("EASY_PIN_MAX_AMOUNT")) || fundTransfer_comp.isNewPayee())
			otp_comp.inputOTP();
		else
			easyPin_comp.inputEasyPin(easyPin);

	}
	@Test(dependsOnMethods="Test08_EasyPin_Page")
	public void Test09_Result_Page() throws Exception
	{
		
		fundTransfer_comp.resultSchedule(fromAccountType,toAccountType,recurrence);
	}
	

}
