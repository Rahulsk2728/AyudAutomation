pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'reports/**', allowEmptyArchive: true
        }
    }
}