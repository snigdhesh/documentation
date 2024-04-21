pipeline {
    agent any
    
    stages {
        stage('Clone Repository') {
            steps {
                script {
                    // Clone the Git repository that contains your Dockerfile
                    git branch: 'main', url: 'https://github.com/manasamor/flask_library_app.git'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image from the Dockerfile
                    docker.build("my-flast-app:latest", ".")
                }
            }
        }
        stage('Run Docker Container') {
            steps {
                script {
                    // Run the Docker container
                    docker.image("my-flast-app:latest").run('-p 9000:9000')
                }
            }
        }
    }
}
