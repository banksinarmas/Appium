package billpayment;

import java.io.IOException;
import java.util.Properties;

import java.lang.reflect.Method;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.BillPayment_component;
import components.EasyPin_component;
import framework.LoadProperties;
import framework.LockdownDevice;

public class Block1_Water extends LockdownDevice{
	
	private EasyPin_component easyPin_comp;
	private BillPayment_component billPayment_comp;
	
	private String billerName,subscriberNo,sourceAccount;
	
	private String FOLDER = "/BillPayment/Block1_Water/"+deviceID;

	public Block1_Water() throws IOException {
		super();
		Properties prop = LoadProperties.getProperties("billpayment.properties");
		billerName=prop.getProperty("block1_billerName");
		subscriberNo=prop.getProperty("block1_subscriberNo");
		sourceAccount=prop.getProperty("block1_sourceAccount");
	}
	
	public Block1_Water(String deviceID,int port, int systemPort,String billerName,String subscriberNo,String sourceAccount) {
		super(deviceID,port,systemPort);
		this.billerName=billerName;
		this.subscriberNo=subscriberNo;
		this.sourceAccount=sourceAccount;
	}
	
	@BeforeClass
	private void loadComponent(){
		
		easyPin_comp= new EasyPin_component(driver);
		billPayment_comp= new BillPayment_component(driver);	
		
		FOLDER=apkVersion+FOLDER;
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
		billPayment_comp.waterPayment_billerMenu(billerName);
	}

	@Test(dependsOnMethods="After_Login_Page")
	private void Test01_Inquiry_Page(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		billPayment_comp.inputSubscriberNo(FOLDER, method.getName(), subscriberNo);
	}

	@Test(dependsOnMethods="Test01_Inquiry_Page")
	private void Test02_Select_Account_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		billPayment_comp.block1_selectAccount(FOLDER,method.getName(),sourceAccount);
	}

	@Test(dependsOnMethods="Test02_Select_Account_Page")
	private void Test03_Summary_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		billPayment_comp.summary(FOLDER,method.getName());
	}

	@Test(dependsOnMethods="Test03_Summary_Page")
	private void Test04_Block1_Water_EasyPin_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		easyPin_comp.input(FOLDER,method.getName(),easyPin);
	}
	
	@Test(dependsOnMethods="Test04_Block1_Water_EasyPin_Page")
	private void Test05_Block1_Water_Result_Page(Method method) throws Exception
	{
		System.out.println(deviceID+"_"+method.getName());
		billPayment_comp.result(FOLDER,method.getName());
	}

}
