pipeline{
    agent any
    stages{
        stage('Deploy') {
            steps {
                script {
                    sh 'docker-compose -f /var/jenkins_home/workspace/backend-genin/docker-compose.yml up -d'
                }
            }
        }
    }
}
