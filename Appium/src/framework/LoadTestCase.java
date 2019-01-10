package framework;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class LoadTestCase {
	private static final String TESTCASE_FOLDER="Resources/TestCase";

	public static Object[][] loadFromFile (String testFile) throws IOException {

		Properties testProp= LoadProperties.getProperties(TESTCASE_FOLDER+"/"+testFile);	

		ArrayList<String[]> list = new ArrayList<String[]>();
		String[] arrStr = null;
		for(Object key : testProp.keySet()) {
			String username = (String) key;
			String str = username;
			
			if(!testProp.getProperty(username).equals(""))
				str +=","+testProp.getProperty(username);
			arrStr=str.split(",");
			list.add(arrStr);	
			
		}

		Object[][] obj = new Object[list.size()][arrStr.length];

		for(int i=0;i<obj.length;i++) {
			for(int j=0;j<arrStr.length;j++) {
				obj[i][j]=list.get(i)[j];
			}		
		}

		return obj;
	}


}
