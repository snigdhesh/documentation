pipeline {
    agent any
    
    stages {
        stage('Clone Repository') {
            steps {
                script {
                    // Clone the Git repository that contains your Dockerfile
                    git branch: 'main', url: 'https://github.com/manasamor/node-js.git'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image from the Dockerfile
                    docker.build("my-node-js:latest", ".")
                }
            }
        }
        stage('Run Docker Container') {
            steps {
                script {
                    // Run the Docker container
                    docker.image("my-node-js:latest").run('-p 3000:3000')
                }
            }
        }
    }
}
