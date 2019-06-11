package ETB;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import components.CCOramiETB_component;
import components.ProdukBank_component;
import components.EasyPin_component;
import framework.DeviceSetup;
import framework.LoadProperties;

public class CCOramiETB extends DeviceSetup{
	private EasyPin_component easyPin_comp;
	//private CCOramiETB_component CCOramiETB_comp;
	private ProdukBank_component ProdukBank_comp;

	private String username,easyPin;
	//private String RT, RW,Address; 
	private String RT, RW,Address,Gaji,Title, Industry, Company, CAddress, CNumber;
	private String Nama, CreditLimit,JumlahKartuKredit;
	//private String NPWP;
	private String FullName,  Relasi,  PhoneNumber;
	
	
	public CCOramiETB () throws IOException {
		this(DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"));	
	}

	public CCOramiETB (String username) throws IOException {
		super(false,username);
		
		this.username=username;
		Properties prop = LoadProperties.getUserProperties(this.username);
		this.easyPin=prop.getProperty("EASYPIN");
		
		//this.newEasyPin="123456";
		this.RT="3";
		this.RW="2";
		this.Address="serpong";
		this.Gaji="4500000";
		this.Title="QA";
		this.Industry="Banking";
		this.Company="Sinarmas";
		this.CAddress="Roxy";
		this.CNumber="086372";
		this.Nama="elshaddai";
		this.CreditLimit="4000000";
		this.JumlahKartuKredit="1";
		//this.NPWP="123456789123456";
		this.FullName="Tika Pradini";
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
	private void Test04_CCOramiMenu() throws Exception
	{		
		ProdukBank_comp.CCOramiMenu();
	}
	
	@Test(dependsOnMethods= "Test04_CCOramiMenu")
	private void Test05_TNCOrami() throws Exception
	{		
		ProdukBank_comp.TNC();
	}
	@Test(dependsOnMethods= "Test05_TNCOrami")
	private void Test06_EmailOrami() throws Exception
	{		
		ProdukBank_comp.Email();
	}
	@Test(dependsOnMethods= "Test06_EmailOrami")
	private void Test07_AddressOrami() throws Exception
	{		
		ProdukBank_comp.Address(RT,RW,Address);
	}
	@Test(dependsOnMethods= "Test07_AddressOrami")
	private void Test08_PekerjaanOrami() throws Exception
	{		
		ProdukBank_comp.Pekerjaan(Gaji);
	}
	@Test(dependsOnMethods= "Test08_PekerjaanOrami")
	private void Test09_DetailPekerjaanOrami() throws Exception
	{		
		ProdukBank_comp.DetailPekerjaan(Title,Industry, Company, CAddress, CNumber, RT,RW);
	}
	@Test(dependsOnMethods= "Test09_DetailPekerjaanOrami")
	private void Test10_DetailCreditCardOrami() throws Exception
	{		
		ProdukBank_comp.DetailCreditCard(Nama, CreditLimit,JumlahKartuKredit);
	}
	@Test(dependsOnMethods= "Test10_DetailCreditCardOrami")
	private void Test11_Tax() throws Exception
	{		
		ProdukBank_comp.TaxNONPWP();
	}
	@Test(dependsOnMethods="Test11_Tax")
	private void Test12_Business() throws Exception
	{
		ProdukBank_comp.BusinessGalery();
	}
	@Test(dependsOnMethods="Test12_Business")
	private void Test13_Emergency() throws Exception
	{
		ProdukBank_comp.Emergency( FullName,  Relasi,  PhoneNumber,  RT,  RW,  Address);
	}
	@Test(dependsOnMethods="Test13_Emergency")
	private void Test14_DeliveryCard() throws Exception
	{
		ProdukBank_comp.DeliveryCard();
	}
	@Test(dependsOnMethods="Test14_DeliveryCard")
	private void Test15_Result() throws Exception
	{
		ProdukBank_comp.Result();
	}
	
}
