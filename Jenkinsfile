pipeliene{
    agent any 
        tools {
            maven "maven1"
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
            stage('Run test'){
                steps{
                    sh 'mvn test'
                }
            }
        }
    
}