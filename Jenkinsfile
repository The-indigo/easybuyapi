pipeline{
    agent any 
        tools {
            jdk "java17"
        }
        environment{
            GITHUB_TOKEN = credentials('githubtoken')
        }
        stages{
            stage('Clean maven target'){
                steps {
                    sh 'mvn clean'
                }
            }
            stage('Run Maven test'){
                steps{
                    sh 'mvn test'
                }
            }
            stage("Checkstyle Analysis"){
                steps{
                    sh 'mvn checkstyle:checkstyle'
                }
            }
            stage("Show docker version"){
                steps{
                    sh 'docker version'
                }
            } 
            stage("Show docker compose version"){
                steps{
                    sh 'docker compose version'
                }
            }                        
            // stage('Build docker image'){
            //     steps{
            //         script {
            //             def dockerComposeFile = './docker-compose.yml'
            //             sh "docker compose -f ${dockerComposeFile} build"
            //             }
            //     }
            // }
        }
    
}