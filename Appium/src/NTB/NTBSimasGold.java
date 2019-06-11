package NTB;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.OTP_component;
import framework.DeviceSetup;

import components.NTBProdukBank_component;

public class NTBSimasGold extends DeviceSetup {
	//private EasyPin_component easyPin_comp;
	private NTBProdukBank_component NTBProdukBank_comp;
	private OTP_component OTP_comp;

	
	private String Phone, Email;
	
	public NTBSimasGold() throws IOException {
		this(DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"));	
	}

	public NTBSimasGold(String username) throws IOException {
		super(true,username);
		
		this.Phone = "0858312305131214";
		this.Email="bangunelsha04@gmail.com";
		
	}
	@BeforeClass
	private void loadComponent(){
		OTP_comp= new OTP_component(driver);
		NTBProdukBank_comp=new NTBProdukBank_component(driver);
	}

	@Test
	private void Test01_BukaProdukBank() throws Exception
	{
		NTBProdukBank_comp.BukaProdukBank();
	}
	@Test(dependsOnMethods="Test01_BukaProdukBank")
	private void Test02_SimasGoldMenu() throws Exception
	{
		NTBProdukBank_comp.SimasGoldMenu();
	}
	@Test(dependsOnMethods="Test02_SimasGoldMenu")
	private void Test03_EmailSimasGold() throws Exception
	{
		NTBProdukBank_comp.email();
	}
	@Test(dependsOnMethods="Test03_EmailSimasGold")
	private void Test04_StartRegistration() throws Exception
	{
		NTBProdukBank_comp.StartRegistration(Phone,Email);
	}
	@Test(dependsOnMethods="Test04_StartRegistration")
	private void Test05_OTP() throws Exception
	{
		OTP_comp.inputOTP();
	}
	@Test(dependsOnMethods="Test05_OTP")
	private void Test06_FotoKTP() throws Exception
	{
		NTBProdukBank_comp.FOTOKTP();
	}
}

