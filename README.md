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

## Running test

For running test run suite file in `TestSuite` folder:
`mvn -f Appium/pom.xml clean compile surefire:test -Dsurefire.suiteXmlFiles=TestSuite/SUITE_NAME`

### Full Test
For single device testing run `SingleDevice.xml`

For multiple device paralel testing run `MultiDevice.xml`

## Jenkins Setup

This is scripted pipeline for jenkins setup, using poll scm method for trigger build when there are any changes in `mobile-app` or `Appium` repo.
There are 3 stages for build testing:
- Checkout : Checkout repository for any SCM changes
- Test : Run build test
- Report : Produce alllure report testing

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
