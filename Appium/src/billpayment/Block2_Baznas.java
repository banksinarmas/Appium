package billpayment;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.BillPayment_component;
import components.EasyPin_component;
import components.OTP_component;
import framework.DeviceSetup;
import framework.LoadProperties;

public class Block2_Baznas extends DeviceSetup{

	private EasyPin_component easyPin_comp;
	private OTP_component otp_comp;
	private BillPayment_component billPayment_comp;
	private String easyPin,fromAccountType,fromAccount,subscriberNo,amount,desc,billerName;
	
	public Block2_Baznas() throws IOException {
		
		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_BP2_BILLER_NAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_BP2_SUBSCRIBER_NO"),
				DEFAULT_PROPERTIES.getProperty("DEF_BP2_AMOUNT"),
				DEFAULT_PROPERTIES.getProperty("DEF_BP2_DESC"));
	}
	
	public Block2_Baznas(String username,String fromAccountType,String billerName,String subscriberNo,String amount,String desc) throws IOException {
		
		super(false,username);
		
		Properties prop = LoadProperties.getUserProperties(username);
		this.easyPin=prop.getProperty("EASYPIN");
		this.fromAccountType=fromAccountType;
		this.fromAccount=prop.getProperty(fromAccountType);
		
		this.billerName=billerName;
		this.subscriberNo=subscriberNo;
		this.amount=amount;
		this.desc=desc;
	}

	@BeforeClass
	private void loadComponent(){
		
		easyPin_comp= new EasyPin_component(driver);
		otp_comp=new OTP_component(driver);
		billPayment_comp= new BillPayment_component(driver);		
		
	}
	@Test
	private void Test01_Login(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		easyPin_comp.loginEasyPin(easyPin);
	}
	
	@Test(dependsOnMethods="Test01_Login")
	private void Test02_Login_After_Login_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		billPayment_comp.other_billerMenu(billerName);
	}

	@Test(dependsOnMethods="Test02_Login_After_Login_Page")
	private void Test03_Inquiry_Page(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		billPayment_comp.inputSubscriberNo(subscriberNo);
	}

	@Test(dependsOnMethods="Test03_Inquiry_Page")
	private void Test04_Select_Account_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		billPayment_comp.block2_selectAccount(fromAccount, amount, desc);
	}

	@Test(dependsOnMethods="Test04_Select_Account_Page")
	private void Test05_Summary_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		billPayment_comp.summary();
	}

	@Test(dependsOnMethods="Test05_Summary_Page")
	private void Test06_Block2_Baznas_EasyPin_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		if(Long.parseLong(amount)>5000000)
			otp_comp.inputOTP();
		else
			easyPin_comp.inputEasyPin(easyPin);

	}
	@Test(dependsOnMethods="Test06_Block2_Baznas_EasyPin_Page")
	private void Test07_Block2_Baznas_Result_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		billPayment_comp.result(fromAccountType);
	}

}
