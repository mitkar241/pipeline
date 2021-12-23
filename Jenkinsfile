#!/usr/bin/env groovy

// Jenkinsfile (Declarative Pipeline)
pipeline {
  agent none
  environment {
    IS_JENKINS = "yes"
  }
  parameters {
    string(name: 'user', defaultValue: 'root', description: 'Provide User Name')
    text(name: 'task', defaultValue: 'jenkins', description: 'Provide Task Name')
    booleanParam(name: 'hasTested', defaultValue: true, description: 'Toggle this value')
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
                buildTask1()
              },
              "Tasktwo" : {
                buildTask2()
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
                deployTask1()
              },
              "Tasktwo" : {
                deployTask2("${params.password}")
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
  //sleep 20
  sh('echo "is jenkins? ${IS_JENKINS}"')
  sh('echo ${EXAMPLE_CREDS_USR}')
  echo "User name is ${params.user}"
}

def buildTask2() {
  sh('echo ${EXAMPLE_CREDS_PSW}')
  echo "Executing task ${params.task}"
  echo "Env choice is ${params.environment}"
}

def deployTask1() {
  //sleep 20
  sh('echo "SSH private key is located at $SSH_CREDS"')
  echo "User name is ${params.user}"
}

def deployTask2(String pwd) {
  sh('echo "SSH user is $SSH_CREDS_USR"')
  echo "Is is tested? ${params.hasTested}"
  //echo "Password is ${params.password}"
  sh('echo "Password is $pwd"')
}
