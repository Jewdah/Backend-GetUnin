pipeline{
    agent any
    stages{
        stage('Build Image') {
          steps {
            script {
              docker.build("backend-getunin:latest")
            }
          }
        }
        stage('Run Container') {
          steps {
            script {
              docker.image("tu-imagen:latest").run("-d -p 5000:5000 --name mi-contenedor")
            }
          }
        }
        stage ('Tests'){
            steps{
                echo "Etapa TEST no disponible"
            }
        }
        stage ('Deploy'){
            steps{
                sh "docker-compose down -v"
                sh "docker-compose up -d --build"
            }
        }
    }
}
