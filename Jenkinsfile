pipeline {
    agent any

    parameters {
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'edge'],
            description: 'Select browser'
        )
    }
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
                bat "mvn test -Dbrowser=${params.BROWSER}"
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
            archiveArtifacts artifacts: 'reports/**', allowEmptyArchive: true
        }
    }
}