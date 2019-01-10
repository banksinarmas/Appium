package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {

	private static final String RESOURCES_FOLDER="Resources";
	private static final String USER_FOLDER="User";
	private static final String PROPERTIES_FOLDER="properties";

	private static final String DEVICE_MAPPING_FILE="deviceMapping.properties";
	private static final String DEFAULT_PROPERTIES_FILE="default.properties";

	public static Properties getProperties(String filename) throws IOException {
		Properties prop = new Properties();           
		InputStream is = new FileInputStream(filename);
		prop.load(is);
		return prop;		
	}

	public static Properties getDeviceProperties() throws IOException {
		String filename=RESOURCES_FOLDER+"/"+PROPERTIES_FOLDER+"/"+DEVICE_MAPPING_FILE;
		return getProperties(filename);		
	}
	public static Properties getUserProperties(String user) throws IOException {
		user=RESOURCES_FOLDER+"/"+PROPERTIES_FOLDER+"/"+USER_FOLDER+"/"+user;
		return getProperties(user);
	}

	public static Properties getDefaultProperties()  {
		String filename=RESOURCES_FOLDER+"/"+PROPERTIES_FOLDER+"/"+DEFAULT_PROPERTIES_FILE;

		Properties prop = null;
		try {
			prop = getProperties(filename);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return prop;
	}

}

