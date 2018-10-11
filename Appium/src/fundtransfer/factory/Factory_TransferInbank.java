package fundtransfer.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.IClass;
import org.testng.IMethodInstance;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import framework.LoadTestCase;
import fundtransfer.TransferInbank;

public class Factory_TransferInbank {

	   @Factory(dataProvider="inbank" )
	    private Object[] inbankCreateInstances(String deviceID,String port, String systemPort,String sourceAccount, String toAccount,String amount,String desc) throws IOException {
	       return new Object[] {new TransferInbank(deviceID,Integer.parseInt(port),Integer.parseInt(systemPort),sourceAccount,toAccount,amount,desc)};
	    }
	     
	    @DataProvider(name="inbank", parallel = true)
	    private static Object[][] inbankDataProvider() throws FileNotFoundException {
	        Object[][] dataArray = LoadTestCase.loadFromFile("FundTransfer/TransferInbank.txt",7);
	        
	        return dataArray;
	    }
	    
	    

}
