package fundtransfer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.IMethodInstance;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Test;

import components.EasyPin_component;
import components.FundTransfer_component;
import components.OTP_component;
import framework.AppiumServer;
import framework.DeviceCapabilities;
import framework.DriverSetup;
import framework.LoadProperties;
import framework.ScreenAction;
import io.appium.java_client.android.AndroidDriver;


public class TransferInbank {

	private String easyPin,sourceAccount,toAccount,amount,desc;

	private AppiumServer appium;
	private AndroidDriver<WebElement> driver;

	private String deviceID;
	private int port,systemPort;

	private OTP_component otp_comp;
	private EasyPin_component easyPin_comp;
	private FundTransfer_component fundTransfer_comp;
	
	private final String FOLDER = "FundTransfer/Inbank/"+deviceID;

	public TransferInbank() throws IOException {

		Properties prop=LoadProperties.getProperties("fundtransfer.properties");
		this.sourceAccount=prop.getProperty("inbankSourceAccount");
		this.toAccount=prop.getProperty("inbankToAccount");
		this.amount=prop.getProperty("inbankAmount");
		this.desc=prop.getProperty("inbankDesc");	

		Properties deviceProp=LoadProperties.getProperties("device.properties");
		port=Integer.parseInt(deviceProp.getProperty("port"));
		systemPort=Integer.parseInt(deviceProp.getProperty("systemPort"));
		deviceID=deviceProp.getProperty("deviceID");

	}

	public TransferInbank(String deviceID,int port,int systemPort,String sourceAccount,String toAccount,String amount,String desc) throws IOException {

		this.sourceAccount=sourceAccount;
		this.toAccount=toAccount;
		this.amount=amount;
		this.desc=desc;	

		this.port=port;
		this.systemPort=systemPort;
		this.deviceID=deviceID;

	}

	@BeforeClass
	private void launch() throws IOException 
	{	
		appium = new AppiumServer(port);
		appium.startServer();

		driver=new DriverSetup(deviceID,port,systemPort).driver;
			
		otp_comp=new OTP_component(driver);
		fundTransfer_comp= new FundTransfer_component(driver);
		easyPin_comp=new EasyPin_component(driver);

	}
	@AfterClass
	private void stop() throws Exception
	{
		appium.stopServer();
	}
	
	@Test
	private void Test00_Login(Method method) throws IOException {

		Properties prop = LoadProperties.getProperties("credential.properties");
		easyPin=prop.getProperty("easyPin");

		System.out.println(deviceID+"_"+method.getName());
		easyPin_comp.loginEasyPin(easyPin);

	}

	@Test(dependsOnMethods="Test00_Login")
	private void Test01_After_Login_Page(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.fundTransferMenu();
	}
	@Test(dependsOnMethods="Test01_After_Login_Page")
	private void Test02_Select_Payee_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectPayee(FOLDER,method.getName(),toAccount);
	}

	@Test(dependsOnMethods="Test02_Select_Payee_Page")
	private void Test03_Select_Account_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.selectAccount(FOLDER,method.getName(),sourceAccount, amount, desc);
	}

	@Test(dependsOnMethods="Test03_Select_Account_Page")
	private void Test04_Summary_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.summary(FOLDER,method.getName());
	}

	@Test(dependsOnMethods="Test04_Summary_Page")
	private void Test05_Transfer_Inbank_EasyPin_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		if(Long.parseLong(amount)>5000000)
			otp_comp.input(FOLDER,method.getName());
		else
			easyPin_comp.input(FOLDER,method.getName(),easyPin);
	}

	@Test(dependsOnMethods="Test05_Transfer_Inbank_EasyPin_Page")
	private void Test06_Transfer_Inbank_Result_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		fundTransfer_comp.result(FOLDER,method.getName());
	}

}
