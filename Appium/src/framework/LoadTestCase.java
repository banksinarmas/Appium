package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadTestCase {
	private static final String TESTCASE_FOLDER="Resources/TestCase";

	
	public static Object[][] loadFromFile (String file) throws FileNotFoundException{
		FileInputStream fstream = new FileInputStream(TESTCASE_FOLDER+"/"+file);
		Scanner sc = new Scanner(new InputStreamReader(fstream));
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		
		String[] arrStr = null;
		while (sc.hasNextLine())   
		{
			String strLine = sc.nextLine().toString();
			strLine=strLine.replace(", ", ",");
			strLine=strLine.replace(" ,", ",");
			
			if(!strLine.equals("")&&!strLine.contains("#")&&!strLine.contains("//"))	{
				
				arrStr=strLine.split(",");
				list.add(arrStr);
			}	
					
		}
		sc.close();

		Object[][] obj = new Object[list.size()][arrStr.length];

		for(int i=0;i<obj.length;i++) {
			for(int j=0;j<arrStr.length;j++) {
				obj[i][j]=list.get(i)[j];		
			}		
		}

		return obj;

	}


}
