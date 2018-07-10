Jenkinsfile (Declarative Pipeline)
pipeline {
    agent any
    tools {
        maven 'Maven 3.5.2'
        jdk 'jdk8'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh 'mvn --version'
            }
        }
   }
}
