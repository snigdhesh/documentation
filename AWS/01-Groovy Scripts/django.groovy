pipeline {
    agent any
    
stages {
        stage('Clone Repository') {
            steps {
                // Clone the Git repository that contains your Dockerfile
                git branch: 'main', url: 'https://github.com/Ya-shu1/django-dockerfile-.git'
            }
        }
        stage('Build Docker Image') {
            steps {
                // Build the Docker image from the Dockerfile
                script {
                    docker.build("event-calendar-app:latest", ".")
                }
            }
        }
        stage('Run Docker Container') {
            steps {
                // Run the Docker container
                script {
                    docker.image("event-calendar-app:latest").run('-p 8000:8000')
                }
            }
        }
    }
}
