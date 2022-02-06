#!/usr/bin/env groovy

SSH_CRED = 'SSH_CRED'

pipeline {
  agent any
  /*
  agent {
    label "xxxxx"
  }
  */
  environment {
    PWD_CRED = getUnamePwd()
  }
  stages {
    stage('Example Username/Password') {
      environment {
        SERVICE_CREDS = credentials("${PWD_CRED}")
      }
      steps {
        sh 'echo "Service user is $SERVICE_CREDS_USR"'
        sh 'echo "Service password is $SERVICE_CREDS_PSW"'
      }
    }
    stage('Example SSH Username with private key') {
      environment {
        SSH_CREDS = credentials("${SSH_CRED}")
      }
      steps {
        sh 'echo "SSH private key is located at $SSH_CREDS"'
        sh 'echo "SSH user is $SSH_CREDS_USR"'
      }
    }
  }
}

def getUnamePwd() {
  return 'UNAME_PWD_CRED'
  /*
   def branchName = "${env.BRANCH_NAME}"
   if (branchName == "xxxxxx") {
      return 'some_credential_string'
   }
   else {
      return 'some_other_credential_string'
   }
   */
}
