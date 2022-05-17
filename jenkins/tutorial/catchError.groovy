#!/usr/bin/env groovy

pipeline {
  agent any
  stages {
    stage("Timeout stage") {
      options {
        timeout(time: 3, unit: "SECONDS")
      }
      steps {
        script {
          Exception caughtException = null
          catchError(buildResult: 'SUCCESS', stageResult: 'ABORTED') { 
            try { 
              echo "Started stage Timeout"
              sleep(time: 5, unit: "SECONDS")
            } catch (org.jenkinsci.plugins.workflow.steps.FlowInterruptedException err) {
              error "Caught ${err.toString()}" 
            } catch (Throwable err) {
              caughtException = err
            }
          }
          if (caughtException) {
            error caughtException.message
          }
        }
      }
    }
    stage("Echo stage") {
      steps {
        echo "Started stage Echo"
      }
    }
  }
}
