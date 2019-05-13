package SimasMerchant;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.EasyPin_component;
import components.SimasMerchant_component;
import components.OTP_component;
import framework.DeviceSetup;
import framework.LoadProperties;

public class SimasMerchant extends DeviceSetup {
	private EasyPin_component easyPin_comp;
	private SimasMerchant_component SimasMerchant_comp;
	private OTP_component otp_comp;
	
	private String username,easyPin,fromAccountType,NamaMerchant,NoSIUP,NoTDP,NoNPWP,DailySales,Email,NamaPemilik,NIK,TelpMerchant,NamaToko,HpToko,Alamat,NamaKasir,LoginKasir,HpKasir;
	
	public SimasMerchant() throws IOException {
	this(DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"));	
	}

	public SimasMerchant(String username) throws IOException {
		super(false,username);
		
		this.username=username;
		Properties prop = LoadProperties.getUserProperties(this.username);
		this.easyPin=prop.getProperty("EASYPIN");
		
	//	this.newEasyPin=easyPin;
		this.NamaMerchant="Morin Naga";
		this.NoSIUP="SIMAS1234567MNG";
		this.NoTDP="121158909054321";
		this.NoNPWP="879256178390898";
		this.DailySales="2300000";
		this.Email="msansteel@gmail.com";
		this.NamaPemilik="Bambang Sugiantoro";
		this.NIK="21739407462940003";
		this.TelpMerchant="083807194704";
		this.NamaToko="Morin Naga Jakarta";
		this.HpToko="083907194704";
		this.Alamat="Jl. Flamboyan";
		this.NamaKasir="Morina Gantar";
		this.LoginKasir="Morgan";
		this.HpKasir="083807194704";
	}

	@BeforeClass
	private void loadComponent(){
		easyPin_comp= new EasyPin_component(driver);
		SimasMerchant_comp=new SimasMerchant_component(driver);
	}

	@Test
	private void Test01_Login() throws Exception
	{			
		easyPin_comp.loginEasyPin(easyPin);
	}

	@Test(dependsOnMethods="Test01_Login")
	private void Test02_Settings_Menu() throws Exception
	{		
		SimasMerchant_comp.settingsMenu();
	}
	
	@Test(dependsOnMethods="Test02_Settings_Menu")
	private void Test03_Simas_Merchant_Menu() throws Exception
	{		
		SimasMerchant_comp.SimasMerchantDaftar(NamaMerchant,NoSIUP,NoTDP,NoNPWP,DailySales,Email,NamaPemilik,NIK,TelpMerchant);
	}
	
	@Test(dependsOnMethods= "Test03_Simas_Merchant_Menu")
	private void Test04_DetailPerusahaan() throws Exception
	{		
		SimasMerchant_comp.DetailPerusahaan(NamaMerchant,NoSIUP,NoTDP,NoNPWP,DailySales,Email,NamaPemilik,NIK,TelpMerchant);
	}
	
	@Test(dependsOnMethods= "Test04_DetailPerusahaan")
	private void Test05_Simas_Merchant_Toko() throws Exception
	{		
		SimasMerchant_comp.SimasToko(NamaToko,HpToko,Alamat);
	}
	@Test(dependsOnMethods= "Test05_Simas_Merchant_Toko")
	private void Test06_Simas_Merchant_Kasir() throws Exception
	{		
		SimasMerchant_comp.SimasKasir(NamaKasir,LoginKasir,HpKasir);
	}
	@Test(dependsOnMethods= "Test06_Simas_Merchant_Kasir")
	private void Test07_Simas_Konfirmasi_Data() throws Exception
	{		
		SimasMerchant_comp.KonfirmasiData();
	}
	@Test(dependsOnMethods= "Test07_Simas_Konfirmasi_Data")
	private void Test08_EasyPin_Page() throws Exception
	{
	    easyPin_comp.inputEasyPin(easyPin);
	}
	@Test(dependsOnMethods= "Test08_EasyPin_Page")
	private void Test07_Result_Page() throws Exception
	{	
		SimasMerchant_comp.result(fromAccountType);
	}

}
