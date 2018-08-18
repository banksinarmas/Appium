package fundtransfer;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import components.EasyPin_component;
import components.FundTransfer_component;
import components.Onboarding_component;
import framework.DeviceCapabilities;
import framework.LoadProperties;


public class TransferInbank extends DeviceCapabilities{

/*	String sourceAccount = "0008394903";
	String toAccount="0171029279";
	String easyPin="123456";
	String amount="1000";
	String desc="inbank";
	*/
	
	private String sourceAccount,toAccount,easyPin,amount,desc;
	
	public TransferInbank() throws IOException {
		Properties prop=LoadProperties.getProperties("fundtransfer.properties");
		this.sourceAccount=prop.getProperty("sourceAccount");
		this.toAccount=prop.getProperty("toAccount");
		this.easyPin=prop.getProperty("easyPin");
		this.amount=prop.getProperty("amount");
		this.desc=prop.getProperty("desc");
	}
	
	public TransferInbank(String easyPin,String sourceAccount,String toAccount,String amount,String desc) {
		this.sourceAccount=sourceAccount;
		this.toAccount=toAccount;
		this.easyPin=easyPin;
		this.amount=amount;
		this.desc=desc;	
	}
	
	@BeforeTest
	private void Test00_Login_Page() throws Exception
	{
		Onboarding_component.loginEasyPin(easyPin);
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
		EasyPin_component.input(easyPin);

	}
	@Test(dependsOnMethods="Test05_Transfer_Inbank_EasyPin_Page")
	private void Test06_Transfer_Inbank_Result_Page() throws Exception
	{
		FundTransfer_component.result();
	}
	
}
