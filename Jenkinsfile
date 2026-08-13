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

            allure(
                commandline: 'Allure',
                includeProperties: false,
                jdk: '',
                results: [[path: 'allure-results']]
            )
            publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report'
                ])

            archiveArtifacts artifacts: 'reports/**', allowEmptyArchive: true

            archiveArtifacts artifacts: 'screenshots/**',
            allowEmptyArchive: true
        }
    }
}