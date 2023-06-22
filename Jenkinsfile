pipeline {
    agent any
    tools{
        maven 'maven'
    }
    stages {
        stage('set environment') {
            steps{
                bat 'docker start sqlServerV'
                //the sqlServerV container should be running on the network 'com'
            }
        }
        stage('project build') {
            steps {
                script{
                    checkout scmGit(branches: [[name: '*/latest']], browser: github('https://github.com/secured-jenkins/Sender.git'), extensions: [], userRemoteConfigs: [[url: 'https://github.com/secured-jenkins/Sender.git']])
                    def numOfRetry = 0;
                    retry(2) {
                        if(numOfRetry > 0){
                            sleep (20);
                        }
                        numOfRetry = numOfRetry + 1;
                        bat 'mvn clean install -P test'
                    
                    }
                }
            }
        }
        stage('dockerize'){
            steps{
                bat 'docker build --tag hasanalrimawi/sender-ci:%BUILD_NUMBER% .'
            }
        }
        stage('push image'){
            steps{
                bat 'docker push hasanalrimawi/sender-ci:%BUILD_NUMBER%'
                // bat 'docker stop sqlServerV'
            }
        }
        stage('start Application'){
            steps{
                bat 'docker run -d --name newApplication --network com -p 8081:8081 hasanalrimawi/sender-ci:%BUILD_NUMBER%'
            }
        }
    }
}