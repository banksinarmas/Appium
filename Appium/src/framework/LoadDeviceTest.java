package framework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadDeviceTest {

	static String folder="DeviceList";

	public static Object[][] loadFromFile (String file,int numOfParams) throws FileNotFoundException{
		FileInputStream fstream = new FileInputStream(folder+"/"+file);
		Scanner sc = new Scanner(new InputStreamReader(fstream));
		ArrayList<String[]> list = new ArrayList<String[]>();
	
		while (sc.hasNextLine())   
		{
			String strLine = sc.nextLine().toString().trim();
			if(!strLine.contains("#")&&!strLine.contains("//"))		
				list.add(strLine.split(","));		
		}
		sc.close();

		Object[][] obj = new Object[list.size()][numOfParams];

		for(int i=0;i<obj.length;i++) {
			for(int j=0;j<numOfParams;j++) {
				obj[i][j]=list.get(i)[j];		
			}		
		}

		return obj;

	}


}
