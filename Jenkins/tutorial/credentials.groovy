#!/usr/bin/env groovy

pipeline {
  agent any
  stages {
    stage('Example Username/Password') {
      environment {
        SERVICE_CREDS = credentials('UNAME_PWD_CRED')
      }
      steps {
        sh 'echo "Service user is $SERVICE_CREDS_USR"'
        sh 'echo "Service password is $SERVICE_CREDS_PSW"'
      }
    }
    stage('Example SSH Username with private key') {
      environment {
        SSH_CREDS = credentials('SSH_CRED')
      }
      steps {
        sh 'echo "SSH private key is located at $SSH_CREDS"'
        sh 'echo "SSH user is $SSH_CREDS_USR"'
      }
    }
  }
}
