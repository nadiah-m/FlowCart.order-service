pipeline {
    agent any
    tools {
        maven 'Maven 3'
    }
    environment {
        // Define Docker image name and version
        DOCKER_IMAGE = 'nadiah92/flowcart.order-service'
        DOCKER_TAG = "latest-${BUILD_NUMBER}" // or use a specific version
        registryCredential = 'dockerCredentials'
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your repository
                checkout scm
            }
        }
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
                withSonarQubeEnv(credentialsId: 'sonarCred', installationName: 'sq1') {
                    sh 'mvn clean verify org.sonarsource.scanner.maven:sonar-maven-plugin:3.11.0.3922:sonar \
                     -Dsonar.projectKey=nadiah-m_FlowCart.order-service \
                     -Dsonar.organization=nadiah-m \
                     -Dsonar.jacoco.reportPaths=target/site/jacoco/jacoco.xml'
                }
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }
        stage('Upload Image') {
            steps {
                script {
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push("V$BUILD_NUMBER")
                        dockerImage.push('latest')
                    }
                }
            }
        }
    }
}
