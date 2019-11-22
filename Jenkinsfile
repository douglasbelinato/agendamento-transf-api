def CONTAINER_NAME="jenkins-pipeline"
def CONTAINER_TAG="latest"
def DOCKER_HUB_USER="hakdogan"
def HTTP_PORT="8090"

node {

    stage('Initialize'){
       
    }

    stage('Checkout') {
        checkout scm
    }

    stage('Build'){
        steps {
            withMaven(maven : 'apache-maven-3.6.1') {
                bat'mvn clean compile'
            }
        }
    }
}
