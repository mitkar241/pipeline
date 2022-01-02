#!/usr/bin/env groovy

/*
Additional Plugin required:
- Active Choices: https://plugins.jenkins.io/uno-choice/
*/

pipeline {
  agent any
  stages {
    stage('Parameters') {
      steps {
        script {
          properties([
            parameters([
              [$class: 'ChoiceParameter',
                choiceType: 'PT_SINGLE_SELECT',
                description: 'Select the Environemnt from the Dropdown List',
                filterLength: 1,
                filterable: false,
                name: 'setup',
                script: [
                  $class: 'GroovyScript',
                  fallbackScript: [
                    classpath: [],
                    sandbox: false,
                    script:
                    "return['Could not get the environemnts']"
                  ],
                  script: [
                    classpath: [],
                    sandbox: false,
                    script:
                    "return['dev','test','prod']"
                  ]
                ]
              ],
              [$class: 'CascadeChoiceParameter',
                choiceType: 'PT_SINGLE_SELECT',
                description: 'Select the Binary from the Dropdown List',
                name: 'binary',
                referencedParameters: 'setup',
                script: [$class: 'GroovyScript',
                  fallbackScript: [
                    classpath: [],
                    sandbox: false,
                    script: "return['Could not get Environment from setup Param']"
                  ],
                  script: [
                    classpath: [],
                    sandbox: false,
                    script: 
                    '''
                    if (setup.equals("dev")) {
                      return ["pkg.dev.deb", "pkg.dev.rpm", "pkg.dev.bat"]
                    } else if (setup.equals("test")) {
                      return ["pkg.test.deb", "pkg.test.rpm", "pkg.test.bat"]
                    } else if (setup.equals("prod")) {
                      return ["pkg.prod.deb", "pkg.prod.rpm", "pkg.prod.bat"]
                    }
                    '''
                  ]
                ]
              ],
              [$class: 'DynamicReferenceParameter',
                choiceType: 'ET_ORDERED_LIST',
                description: 'Select the Binary based on the following information',
                name: 'information',
                referencedParameters: 'setup',
                script: [$class: 'GroovyScript',
                  script: 'return["Could not get Binary Information"]',
                  script: [
                    script: 
                    '''
                    if (setup.equals("dev")) {
                      return ["pkg.dev.deb:  DEB package", "pkg.dev.rpm:  RPM package", "pkg.dev.bat:  BAT package"]
                    } else if (setup.equals("test")) {
                      return ["pkg.test.deb:  DEB package", "pkg.test.rpm:  RPM package", "pkg.test.bat:  BAT package"]
                    } else if (setup.equals("prod")) {
                      return ["pkg.prod.deb:  DEB package", "pkg.prod.rpm:  RPM package", "pkg.prod.bat:  BAT package"]
                    }
                    '''
                  ]
                ]
              ]
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
  echo "Setup is: ${params.setup}"
  echo "Binary chosen: ${params.binary}"
}