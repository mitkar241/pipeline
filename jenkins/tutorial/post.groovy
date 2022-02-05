#!/usr/bin/env groovy

pipeline {
  agent any
  stages {
    stage('Post Stage') {
      steps {
        script {
          sh 'wrong command'
          echo 'Succeeded!'
        }
      }
      post {
        success {
          echo "Release Success"
        }
        failure {
          echo "Release Failed"
        }
        always {
          echo "Release finished do cleanup and send notifications"
          deleteDir()
        }
        cleanup {
          /* clean up our workspace */
          deleteDir()
          /* clean up tmp directory */
          dir("${workspace}@tmp") {
            deleteDir()
          }
          /* clean up script directory */
          dir("${workspace}@script") {
            deleteDir()
          }
        }
      }
    }
  }
}
