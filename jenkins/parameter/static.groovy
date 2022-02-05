#!/usr/bin/env groovy

import hudson.util.Secret
/*
http://localhost:5000/scriptApproval/
Signatures already approved:
- staticMethod hudson.util.Secret fromString java.lang.String
*/

defaultComment = '''Provide any comment here.
Comments might include purpose of this Job.'''

pipeline {
  agent none
  stages {
    stage('Setup parameters') {
      agent none
      steps {
        script {
          properties([
            parameters([
              string(name: 'username', defaultValue: 'root', description: 'Provide User Name'),
              // "defaultValue" for "password" is deprecated
              //reference: https://javadoc.jenkins.io/hudson/model/PasswordParameterDefinition.html#PasswordParameterDefinition-java.lang.String-java.lang.String-java.lang.String-
              password(name: 'password', defaultValueAsSecret: Secret.fromString('PWD12345'), description: 'Provide Password'),
              choice(name: 'environment', choices: ['dev', 'test', 'prod'], description: 'Provide env choice'),
              booleanParam(name: 'doSendMail', defaultValue: true, description: 'Toggle this value'),
              text(name: 'comment', defaultValue: defaultComment, description: 'Provide optional comment')
            ])
          ])
        }
      }
    }
    stage('Print parameters') {
      agent { label "build" }
      steps {
        printParameters()
      }
    }
  }
}

def printParameters() {
  echo "User name is: ${params.username}"
  echo "Password is: ${params.password}"
  echo "Environment choice is: ${params.environment}"
  echo "Do we send mail? ${params.doSendMail}"
  echo "Comment provided is: ${params.comment}"
}
