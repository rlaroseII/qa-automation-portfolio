pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                dir('qa-automation-portfolio') {
                    sh 'mvn test -Dtest=VetApiTest,OwnerApiTest'
                }
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
