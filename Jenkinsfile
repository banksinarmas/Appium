pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                bat 'cd Appium && mvn clean compile surefire:test'
            }
        }
    }
}
