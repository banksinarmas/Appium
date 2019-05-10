# Appium
Android automation test for SimobiPlus

Tools/framework:
- Maven
- TestNG
- Eclipse
- Git
- Allure reporting
- Appium
- Android emulator

CI:
- Jenkins

## Setup
1. git clone `https://github.com/banksinarmas/Appium.git`
2. install android sdk & emulator
3. install maven
4. install appium through npm `npm install -g appium`
5. Make sure android debugging is active through `adb devices`

## Creating test
To make a new test please do the following:
1. Create `component class` in components package. Every method which are used in test class are defined here
2. Create `test class` to run the test
3. Create `test case` which is defined in `Resources/TestCase` folder
4. Create `factory class` which purpose is to make a test class can run multiple test scenario defined in test case
5. Create `test suite file` which is a configuration to run a factory class
6. After create `test suite file` don't forget to add the suite file to another suite file to include the test and make sure those test included in `SingleDevice.xml` and `MultiDevice.xml`

### Example
This is example for Transfer Inbank single device testing

#### Component Class
`FundTransfer_component` class in `components` package
```
public class FundTransfer_component  {

	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait10,wait30,wait65;
	...

	public FundTransfer_component(AndroidDriver<WebElement> driver) {
		this.driver=driver;
		wait10 = new WebDriverWait(driver,10);
		wait30 = new WebDriverWait(driver,30);
		wait65 = new WebDriverWait(driver,65);
		...

	}

	public void fundTransferMenu() {
		...
	}
	...
}
```
#### Test Class
`TransferInbank` class in `fundtransfer` package
```
public class TransferInbank  extends DeviceSetup{

	private EasyPin_component easyPin_comp;
	private OTP_component otp_comp;
	private FundTransfer_component fundTransfer_comp;

	private String username,easyPin,fromAccountType,fromAccount,toAccountType,toAccount,amount,desc;

	public TransferInbank() throws IOException {			
		...
	}

	public TransferInbank(String fromAccountType,String toAccountType,String amount,String desc) throws IOException {
		...
	}
	
	public TransferInbank(String username,String fromAccountType,String toAccountType,String amount,String desc) throws IOException {
		super(false,username);

		this.username=username;	
		Properties prop = LoadProperties.getUserProperties(this.username);
		this.easyPin=prop.getProperty("EASYPIN");
		this.fromAccountType=fromAccountType;
		this.fromAccount=prop.getProperty(this.fromAccountType);
		this.toAccountType=toAccountType;
		this.toAccount=prop.getProperty(this.toAccountType);
		this.amount=amount;
		this.desc=desc;	
	}


	@BeforeClass
	private void loadComponent() throws Exception{
		...
	}

	@Test
	private void Test01_Login() throws Exception
	{		
		easyPin_comp.loginEasyPin(easyPin);
	}
	...
}	
```

#### Test Case
`Single_TransferInbank.txt` in folder `Resources/TestCase/FundTransfer/Single`. Every line represent test scenario related to the test class
```
#fromAccountType,toAccountType,amount,desc
FROM_ACCOUNT_KONVEN_NORMAL,TO_ACCOUNT_KONVEN_NORMAL,1001,to inbank
FROM_ACCOUNT_KONVEN_NORMAL,TO_ACCOUNT_SYARIAH_NORMAL,1001,to inbank
FROM_ACCOUNT_KONVEN_NORMAL,TO_ACCOUNT_KONVEN_FOREX,20003,to inbank
```

#### Factory class
`Single_TransferInbank` class in `fundtransfer.single` package
```
public class Single_TransferInbank {

	@Factory(dataProvider="inbank" )
	private Object[] inbankCreateInstances(String fromAccountType,String toAccountType,String amount,String desc) throws IOException {
		return new Object[] {new TransferInbank(fromAccountType,toAccountType,amount,desc)};
	}

	@DataProvider(name="inbank")
	private static Object[][] inbankDataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("FundTransfer/Single/Single_TransferInbank.txt");
		return dataArray;
	}
}
```

#### Test Suite file
`Single_TransferInbank.xml` in folder `TestSuite/FundTransfer/Single`
This suite is for running `TransferInbank` test scenario only
```
<?xml version = "1.0" encoding = "UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >

<suite name="FundTransfer" >
	<test name="TransferInbank"  thread-count="8" group-by-instances="true" >
		<classes>
			<class name="fundtransfer.single.Single_TransferInbank" />
		</classes>
	</test>
</suite>

```

`Single_FundTransfer.xml` in folder `TestSuite/FundTransfer/Single`
This suite is for running every Fund Transfer test scenario
```
<?xml version = "1.0" encoding = "UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >

<suite name="FundTransfer">
	<test name="TransferInbank" thread-count="8" group-by-instances="true">
		<classes>
			<class name="fundtransfer.single.Single_TransferInbank" />
		</classes>
	</test>
	<test name="TransferOtherbank" thread-count="8" group-by-instances="true">
		<classes>
			<class name="fundtransfer.single.Single_TransferOtherbank" />
		</classes>
	</test>
	<test name="Schedule_TransferInbank" thread-count="8" group-by-instances="true">
		<classes>
			<class name="fundtransfer.single.Single_Schedule_TransferInbank" />
		</classes>
	</test>
	<test name="Schedule_TransferOtherbank" thread-count="8" group-by-instances="true">
		<classes>
			<class name="fundtransfer.single.Single_Schedule_TransferOtherbank" />
		</classes>
	</test>
</suite>
```

`SingleDevice.xml` in `TestSuite` folder
This suite is for running every automation test available. Note that `Single_FundTransfer.xml` is included in this suite file 
`<suite-file path="TestSuite/Onboarding/Single/Single_Login_FreshDevice.xml" />`. This suite is the config used in the automation test scenario which is run in jenkins.

```
<?xml version = "1.0" encoding = "UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd" >

<suite name="single-device-testing">
	<suite-files>
		<suite-file path="TestSuite/Onboarding/Single/Single_Login_FreshDevice.xml" />
		<suite-file path="TestSuite/FundTransfer/Single/Single_FundTransfer.xml" />
		<suite-file path="TestSuite/BillPayment/Single/Single_BillPayment.xml" />
		<suite-file path="TestSuite/TimeDeposit/Single/Single_CreateTimeDeposit.xml" />
		<suite-file path="TestSuite/CardlessWithdrawal/Single/Single_CardlessWithdrawal.xml" />
		<suite-file path="TestSuite/UserProfile/Single/Single_ChangeEasyPin.xml" />
	</suite-files>
</suite>
```

## Manage user and device data for testing
To make user and device avail for testing, please do the following:
1. Create `user data` file which is stored in `Resources/properties/User` folder. The file is named after the username
2. Add **username linked to device properties** in file `device.properties` in `Resources/properties`
3. Edit `default.properties` file, change `DEF_USERNAME` value to the new username, for changing automation user used, edit `DEF_AUTOMATION_USERNAME` value

### Example
This example is using username `automation1` and device `J7AXGF00R1006RA`

#### User properties
Ex. User used is **automation1** so the file `automation1` is created in folder `Resources/properties/User`

```
#Credential
PASSWORD=Ab123456
EASYPIN=123456

#Source Account
FROM_ACCOUNT_KONVEN_NORMAL=0008535116
FROM_ACCOUNT_KONVEN_DORMANT=0008540055
FROM_ACCOUNT_KONVEN_BLOCK=0008540047
FROM_ACCOUNT_SYARIAH_NORMAL
FROM_ACCOUNT_SYARIAH_DORMANT
FROM_ACCOUNT_SYARIAH_BLOCK
ACCOUNT_RDN

#To Account
TO_ACCOUNT_KONVEN_NORMAL=0171029279
TO_ACCOUNT_KONVEN_BLOCK=0008392161
TO_ACCOUNT_KONVEN_FOREX=0008391491
TO_ACCOUNT_SYARIAH_NORMAL=9999004056
TO_ACCOUNT_OTBANK=001001001
TO_VIRTUAL_ACCOUNT=8334000997000001
TO_EMONEY=38087783088806
TO_PHONE_NO=0800123456

#Time Deposit
TIME_DEPOSIT_KONVEN
TIME_DEPOSIT_SYARIAH

#Credit card
CREDIT_CARD_NO

```

#### Device properties
User **automation1** is linked to device **J7AXGF00R1006RA** in `device.properties` in folder `Resources/properties`
```
...
#Asus zenfone L1
automation1=J7AXGF00R1006RA,4732,8209
```
#### Default properties
Change `DEF_USERNAME` to the **automation** in `default.properties` in folder `Resources/properties`
If you want to change the automation user used change `DEF_USERNAME` as well
```
###Default username and account type 
DEF_AUTOMATION_USERNAME=automation1
DEF_USERNAME=automation1
...
```

## Running test

For running test run suite file in `TestSuite` folder. This can be run directly in eclipse or by using maven command :
Eclipse : `Right click on the suite file which to run > Run As > TestNG Suite files`

Maven command : `mvn -f Appium/pom.xml clean compile surefire:test -Dsurefire.suiteXmlFiles=TestSuite/PATH_TO_SUITE`
i.e for running `TransferInbank` test for single device run `mvn -f Appium/pom.xml clean compile surefire:test -Dsurefire.suiteXmlFiles=TestSuite/FundTransfer/Single/Single_TransferInbank.xml`

### Full Test

For single device testing run **SingleDevice.xml**
For multiple device paralel testing run **MultiDevice.xml**

## Jenkins Setup
This is scripted pipeline for jenkins setup, using poll scm method for trigger build when there are any changes in **mobile-app** or **Appium** repo.
There are 3 stages for build testing:
- Checkout : Checkout repository for any SCM changes
- Test : Run build test
- Report : Produce allure report testing

```
properties([
    pipelineTriggers([
        [$class: "SCMTrigger", scmpoll_spec: "* * * * *"],
		cron('H 8-23/12 * * *')
    ]),
])

node {
  stage ('Checkout') {
    dir('Mobile-app') {
      git(
      poll: true,
          url: 'https://github.com/banksinarmas/mobile-app.git',
          credentialsId: 'alvinev',
          branch: 'master'
      )
    }
    dir('Appium') {
      git(
      poll: true,
          url: 'https://github.com/banksinarmas/Appium.git',
          credentialsId: 'alvinev',
          branch: 'master'
        )
    }
}
	try{  
		stage ('Test') {
			dir('Appium') {
			bat 'mvn -f Appium/pom.xml clean compile surefire:test'  
			}
		}
	}catch(e){
		throw e
	}finally{	
		stage('Reports') {
		    dir('Appium') {
		    	script {
					allure([
						includeProperties: false,
						jdk: '',
						properties: [],
						reportBuildPolicy: 'ALWAYS',
						results: [[path: 'Appium/allure-results']]
					])
			    }
		    }
		}
	}
}
```
