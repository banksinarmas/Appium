package userprofile;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.ChangeEasyPin_component;
import components.EasyPin_component;
import framework.LoadProperties;
import framework.LockdownDevice;

public class ChangeEasyPin extends LockdownDevice {

	private EasyPin_component easyPin_comp;
	private ChangeEasyPin_component changeEasyPin_comp;

	private String currentPassword,newEasyPin;
	private final String FOLDER ="UserProfile/ChangeEasyPin/"+deviceID;
	
	public ChangeEasyPin() throws IOException {
		super();
		Properties prop = LoadProperties.getProperties("credential.properties");
		this.currentPassword = prop.getProperty("password");
		this.newEasyPin=prop.getProperty("newEasyPin");
		
	}

	public ChangeEasyPin(String deviceID,int port, int systemPort,String currentPassword,String newEasyPin) {
		super(deviceID,port,systemPort);
		
		this.currentPassword= currentPassword;
		this.newEasyPin= newEasyPin;
	}

	@BeforeClass
	private void loadComponent(){
		easyPin_comp= new EasyPin_component(driver);
		changeEasyPin_comp=new ChangeEasyPin_component(driver);
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
		changeEasyPin_comp.changeEasyPinMenu();
	}
	@Test(dependsOnMethods= "After_Login_Page")
	private void Test01_Change_EasyPin_Page(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		changeEasyPin_comp.createNew(FOLDER,method.getName(),newEasyPin);
	}
	@Test(dependsOnMethods= "Test01_Change_EasyPin_Page")
	private void Test02_Validate_Password_Page(Method method) throws Exception
	{	
		System.out.println(deviceID+"_"+method.getName());
		changeEasyPin_comp.validatePassword(FOLDER,method.getName(),currentPassword);
	}

}
