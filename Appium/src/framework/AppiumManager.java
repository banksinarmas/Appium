package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumManager {

//	private AvailablePorts ap = new AvailablePorts();
	public AppiumDriverLocalService appiumDriverLocalService;
	public AppiumServiceBuilder builder = new AppiumServiceBuilder();
	public static int port = 0;
	
	public AppiumServiceBuilder appiumServerForAndroid() throws Exception{
		
//		port = ap.getPort();
		port = 4723;
		int chromePort = 6723;
//		int chromePort = ap.getPort();
//		int bootstrapPort = ap.getPort();
		int bootstrapPort = 5723;
		
		Properties prop = new Properties();
		InputStream input = new FileInputStream("Config.properties");
//		InputStream input = new FileInputStream("D:\\Simobi\\Config.properties");
		
		prop.load(input);
		
		AppiumServiceBuilder builder = 
				new AppiumServiceBuilder()
				.usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
				.withAppiumJS(new File("C:/Users/user.user-PC/AppData/Local/appium-desktop/app-1.5.0/resources/app/node_modules/appium/build/lib/main.js"))
				.withArgument(GeneralServerFlag.LOG_LEVEL, "info")
				.withArgument(AndroidServerFlag.CHROME_DRIVER_PORT, Integer.toString(chromePort))
				.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, Integer.toString(bootstrapPort))
				.withIPAddress(prop.getProperty("APPIUM_IP"))
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
				.withArgument(AndroidServerFlag.SUPPRESS_ADB_KILL_SERVER)
				.usingPort(port);
		
		appiumDriverLocalService = builder.build();
		appiumDriverLocalService.start();
		return builder;
	}
	public void destroyAppiumNode() {
		appiumDriverLocalService.stop();
		if (appiumDriverLocalService.isRunning()) {
			System.out.println("AppiumServer didn't shut... Trying to quit again....");
			appiumDriverLocalService.stop();
		}
	}
	
	public static void main(String[] args) throws Exception{

	//AppiumManager ap = new AppiumManager();
	//ap.appiumServerForAndroid();
		
	//	AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
	//	service.start();
		AppiumDriverLocalService appiumService;
		appiumService = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder().usingAnyFreePort()
						.usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
						.withAppiumJS(new File("C:/Users/user.user-PC/AppData/Local/appium-desktop/app-1.5.0/resources/app/node_modules/appium/build/lib/main.js")));
	
		appiumService.start();
		
	}
	
}
