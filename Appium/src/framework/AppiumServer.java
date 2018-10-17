package framework;

import java.io.IOException;
import java.net.ServerSocket;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServer {

	private AppiumDriverLocalService service;
	private AppiumServiceBuilder builder;
	private int port;

	public AppiumServer(int port) {
		this.port=port;	
	}

	public void startServer() {	

			try {
				//Build the Appium service
				builder = new AppiumServiceBuilder();
				builder.withIPAddress("127.0.0.1");
				builder.usingPort(port);
				builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
				builder.withArgument(GeneralServerFlag.LOG_LEVEL, "info");

				//Start the server with the builder
				service = AppiumDriverLocalService.buildService(builder);	
				
				System.out.println("Running appium in port :"+port);
				service.start();
				
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Fail to appium run in port :"+port);
			}
		
	}

	private boolean isServerRunning (int port) {

		boolean isRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			//If control comes here, then it means that the port is in use
			isRunning = true;
		} finally {
			serverSocket = null;
		}
		return isRunning;
	}

	public void stopServer() {
		if(this.isServerRunning(port))System.out.println("Appium is running in port :"+ port+ ", Stopping immediately...");
		else System.out.println("Appium is not running in this port :"+port);

	}

}
