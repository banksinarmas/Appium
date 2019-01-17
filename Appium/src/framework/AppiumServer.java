package framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public void startServer() throws IOException {	

		//killing appium server if it is still running
		if(isServerRunning(port)) {
			System.out.println("Appium is running in :"+port+",killing immediately");
			class CommandPrompt{
				public String windowsCommand(String command) throws IOException {
					return new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(command).getInputStream())).readLine();			

				}
			}

			CommandPrompt cmd = new CommandPrompt();
			String netstat =cmd.windowsCommand("cmd /c netstat -ano | findstr "+port);
			String pid = netstat.split(" ")[netstat.split(" ").length-1];
			System.out.println(cmd.windowsCommand("cmd /c taskkill -pid "+pid+ " /f /t"));

		}
		
			//Build the Appium service
			builder = new AppiumServiceBuilder();
			builder.withIPAddress("127.0.0.1");
			builder.usingPort(port);
			builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
			builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

			//Start the server with the builder
			service = AppiumDriverLocalService.buildService(builder);	

			System.out.println("Running appium in port :"+port);
			service.start();	

	}

	private static boolean isServerRunning (int port) {

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
		if(isServerRunning(port)) {
			System.out.println("Appium is running in port :"+ port+ ", Stopping immediately...");
			this.service.stop();
		}		
		else System.out.println("Appium is not running in this port :"+port);

	}
	
	public static void main(String[] args) {
		class CommandPrompt{
			public String windowsCommand(String command) throws IOException {
				return new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(command).getInputStream())).readLine();			

			}
		}
		CommandPrompt cmd = new CommandPrompt();
		try {
			String netstat =cmd.windowsCommand("cmd /c netstat -ano | findstr 127.0.0.1:"+4723);
			System.out.println(netstat);	
			String pid = netstat.split(" ")[netstat.split(" ").length-1];
			System.out.println(pid);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
