pipeline {
    agent {label 'docker'}
    triggers { pollSCM('H/5 * * * * *') }
    stages {
        stage('check-site'){
            steps {
                script{
                    sh './scripts/check-site.sh'
                }
            }
        }
    }
    post {
        failure {
            mail to: 'dilitrium@gmail.com',
            subject: "Pipeline failed: ${currentBuild.fullDisplayName}",
            body: "Build failed ${env.BUILD_URL}"
        }
    }
}