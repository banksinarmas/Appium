pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                bat 'cmd Appium && mvn clean compile surefire:test'
            }
        }
    }
}
