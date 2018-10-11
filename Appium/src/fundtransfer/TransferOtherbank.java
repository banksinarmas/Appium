package fundtransfer;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import components.EasyPin_component;
import components.FundTransfer_component;
import components.OTP_component;
import framework.AppiumServer;
import framework.DeviceCapabilities;
import framework.DriverSetup;
import framework.LoadProperties;
import io.appium.java_client.android.AndroidDriver;

public class TransferOtherbank extends DeviceCapabilities{

	private String sourceAccount,toAccount,amount,desc,method;	
	
	private AppiumServer appium;
	private AndroidDriver<WebElement> driver;
	
	private String deviceID;
	private int port,systemPort;
	
	private OTP_component otp_comp;
	private EasyPin_component easyPin_comp;
	private FundTransfer_component fundTransfer_comp;
	
	public TransferOtherbank() throws IOException {
		Properties prop=LoadProperties.getProperties("fundtransfer.properties");
		this.sourceAccount=prop.getProperty("otbankSourceAccount");
		this.toAccount=prop.getProperty("otbankToAccount");
		this.amount=prop.getProperty("otbankAmount");
		this.desc=prop.getProperty("otbankDesc");
		this.method=prop.getProperty("otbankMethod");
		
/*		Properties deviceProp=LoadProperties.getProperties("device.properties");
		port=Integer.parseInt(deviceProp.getProperty("port"));
		systemPort=Integer.parseInt(deviceProp.getProperty("systemPort"));
		deviceID=deviceProp.getProperty("deviceID");*/
		
		Properties deviceProp=LoadProperties.getProperties("device.properties");
		port=Integer.parseInt(deviceProp.getProperty("billPaymentPort"));
		systemPort=Integer.parseInt(deviceProp.getProperty("billPaymentSystemPort"));
		deviceID=deviceProp.getProperty("billPaymentDeviceID");
	}
	
	public TransferOtherbank(String method,String sourceAccount,String toAccount,String amount,String desc) throws IOException {
		this.sourceAccount=sourceAccount;
		this.toAccount=toAccount;
		this.amount=amount;
		this.desc=desc;	
		this.method=method;
		
		Properties deviceProp=LoadProperties.getProperties("device.properties");
		port=Integer.parseInt(deviceProp.getProperty("fundTransferPort"));
		systemPort=Integer.parseInt(deviceProp.getProperty("fundTransferSystemPort"));
		deviceID=deviceProp.getProperty("fundTransferDeviceID");
	}
	
	@BeforeTest
	private void launch() throws Exception
	{		
		appium = new AppiumServer(port);
		appium.startServer();
		
		driver=new DriverSetup(deviceID,port,systemPort).driver;
		
		otp_comp=new OTP_component(driver);
		fundTransfer_comp= new FundTransfer_component(driver);
		easyPin_comp=new EasyPin_component(driver);
	}
	
	@BeforeClass
	private void login() throws IOException {
		Properties prop = LoadProperties.getProperties("credential.properties");
		easyPin=prop.getProperty("easyPin");
		easyPin_comp.loginEasyPin(easyPin);
		
	}

	@AfterSuite
	private void stop() throws Exception
	{
		appium.stopServer();
	}
	@Test
	private void Test01_After_Login_Page() throws Exception
	{
		fundTransfer_comp.fundTransferMenu();

	}
	
	@Test(dependsOnMethods="Test01_After_Login_Page")
	private void Test02_Select_Payee_Page() throws Exception
	{
		fundTransfer_comp.selectPayee(toAccount);

	}

	@Test(dependsOnMethods="Test02_Select_Payee_Page")
	private void Test03_Select_Account_Page() throws Exception
	{
		fundTransfer_comp.selectAccount(sourceAccount, amount, desc);
	}

	@Test(dependsOnMethods="Test03_Select_Account_Page")
	private void Test04_Transfer_Method_Page() throws Exception
	{
		fundTransfer_comp.selectTransferMethod(method);

	}
	
	@Test(dependsOnMethods="Test04_Transfer_Method_Page")
	private void Test05_Summary_Page() throws Exception
	{
		fundTransfer_comp.summary();

	}

	@Test(dependsOnMethods="Test05_Summary_Page")
	private void Test06_Transfer_Otherbank_EasyPin_Page() throws Exception
	{
		if(Long.parseLong(amount)>5000000)
			otp_comp.input();
		else
			easyPin_comp.input(easyPin);

	}
	@Test(dependsOnMethods="Test06_Transfer_Otherbank_EasyPin_Page")
	private void Test07_Transfer_Otherbank_Result_Page() throws Exception
	{
		fundTransfer_comp.result();
	}
	
}
