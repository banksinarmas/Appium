package userprofile.single;

import java.io.IOException;

import org.testng.annotations.Factory;

import framework.LoadProperties;
import userprofile.ChangeEasyPin;

public class Single_ChangeEasyPin {

	@Factory
	private Object[] changeEasyPinCreateInstances() throws IOException {
		return new Object[] {new ChangeEasyPin(LoadProperties.getDefaultProperties().getProperty("DEF_AUTOMATION_USERNAME"))};
	}


}
