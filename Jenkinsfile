pipeline {
    agent any
    tools {
        maven 'Maven 3'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        // post {
        //     success {
        //         echo 'Now Archiving...'
        //         archiveArtifacts artifacts: '**/target/*.war'
        //     }
        // }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Integration Test') {
            steps {
                sh 'mvn verify -DskipUnitTests'
            }
        }
        stage('Code Analysis With Checkstyle') {
            steps {
                sh 'mvn checkstyle:checkstyle'
            }
            post {
                success {
                    echo 'Generated Analysis Result'
                }
            }
        }
        stage('Run Sonarqube') {
            environment {
                scannerHome = tool 'Sonar'
            }
            steps {
                withSonarQubeEnv(credentialsId: 'Sonar', installationName: 'sq1') {
                    sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.11.0.3922:sonar \
                     -Dsonar.projectKey=nadiah-m_FlowCart.order-service \
                     -Dsonar.organization=nadiah-m'
                }
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
