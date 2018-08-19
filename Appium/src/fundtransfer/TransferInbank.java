package fundtransfer;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import components.EasyPin_component;
import components.FundTransfer_component;
import components.OTP_component;
import framework.DeviceCapabilities;
import framework.LoadProperties;


public class TransferInbank extends DeviceCapabilities{

	private String sourceAccount,toAccount,amount,desc;	

	public TransferInbank() throws IOException {
		Properties prop=LoadProperties.getProperties("fundtransfer.properties");
		this.sourceAccount=prop.getProperty("inbankSourceAccount");
		this.toAccount=prop.getProperty("inbankToAccount");
		this.amount=prop.getProperty("inbankAmount");
		this.desc=prop.getProperty("inbankDesc");
	}

	public TransferInbank(String sourceAccount,String toAccount,String amount,String desc) throws IOException {
		this.sourceAccount=sourceAccount;
		this.toAccount=toAccount;
		this.amount=amount;
		this.desc=desc;	
	}

	@Test
	private void Test01_After_Login_Page() throws Exception
	{	
		FundTransfer_component.fundTransferMenu();
	}
	@Test(dependsOnMethods="Test01_After_Login_Page")
	private void Test02_Select_Payee_Page() throws Exception
	{
		FundTransfer_component.selectPayee(toAccount);
	}

	@Test(dependsOnMethods="Test02_Select_Payee_Page")
	private void Test03_Select_Account_Page() throws Exception
	{
		FundTransfer_component.selectAccount(sourceAccount, amount, desc);
	}

	@Test(dependsOnMethods="Test03_Select_Account_Page")
	private void Test04_Summary_Page() throws Exception
	{
		FundTransfer_component.summary();
	}

	@Test(dependsOnMethods="Test04_Summary_Page")
	private void Test05_Transfer_Inbank_EasyPin_Page() throws Exception
	{
		if(Long.parseLong(amount)>5000000)
			OTP_component.input();
		else
			EasyPin_component.input(easyPin);


	}
	@Test(dependsOnMethods="Test05_Transfer_Inbank_EasyPin_Page")
	private void Test06_Transfer_Inbank_Result_Page() throws Exception
	{
		FundTransfer_component.result();
	}

}
