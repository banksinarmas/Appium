package onboarding.single;

import java.io.IOException;

import org.testng.annotations.Factory;

import framework.LoadProperties;
import onboarding.Login_FreshDevice;

public class Single_Login_FreshDevice {

	@Factory
	private Object[] loginFreshDeviceCreateInstances() throws IOException {
		return new Object[] {new Login_FreshDevice(LoadProperties.getDefaultProperties().getProperty("DEF_AUTOMATION_USERNAME"))};
	}
}
