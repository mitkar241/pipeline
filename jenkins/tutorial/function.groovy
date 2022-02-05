#!/usr/bin/env groovy

pipeline {
  agent any
  stages {
    stage('Printing Python3 Version') {
      steps {
        printPyVer()
      }
    }
    stage('Printing Message') {
      steps {
        printMsg("Hello Node!")
      }
    }
  }
}

/*
####################
# FUNCTIONS
####################
*/

def printPyVer() {
  sh('python3 --version')
}

def printMsg(String msg) {
  sleep 5
  echo msg
}
