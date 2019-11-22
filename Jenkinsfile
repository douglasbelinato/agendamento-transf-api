def CONTAINER_NAME="agendamento-transf-api"
def CONTAINER_TAG="latest"
def DOCKER_HUB_USER="dbelinato"
def HTTP_PORT="8090"

node {

    stage('Initialize'){
       def mavenHome  = tool 'myMaven'
       env.PATH = "${mavenHome}/bin:${env.PATH}"
    }

    stage('Checkout') {
        checkout scm
    }

    stage('Build'){
        sh "mvn clean install"
    }
    
    stage('Image Build'){
        imageBuild(CONTAINER_NAME, CONTAINER_TAG)
    }
    
    def imageBuild(containerName, tag){
        sh "docker build -t $containerName:$tag  -t $containerName --pull --no-cache ."
        echo "Image build complete"
    }
}
