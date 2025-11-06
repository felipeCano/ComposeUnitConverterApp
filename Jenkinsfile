pipeline {
    // 1. Cambiar el agente Docker a una imagen con Android SDK
    agent {
        docker {
            // Se recomienda usar una imagen de Docker que ya contenga el Android SDK
            // Por ejemplo: 'androidsdk/android-33' o una imagen basada en Ubuntu/Debian donde puedas instalarlo
            // Usaremos un agente 'any' si Jenkins ya tiene el Android SDK instalado como herramienta global,
            // pero si quieres usar Docker (más portable), una imagen como la siguiente es necesaria.
            image 'ghcr.io/circleci/android:api-33-ndk-r25c-node' // Ejemplo de imagen con Android SDK y Gradle
            reuseNode true
        }
    }
    
    // 2. Definir las herramientas necesarias (opcional si la imagen Docker es completa)
    // tools {
    //     // Esto asume que tienes configurado Gradle como herramienta en Jenkins
    //     gradle 'Gradle 8.0' 
    // }

    stages {
        stage('Build Android APK') {
            steps {
                // 3. Ejecutar el comando de Gradle para generar el APK
                // El comando varía según si quieres una versión de debug o release
                // Para generar el APK de debug:
                sh './gradlew assembleDebug'
                
                // Si quieres generar el APK de release, necesitarás configurar el signing:
                // sh './gradlew assembleRelease'
                
                // Opcional: listar para ver el APK generado
                sh 'ls -l app/build/outputs/apk/debug/' 
                
                // 4. Archivar el APK para que esté disponible en Jenkins
                // Reemplaza 'app-debug.apk' con el nombre de archivo real
                // El path suele ser: app/build/outputs/apk/debug/app-debug.apk
                archiveArtifacts artifacts: 'app/build/outputs/apk/debug/*.apk', onlyIfSuccessful: true
            }
        }
    }
}