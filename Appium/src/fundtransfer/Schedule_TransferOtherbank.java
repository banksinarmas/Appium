package fundtransfer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.EasyPin_component;
import components.FundTransfer_component;
import components.OTP_component;
import framework.LoadProperties;
import framework.LockdownDevice;

public class Schedule_TransferOtherbank extends LockdownDevice {

	private EasyPin_component easyPin_comp;
	private OTP_component otp_comp;
	private FundTransfer_component fundTransfer_comp;
	
	private String sourceAccount,toAccount,amount,desc,transferMethod,recurrence,frequency;	
	
	public Schedule_TransferOtherbank() throws IOException {
		
		super();
		Properties prop=LoadProperties.getProperties("fundtransfer.properties");
		this.sourceAccount=prop.getProperty("schOtbankSourceAccount");
		this.toAccount=prop.getProperty("schOtbankToAccount");
		this.amount=prop.getProperty("schOtbankAmount");
		this.desc=prop.getProperty("schOtbankDesc");
		this.transferMethod=prop.getProperty("schOtbankMethod");
		this.recurrence=prop.getProperty("schOtbankRecurrence");
		this.frequency=prop.getProperty("schOtbankFrequency");
	}
	
	public Schedule_TransferOtherbank(String deviceID,int port,int systemPort,String sourceAccount,String toAccount,String amount,String desc,String transferMethod,String recurrence,String frequency) throws IOException {
		
		super(deviceID,port,systemPort);
		this.sourceAccount=sourceAccount;
		this.toAccount=toAccount;
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
	private void Login(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		easyPin_comp.loginEasyPin(easyPin);
	}
	
	@Test(dependsOnMethods="Login")
	private void After_Login_Page(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.fundTransferMenu();
	}	

	
	@Test(dependsOnMethods="After_Login_Page")
	public void Test01_Select_Payee_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectPayee(toAccount);
	}

	@Test(dependsOnMethods="Test01_Select_Payee_Page")
	public void Test02_Select_Account_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectAccountSchedule(sourceAccount, amount, desc);
	}

	
	@Test(dependsOnMethods="Test02_Select_Account_Page")
	public void Test03_Schedule_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectSchedule(recurrence,frequency);
	}
	
	@Test(dependsOnMethods="Test03_Schedule_Page")
	public void Test04_Transfer_Method_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectTransferMethod(transferMethod);

	}
	@Test(dependsOnMethods="Test04_Transfer_Method_Page")
	public void Test05_Summary_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.summarySchedule();
	}

	@Test(dependsOnMethods="Test05_Summary_Page")
	public void Test06_Schedule_Transfer_Otherbank_EasyPin_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		if(Long.parseLong(amount)>5000000)
			otp_comp.input();
		else
			easyPin_comp.input(easyPin);

	}
	@Test(dependsOnMethods="Test06_Schedule_Transfer_Otherbank_EasyPin_Page")
	public void Test07_Schedule_Transfer_Otherbank_Result_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.result();
	}
	

}
