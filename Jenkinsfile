pipeline {
    agent any

    stages {

        stage('Parallel Cross Browser Tests') {

            parallel {

                stage('Chrome Tests') {

                    steps {

                        dir('chrome') {

                            checkout scm

                            bat 'mvn clean test "-Dbrowser=chrome" "-Dsurefire.suiteXmlFiles=testng.xml"'
                        }
                    }
                }

                stage('Edge Tests') {

                    steps {

                        dir('edge') {

                            checkout scm

                            bat 'mvn clean test "-Dbrowser=edge" "-Dsurefire.suiteXmlFiles=testng.xml"'
                        }
                    }
                }
            }
        }
    }

    post {

        always {

            echo 'Publishing test results...'

            junit(
                testResults: '**/target/surefire-reports/*.xml',
                allowEmptyResults: true
            )

            allure(
                commandline: 'Allure',
                includeProperties: false,
                jdk: '',
                results: [
                    [path: 'chrome/allure-results'],
                    [path: 'edge/allure-results']
                ]
            )

            publishHTML(target: [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'chrome/reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Chrome Extent Report'
                ])

            publishHTML(target: [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'edge/reports',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Edge Extent Report'
                ])

            archiveArtifacts(
                artifacts: '**/screenshots/**, **/traces/**',
                allowEmptyArchive: true
            )

            archiveArtifacts(
                artifacts: '**/reports/**',
                allowEmptyArchive: true
            )
        }
    }
}