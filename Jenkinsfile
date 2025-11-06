pipeline {
    // 1. Cambiar el agente Docker a una imagen con Android SDK
    agent {
        docker {
            image 'node:18-alpine'
            reuseNode true
        }
    }
    stages {
        stage('Build Android APK') {
            steps {
                sh './gradlew assembleDebug'
                sh 'ls -l app/build/outputs/apk/debug/' 
                archiveArtifacts artifacts: 'app/build/outputs/apk/debug/*.apk', onlyIfSuccessful: true
            }
        }
    }
}