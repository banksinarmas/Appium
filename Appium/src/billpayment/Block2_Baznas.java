package billpayment;

import java.io.IOException;
import java.util.Properties;

import java.lang.reflect.Method;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.BillPayment_component;
import components.EasyPin_component;
import components.OTP_component;
import framework.LoadProperties;
import framework.LockdownDevice;

public class Block2_Baznas extends LockdownDevice{

	private EasyPin_component easyPin_comp;
	private OTP_component otp_comp;
	private BillPayment_component billPayment_comp;
	private String sourceAccount,subscriberNo,amount,desc,billerName;
	
	public Block2_Baznas() throws IOException {
		
		super();
		Properties prop = LoadProperties.getProperties("billpayment.properties");
		this.billerName=prop.getProperty("block2_billerName");
		this.sourceAccount=prop.getProperty("block2_sourceAccount");
		this.subscriberNo=prop.getProperty("block2_subscriberNo");
		this.amount=prop.getProperty("block2_amount");
		this.desc=prop.getProperty("block2_desc");
	}
	public Block2_Baznas(String deviceID,int port,int systemPort,String billerName,String subscriberNo,String sourceAccount,String amount,String desc) {
		
		super(deviceID,port,systemPort);
		this.billerName=billerName;
		this.sourceAccount=sourceAccount;
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
	private void Test00_Login(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		easyPin_comp.loginEasyPin(easyPin);
	}
	
	@Test(dependsOnMethods="Test00_Login")
	private void Test01_Login_After_Login_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		billPayment_comp.other_billerMenu(billerName);
	}

	@Test(dependsOnMethods="Test01_Login_After_Login_Page")
	private void Test02_Inquiry_Page(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		billPayment_comp.inputSubscriberNo(subscriberNo);
	}

	@Test(dependsOnMethods="Test02_Inquiry_Page")
	private void Test03_Select_Account_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		billPayment_comp.block2_selectAccount(sourceAccount, amount, desc);
	}

	@Test(dependsOnMethods="Test03_Select_Account_Page")
	private void Test04_Summary_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		billPayment_comp.summary();
	}

	@Test(dependsOnMethods="Test04_Summary_Page")
	private void Test05_Block2_Baznas_EasyPin_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		if(Long.parseLong(amount)>5000000)
			otp_comp.input();
		else
			easyPin_comp.input(easyPin);

	}
	@Test(dependsOnMethods="Test05_Block2_Baznas_EasyPin_Page")
	private void Test06_Block2_Baznas_Result_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		billPayment_comp.result();
	}

}
