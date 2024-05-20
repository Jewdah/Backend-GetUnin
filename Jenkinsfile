pipeline{
    agent any
    stages{
        stage('Deploy') {
            steps {
                script {
                    sh 'docker-compose.yml up -d'
                }
            }
        }
    }
}
