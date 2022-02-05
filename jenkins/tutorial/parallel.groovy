#!/usr/bin/env groovy

pipeline {
  agent none
  stages {
    stage('Parallel Stages') {
      agent any
      steps {
        parallel (
          "Taskone" : {
            script {
              echo "Taskone: This is testing echo"
            }
          },
          "Tasktwo" : {
            script {
              echo "Tasktwo: This is testing echo"
            }
          }
        )
      }
    }
  }
}
