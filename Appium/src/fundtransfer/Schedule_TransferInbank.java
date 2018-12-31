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

public class Schedule_TransferInbank extends LockdownDevice{
	
	private EasyPin_component easyPin_comp;
	private OTP_component otp_comp;
	private FundTransfer_component fundTransfer_comp;
	
	private String sourceAccount,toAccount,amount,desc,recurrence,frequency;	
	
	public Schedule_TransferInbank() throws IOException {	
		super();
		Properties prop=LoadProperties.getProperties("fundtransfer.properties");
		this.sourceAccount=prop.getProperty("schInbankSourceAccount");
		this.toAccount=prop.getProperty("schInbankToAccount");
		this.amount=prop.getProperty("schInbankAmount");
		this.desc=prop.getProperty("schInbankDesc");
		this.recurrence=prop.getProperty("schInbankRecurrence");
		this.frequency=prop.getProperty("schInbankFrequency");	
	}
	
	public Schedule_TransferInbank(String deviceID,int port,int systemPort,String sourceAccount,String toAccount,String amount,String desc,String recurrence,String frequency) throws IOException {
		super(deviceID,port,systemPort);
		this.sourceAccount=sourceAccount;
		this.toAccount=toAccount;
		this.amount=amount;
		this.desc=desc;	
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
		fundTransfer_comp.selectAccountSchedule(sourceAccount,amount,desc);
	}
	
	@Test(dependsOnMethods="Test02_Select_Account_Page")
	public void Test03_Schedule_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectSchedule(recurrence,frequency);
	}
	
	@Test(dependsOnMethods="Test03_Schedule_Page")
	public void Test04_Summary_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.summarySchedule();
	}

	@Test(dependsOnMethods="Test04_Summary_Page")
	public void Test05_Schedule_Transfer_Inbank_EasyPin_Page(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		if(Long.parseLong(amount)>5000000)
			otp_comp.input();
		else
			easyPin_comp.input(easyPin);

	}
	@Test(dependsOnMethods="Test05_Schedule_Transfer_Inbank_EasyPin_Page")
	public void Test06_Schedule_Transfer_Inbank_Result_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.result();
	}
	
}
