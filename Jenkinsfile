pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/jglick/simple-maven-project-with-tests.git'

            }
        }
        stage('Docker Image') {
            steps {
                // To run Maven on a Windows agent, use
                bat "mvn clean package -DskipTests"
            }
        }
    }
}
