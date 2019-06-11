package ETB;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import components.ProdukBank_component;
import components.EasyPin_component;
import framework.DeviceSetup;
import framework.LoadProperties;

public class CCAlfamartETB extends DeviceSetup {

	private EasyPin_component easyPin_comp;
	
	private ProdukBank_component ProdukBank_comp;

	private String username,easyPin;
	private String RT, RW,Address,Gaji,Title, Industry, Company, CAddress, CNumber;
	private String Nama, CreditLimit,JumlahKartuKredit;
	private String NPWP;
	private String FullName,  Relasi,  PhoneNumber;
	public CCAlfamartETB() throws IOException {
		this(DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"));	
	}

	public CCAlfamartETB (String username) throws IOException {
		super(false,username);
		
		this.username=username;
		Properties prop = LoadProperties.getUserProperties(this.username);
		this.easyPin=prop.getProperty("EASYPIN");
		
		
		this.RT="3";
		this.RW="2";
		this.Address="Serpong";
		this.Gaji="4500000";
		this.Title="Staff";
		this.Industry="Banking";
		this.Company="Sinarmas";
		this.CAddress="Roxy";
		this.CNumber="086372";
		this.Nama="elshaddai";
		this.CreditLimit="4000000";
		this.JumlahKartuKredit="1";
		this.NPWP="123456789123456";
		this.FullName="elshaddai";
		this.PhoneNumber="085831230513";
		
	}
	
	@BeforeClass
	private void loadComponent(){
		easyPin_comp= new EasyPin_component(driver);
		//CCIndigoETB_comp=new CCIndigoETB_component(driver);
		ProdukBank_comp = new ProdukBank_component(driver);
	}

	@Test
	private void Test01_Login() throws Exception
	{			
		easyPin_comp.loginEasyPin(easyPin);
	}

	@Test(dependsOnMethods="Test01_Login")
	private void Test02_BukaProdukBank() throws Exception
	{		
		ProdukBank_comp.BukaProdukBank();
	}
	
	@Test(dependsOnMethods="Test02_BukaProdukBank")
	private void Test03_ProdukMenu() throws Exception
	{		
		ProdukBank_comp.CreditCardMenu();
	}
	
	@Test(dependsOnMethods= "Test03_ProdukMenu")
	private void Test04_CCAlfamartMenu() throws Exception
	{		
		ProdukBank_comp.CCAlfamartMenu();
	}
	
	@Test(dependsOnMethods= "Test04_CCAlfamartMenu")
	private void Test05_TNCAlfamart() throws Exception
	{		
		ProdukBank_comp.TNC();
	}
	@Test(dependsOnMethods= "Test05_TNCAlfamart")
	private void Test06_EmailAlfamart() throws Exception
	{		
		ProdukBank_comp.Email();
	}
	@Test(dependsOnMethods= "Test06_EmailAlfamart")
	private void Test07_AddressAlfamart() throws Exception
	{		
		ProdukBank_comp.Address(RT,RW,Address);
	}
	@Test(dependsOnMethods= "Test07_AddressAlfamart")
	private void Test08_PekerjaanAlfamart() throws Exception
	{		
		ProdukBank_comp.Pekerjaan(Gaji);
	}
	@Test(dependsOnMethods= "Test08_PekerjaanAlfamart")
	private void Test09_DetailPekerjaanAlfamart() throws Exception
	{		
		ProdukBank_comp.DetailPekerjaan(Title,Industry, Company, CAddress, CNumber, RT,RW);
	}
	@Test(dependsOnMethods= "Test09_DetailPekerjaanAlfamart")
	private void Test10_DetailCreditCardAlfamart() throws Exception
	{		
		ProdukBank_comp.DetailCreditCard(Nama, CreditLimit,JumlahKartuKredit);
	}
	@Test(dependsOnMethods= "Test10_DetailCreditCardAlfamart")
	private void Test11_Tax() throws Exception
	{		
		ProdukBank_comp.TaxNPWP(NPWP);
	}
	
	@Test(dependsOnMethods= "Test11_Tax")
	private void Test12_takephototax() throws Exception
	{		
		ProdukBank_comp.takephototax();
	}
	@Test(dependsOnMethods="Test12_takephototax")
	private void Test13_Business() throws Exception
	{
		ProdukBank_comp.Business();
	}
	
	@Test(dependsOnMethods="Test13_Business")
	private void Test14_Emergency() throws Exception
	{
		ProdukBank_comp.Emergency( FullName,  Relasi,  PhoneNumber,  RT,  RW,  Address);
	}
	@Test(dependsOnMethods="Test14_Emergency")
	private void Test15_DeliveryCard() throws Exception
	{
		ProdukBank_comp.DeliveryCard();
	}
	@Test(dependsOnMethods="Test15_DeliveryCard")
	private void Test16_Result() throws Exception
	{
		ProdukBank_comp.Result();
	}
	
	
	
}
