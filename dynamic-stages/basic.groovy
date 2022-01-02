#!/usr/bin/env groovy

def makeStage() {
  return {
    stage('Sample Stage') {
      echo 'Hello World'
    }
  }
}

pipeline {
  agent none
  stages {
    stage('Deploy') {
      agent { label "prod" }
      steps {
        script {
          makeStage().call()
        }
      }
    }
  }
}
