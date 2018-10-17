package fundtransfer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import framework.LoadProperties;
import onboarding.Login_LockdownDevice;

public class Schedule_TransferOtherbank extends Login_LockdownDevice {

	private String sourceAccount,toAccount,amount,desc,transferMethod,recurrence,frequency;	
	private final String FOLDER = "FundTransfer/Schedule/Otherbank/"+deviceID;
	
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
	
	@Test
	private void Login(Method method) throws Exception
	{	
		super.login(method);
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
		fundTransfer_comp.selectPayee(FOLDER,method.getName(),toAccount);
	}

	@Test(dependsOnMethods="Test01_Select_Payee_Page")
	public void Test02_Select_Account_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectAccountSchedule(FOLDER,method.getName(),sourceAccount, amount, desc);
	}

	
	@Test(dependsOnMethods="Test02_Select_Account_Page")
	public void Test03_Schedule_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectSchedule(FOLDER,method.getName(),recurrence,frequency);
	}
	
	@Test(dependsOnMethods="Test03_Schedule_Page")
	public void Test04_Transfer_Method_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectTransferMethod(FOLDER,method.getName(),transferMethod);

	}
	@Test(dependsOnMethods="Test04_Transfer_Method_Page")
	public void Test05_Summary_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.summarySchedule(FOLDER,method.getName());
	}

	@Test(dependsOnMethods="Test05_Summary_Page")
	public void Test06_Schedule_Transfer_Otherbank_EasyPin_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		if(Long.parseLong(amount)>5000000)
			otp_comp.input(FOLDER,method.getName());
		else
			easyPin_comp.input(FOLDER,method.getName(),easyPin);

	}
	@Test(dependsOnMethods="Test06_Schedule_Transfer_Otherbank_EasyPin_Page")
	public void Test07_Schedule_Transfer_Otherbank_Result_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.result(FOLDER,method.getName());
	}
	

}
