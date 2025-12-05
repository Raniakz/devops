pipeline {
  agent any

  environment {
    DOCKER_IMAGE = "raniaouss24/devops"
    DOCKER_CRED  = "dockercreds"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
        script { echo "Branch: ${env.BRANCH_NAME ?: 'unknown'}" }
      }
    }

    stage('Clean + Build Maven') {
      steps {
        sh 'mvn -B clean package -DskipTests=false'
      }
      post {
        success {
          archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
      }
    }

    stage('Unit Tests') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Docker Build') {
      steps {
        script {
          def tag = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
          sh "docker build -t ${DOCKER_IMAGE}:${tag} ."
        }
      }
    }

    stage('Docker Push') {
      steps {
        withCredentials([usernamePassword(credentialsId: "${DOCKER_CRED}", usernameVariable: 'DH_USER', passwordVariable: 'DH_PASS')]) {
          sh '''
            echo "$DH_PASS" | docker login -u "$DH_USER" --password-stdin
            TAG=$(git rev-parse --short HEAD)
            docker push ${DOCKER_IMAGE}:$TAG
            docker logout
          '''
        }
      }
    }
  }

  post {
    success { echo "Pipeline succeeded: ${DOCKER_IMAGE}" }
    failure { echo "Pipeline failed" }
  }
}
