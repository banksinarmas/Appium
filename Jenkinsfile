properties([
    pipelineTriggers([
        [$class: "SCMTrigger", scmpoll_spec: "* * * * *"],
		cron('H/2 * * * *')
    ]),
])

node {
  stage ('Checkout') {
    dir('mobile-app') {
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
          url: 'https://github.com/alvinev/Appium.git',
          credentialsId: 'alvinev',
          branch: 'master'
        )
    }
	
  }
    load 'Appium/Jenkinsfile'
 }