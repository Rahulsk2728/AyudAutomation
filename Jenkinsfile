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

        stage('API Tests') {
            steps {
                bat 'mvn test "-Dsurefire.suiteXmlFiles=testng-api.xml"'
            }
        }

        stage('UI Tests') {
            parallel {

                stage('Chrome Tests') {
                    steps {
                        bat 'mvn test "-Dbrowser=chrome" "-Dsurefire.suiteXmlFiles=testng.xml"'
                    }
                }

                stage('Edge Tests') {
                    steps {
                        bat 'mvn test "-Dbrowser=edge" "-Dsurefire.suiteXmlFiles=testng.xml"'
                    }
                }
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
                results: [
                    [path: 'allure-results']
                ]
            )

            publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report'
                ])

            archiveArtifacts(
                artifacts: 'screenshots/**',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: 'traces/**',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: 'reports/**',
                allowEmptyArchive: true
            )
        }
    }
}