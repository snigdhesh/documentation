# step0: Get EC2 instance ready
- Login to EC2 instance (amazonlinux:2023)
- Enable port `8080` on security-group. Cause `Jenkins` runs on port `8080`.
- Enable port `3000` on security-group. Cause `node.js app` runs on port `3000`.
- Install git `sudo yum install git` (This comes in handy in future steps)
- Install docker `sudo yum install docker -y` (This comes in handy in future steps)
  - Run `sudo systemctl start docker`
  - Run `sudo systemctl enable docker`
  - Run `sudo chmod 666 /var/run/docker.sock`
  - Run `sudo systemctl status docker` to check docker status
- Install node `sudo yum install nodejs npm`


# step1: Install jenkins [Reference link](https://www.jenkins.io/doc/book/installing/linux/)

**Note:** We want `Red Hat/Alma/Rocky` from reference link

- Run following commands to get necessary packages for jenkins initially.

```
sudo wget -O /etc/yum.repos.d/jenkins.repo https://pkg.jenkins.io/redhat-stable/jenkins.repo
sudo rpm --import https://pkg.jenkins.io/redhat-stable/jenkins.io-2023.key
sudo yum upgrade
```
- Install jdk with following command  
 `sudo amazon-Linux-extras install java-openjdk11 -y` (for amazon linux 2)
 `sudo yum install java-11-amazon-corretto-devel -y` (for amazon linux 2023)

- Install jenkins with following command  
 `sudo yum install jenkins -y`

# step2: Start jenkins
- Run `sudo systemctl start jenkins` to start jenkins.  
- Run `sudo systemctl enable jenkins` to enable jenkins. 
- Run `sudo cat /var/lib/jenkins/secrets/initialAdminPassword` to get admin password.
- Run `sudo systemctl start jenkins` to check jenkins status.

# step3: Login to jenkins
- Access http-IPV4 endpoint with port 8080. **Example:** http://3.23.87.89:8080/
- Enter admin password (No username required)
- Install suggested plugins.
- Skip and continue as admin > save > start using jenkins

# step4: Setup plugins for pipeline
- Dashboard > Manage Jenkins > plugins > available plugins.
- Search and install `Docker Pipeline` plugin.
- Make sure you have `Git plugin` installed already.

# step5: Setup Pipeline (For groovy script approach)
- Dashboard > new item > pipeline item
- Copy following code in pipeline script or search for "jenkins pipeline groovy script for node.js` on chatgpt

```
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
```

# step6 : Build pipeline
- Dashboard > project > Build now
- Check IPV4 address with exposed port. Ex: http://3.23.87.89:3000/