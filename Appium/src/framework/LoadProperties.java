package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {

	private static final String PROPERTIES_FOLDER="Resources/Properties";
	
	public static Properties getProperties(String filename) throws IOException {
		Properties prop = new Properties();           
		InputStream is = new FileInputStream(PROPERTIES_FOLDER+"/"+filename);
		prop.load(is);
		return prop;		
	}
	
}

