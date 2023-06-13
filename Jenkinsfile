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
            stage('Build docker image'){
                steps{
                    script {
                        def dockerComposeFile = './docker-compose.yml'
                        sh "docker compose -f ${dockerComposeFile} build"
                        }
                }
            }
        }
    
}