package NTB;

import java.io.IOException;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.OTP_component;
import framework.DeviceSetup;

import components.NTBProdukBank_component;

public class NTBCCIndigo extends DeviceSetup{
	
		private NTBProdukBank_component NTBProdukBank_comp;
		private OTP_component OTP_comp;

		
		private String Phone, Email;
		
		public NTBCCIndigo() throws IOException {
			this(DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"));	
		}

		public NTBCCIndigo(String username) throws IOException {
			super(false,username);
			
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
		private void Test02_CCIndigoMenu() throws Exception
		{
			NTBProdukBank_comp.CCIndigoMenu();
		}
		@Test(dependsOnMethods="Test02_CCIndigoMenu")
		private void Test03_EmailIndigo() throws Exception
		{
			NTBProdukBank_comp.email();
		}
		@Test(dependsOnMethods="Test03_EmailIndigo")
		private void Test04_StartRegistration() throws Exception
		{
			NTBProdukBank_comp.StartRegistration(Phone,Email);
		}
		@Test(dependsOnMethods="Test04_StartRegistration")
		private void Test05_OTP() throws Exception
		{
			OTP_comp.inputOTP();
		}
}
