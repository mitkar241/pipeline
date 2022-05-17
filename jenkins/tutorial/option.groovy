#!/usr/bin/env groovy

pipeline {
  agent any
  options {
    timeout(
      unit: 'HOURS',
      time: 1
    )
    buildDiscarder(
      logRotator(
        // number of build logs to keep
        numToKeepStr: '5',
        // history to keep in days
        daysToKeepStr: '1',
        // number of builds have their artifacts kept
        artifactNumToKeepStr: '5',
        // artifacts are kept for days
        artifactDaysToKeepStr: ''
      )
    )
    disableConcurrentBuilds()
    disableResume()
  }
  stages {
    stage('Print') {
      options {
        timeout(
          unit: 'SECONDS',
          time: 30
        )
        retry(2)
        timestamps()
      }
      steps {
        echo "This is to test echo"
      }
    }
  }
}

/* Test Possibility */
def setGeneralProperties() {
  properties([
    timeout(
      unit: 'SECONDS',
      time: 30
    ),
    retry(2),
    timestamps()
  ])
}
