package billpayment;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.BillPayment_component;
import components.EasyPin_component;
import framework.DeviceSetup;
import framework.LoadProperties;

public class BillPayment_WaterMenu extends DeviceSetup{

	
	private EasyPin_component easyPin_comp;
	private BillPayment_component billPayment_comp;

	private String username,easyPin,billerName,fromAccountType,fromAccount,subscriberNo,desc;

	public BillPayment_WaterMenu() throws IOException {
		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_WATER_BILLER_NAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_WATER_SUBSCRIBER_NO"),
				DEFAULT_PROPERTIES.getProperty("DEF_WATER_DESC"));
	}
	
	public BillPayment_WaterMenu(String fromAccountType,String billerName,String subscriberNo,String desc) throws IOException {
		this(DEFAULT_PROPERTIES.getProperty("DEF_AUTOMATION_USERNAME"),fromAccountType,billerName,subscriberNo,desc);
	}

	public BillPayment_WaterMenu(String username,String fromAccountType,String billerName,String subscriberNo,String desc) throws IOException {
		super(false,username);

		this.username=username;
		Properties prop = LoadProperties.getUserProperties(this.username);
		this.easyPin=prop.getProperty("EASYPIN");
		this.fromAccountType=fromAccountType;
		this.fromAccount=prop.getProperty(fromAccountType);

		this.billerName=billerName;
		this.subscriberNo=subscriberNo;
		this.desc=desc;

	}

	@BeforeClass
	private void loadComponent(){

		easyPin_comp= new EasyPin_component(driver);
		billPayment_comp= new BillPayment_component(driver);
	}

	@Test
	private void Test01_Login() throws Exception
	{	
		
		easyPin_comp.loginEasyPin(easyPin);
	}

	@Test(dependsOnMethods="Test01_Login")
	private void Test02_Main_Biller_Menu() throws Exception
	{
		
		billPayment_comp.main_billerMenu();
	}
	@Test(dependsOnMethods="Test02_Main_Biller_Menu")
	private void Test03_Water_Biller_Menu() throws Exception
	{
		
		billPayment_comp.water_billerMenu(billerName);
	}

	@Test(dependsOnMethods="Test03_Water_Biller_Menu")
	private void Test04_Inquiry_Page() throws Exception
	{	
		
		billPayment_comp.inputSubscriberNo( subscriberNo);
	}

	@Test(dependsOnMethods="Test04_Inquiry_Page")
	private void Test05_Select_Account_Page() throws Exception
	{
		
		billPayment_comp.selectAccount(fromAccount,"",desc);
	}

	@Test(dependsOnMethods="Test05_Select_Account_Page")
	private void Test06_Summary_Page() throws Exception
	{
		
		billPayment_comp.summary();
	}

	@Test(dependsOnMethods="Test06_Summary_Page")
	private void Test07_EasyPin_Page() throws Exception
	{
		
		easyPin_comp.inputEasyPin(easyPin);
	}

	@Test(dependsOnMethods="Test07_EasyPin_Page")
	private void Test08_Result_Page() throws Exception
	{
		
		billPayment_comp.result(fromAccountType);
	}
}
