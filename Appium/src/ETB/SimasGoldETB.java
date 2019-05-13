package ETB;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.SimasGold_component;
//import components.ChangeEasyPin_component;
import components.EasyPin_component;
import framework.DeviceSetup;
import framework.LoadProperties;

public class SimasGoldETB extends DeviceSetup{
	private EasyPin_component easyPin_comp;
	private SimasGold_component SimasGold_comp;

	private String username,easyPin;
	
	public SimasGoldETB() throws IOException {
		this(DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"));	
	}

	public SimasGoldETB(String username) throws IOException {
		super(false,username);
		
		this.username=username;
		Properties prop = LoadProperties.getUserProperties(this.username);
		this.easyPin=prop.getProperty("EASYPIN");
		
		//this.newEasyPin="123456";
		
	}
	@BeforeClass
	private void loadComponent(){
		easyPin_comp= new EasyPin_component(driver);
		SimasGold_comp=new SimasGold_component(driver);
	}

	@Test
	private void Test01_Login() throws Exception
	{			
		easyPin_comp.loginEasyPin(easyPin);
	}

	@Test(dependsOnMethods="Test01_Login")
	private void Test02_BukaProdukBank() throws Exception
	{		
		SimasGold_comp.BukaProdukBank();
	}
	
	@Test(dependsOnMethods="Test02_BukaProdukBank")
	private void Test03_ProdukMenu() throws Exception
	{		
		SimasGold_comp.ProdukMenu();
	}
	
	@Test(dependsOnMethods= "Test03_ProdukMenu")
	private void Test04_SavingAccountMenu() throws Exception
	{		
		SimasGold_comp.SavingAccountMenu();
	}
	
	@Test(dependsOnMethods= "Test04_SavingAccountMenu")
	private void Test05_TNCSimasGold() throws Exception
	{		
		SimasGold_comp.TNCSimasGold();
	}
	@Test(dependsOnMethods= "Test05_TNCSimasGold")
	private void Test06_inputSimasGold() throws Exception
	{		
		SimasGold_comp.inputSimasGold();
	}
	
	@Test(dependsOnMethods= "Test06_inputSimasGold")
	private void Test07_PembayaranSimasGold() throws Exception
	{		
		SimasGold_comp.PembayaranSimasGold();
	}
	//masukkan halaman easypin
	@Test(dependsOnMethods= "Test07_PembayaranSimasGold")
	private void Test08_EasyPin_Page() throws Exception
	{
		easyPin_comp.inputEasyPin(easyPin);
	}
	
	@Test(dependsOnMethods= "Test08_EasyPin_Page")
	private void Test09_SGberhasil() throws Exception
	{
		SimasGold_comp.SGresult();
	}
}
