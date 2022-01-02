#!/usr/bin/env groovy

pipeline {
  agent none
  environment {
    SCRIPT_VERSION = "v1.0.0"
  }
  parameters {
    string(name: 'user', defaultValue: 'root', description: 'Provide User Name')
    text(name: 'task', defaultValue: 'jenkins', description: 'Provide Task Name')
    booleanParam(name: 'doTest', defaultValue: true, description: 'Toggle this value')
    choice(name: 'environment', choices: ['dev', 'test', 'prod'], description: 'Provide env choice')
    password(name: 'password', defaultValue: 'PWD12345', description: 'Provide Password')
  }
  options {
    timeout(unit: 'SECONDS', time: 10)
  }
  stages {
    stage('Testing') {
      agent { label "build" }
      environment {
        EXAMPLE_CREDS = credentials('6a96e553-fa12-489a-abb4-1488f5f4f616')
      }
      steps {
        parallel (
          "Taskone" : {
            script {
              try {
                buildTask1()
              } catch (Exception e) {
                echo "buildTask1: This is Error" + e.toString()
              }
            }
          },
          "Tasktwo" : {
            script {
              try {
                buildTask2()
              } catch (Exception e) {
                echo "buildTask2: This is Error" + e.toString()
              }
            }
          }
        )
      //steps {
        //sh('echo ${EXAMPLE_CREDS_USR}:${EXAMPLE_CREDS_PSW}')
      }
    }
    stage('Deploy') {
      agent { label "prod" }
      environment {
        SSH_CREDS = credentials('ssh-prkey')
        CRED_PWD = "${params.password}"
      }
      steps {
        parallel (
          "Taskone" : {
            script {
              try {
                deployTask1()
              } catch (Exception e) {
                echo "deployTask1: This is Error" + e.toString()
              }
            }
          },
          "Tasktwo" : {
            script {
              try {
                deployTask2("${params.password}")
              } catch (Exception e) {
                echo "deployTask2: This is Error " + e.toString()
                currentBuild.result = "FAILURE"
                //catch (err) {echo err.getMessage()}
              }
            }
          }
        )
      }
    }
  }
  post {
    always { 
      echo "This will always be executed"
      //sh('wrong command intentional')
    }
    success {
      script {
        echo "success"
      }
    }
    failure {
      echo "This is Error handling in post"
    }
  }
}

def buildTask1() {
  sh('echo "Example User is $EXAMPLE_CREDS_USR"')
  sh('echo "Example Password is $EXAMPLE_CREDS_PSW"')
}

def buildTask2() {
  echo "User name is ${params.user}"
  echo "Executing task ${params.task}"
  echo "Is it supposed to be tested? ${params.doTest}"
  echo "Env choice is ${params.environment}"
  echo "Password is ${params.password}"
}

def deployTask1() {
  sh('echo "SSH User is $SSH_CREDS_USR"')
  sh('echo "SSH Private Key is $SSH_CREDS"')
}

def deployTask2(String pwd) {
  sleep 20
  sh('echo "is jenkins? ${SCRIPT_VERSION}"')
  sh('echo "Password is $pwd"')
}
