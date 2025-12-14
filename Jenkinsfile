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

    stage('4. Analyse SonarQube') {
                steps {
                    echo '📊 Analyse SonarQube...'
                    withSonarQubeEnv('SonarQube') {
                        sh '''
                            mvn clean verify sonar:sonar \
                              -Dsonar.projectKey=rania \
                              -Dsonar.projectName="rania" \
                              -DskipTests=true
                        '''
                    }
                }
            }

    stage('Docker Build') {
      steps {
        script {
          def tag = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
          sh "docker build -t ${DOCKER_IMAGE}:${tag} ."
          sh "docker tag ${DOCKER_IMAGE}:${tag} ${DOCKER_IMAGE}:latest"
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
            docker push ${DOCKER_IMAGE}:latest
            docker logout
          '''
        }
      }
    }

    stage('Deploy to Kubernetes') {
      steps {
        script {
          echo "Deploying to Kubernetes cluster..."
          sh 'kubectl apply -f k8s/mysql-pv.yaml'
          sh 'kubectl apply -f k8s/mysql-pvc.yaml'
          sh 'kubectl apply -f k8s/mysql-deployment.yaml'
          sh 'kubectl apply -f k8s/mysql-service.yaml'
          sh 'kubectl apply -f k8s/spring-deployment.yaml'
          sh 'kubectl apply -f k8s/spring-service.yaml'

          echo "Waiting for MySQL to be ready..."
          sh 'kubectl wait --for=condition=ready pod -l app=mysql -n devops --timeout=300s'

          echo "Restarting Spring Boot deployment to pull latest image..."
          sh 'kubectl rollout restart deployment/spring-app -n devops'
          sh 'kubectl rollout status deployment/spring-app -n devops --timeout=300s'

          echo "Deployment completed successfully!"
        }
      }
    }

    stage('Verify Deployment') {
      steps {
        script {
          echo "Checking deployment status..."
          sh 'kubectl get pods -n devops'
          sh 'kubectl get svc -n devops'
        }
      }
    }
  }


    post {
        success {
          echo "Pipeline succeeded: ${DOCKER_IMAGE}"
          echo "Application deployed to Kubernetes!"
        }
        failure {
          echo "Pipeline failed"
          sh 'kubectl get pods -n devops || true'
          sh 'kubectl logs -l app=spring-app -n devops --tail=50 || true'
        }
      }
    }