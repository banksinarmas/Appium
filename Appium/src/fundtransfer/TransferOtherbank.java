package fundtransfer;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import components.EasyPin_component;
import components.FundTransfer_component;
import components.OTP_component;
import framework.DeviceCapabilities;
import framework.LoadProperties;

public class TransferOtherbank extends DeviceCapabilities{

	private String sourceAccount,toAccount,amount,desc,method;	
	
	public TransferOtherbank() throws IOException {
		Properties prop=LoadProperties.getProperties("fundtransfer.properties");
		this.sourceAccount=prop.getProperty("otbankSourceAccount");
		this.toAccount=prop.getProperty("otbankToAccount");
		this.amount=prop.getProperty("otbankAmount");
		this.desc=prop.getProperty("otbankDesc");
		this.method=prop.getProperty("otbankMethod");
	}
	
	public TransferOtherbank(String method,String sourceAccount,String toAccount,String amount,String desc) throws IOException {
		this.sourceAccount=sourceAccount;
		this.toAccount=toAccount;
		this.amount=amount;
		this.desc=desc;	
		this.method=method;
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
	private void Test04_Transfer_Method_Page() throws Exception
	{
		FundTransfer_component.selectTransferMethod(method);

	}
	
	@Test(dependsOnMethods="Test04_Transfer_Method_Page")
	private void Test05_Summary_Page() throws Exception
	{
		FundTransfer_component.summary();

	}

	@Test(dependsOnMethods="Test05_Summary_Page")
	private void Test06_Transfer_Otherbank_EasyPin_Page() throws Exception
	{
		if(Long.parseLong(amount)>5000000)
			OTP_component.input();
		else
			EasyPin_component.input(easyPin);

	}
	@Test(dependsOnMethods="Test06_Transfer_Otherbank_EasyPin_Page")
	private void Test07_Transfer_Otherbank_Result_Page() throws Exception
	{
		FundTransfer_component.result();
	}
	

}
