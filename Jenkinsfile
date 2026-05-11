pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'mvn test -Dtest=VetApiTest,OwnerApiTest'
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
