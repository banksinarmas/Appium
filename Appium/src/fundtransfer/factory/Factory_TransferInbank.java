package fundtransfer.factory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import fundtransfer.TransferInbank;

public class Factory_TransferInbank {

	   @Factory(dataProvider="inbank")
	    private Object[] inbankCreateInstances(String easyPin,String sourceAccount, String toAccount,String amount,String desc) throws IOException {
	       return new Object[] {new TransferInbank(easyPin,sourceAccount,toAccount,amount,desc)};
	    }
	     
	    @DataProvider(name="inbank")
	    private static Object[][] inbankDataProvider() throws FileNotFoundException {
	        Object[][] dataArray = LoadTestCase.loadFromFile("FundTransfer/TransferInbank.txt",5);
	        return dataArray;
	    }
}
