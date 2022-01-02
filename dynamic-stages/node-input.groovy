#!/usr/bin/env groovy

// enter valid agent label name in the array and the sequence
def agents  = ['build-1', 'build-2', 'prod-1', 'prod-2']
//global maps to keep parallel processings separate
labelMap = [:]
seqMap = [:]

def generateStage(nodeInfo) {
    labelMap[nodeInfo] = nodeInfo.split('-')[0]
    seqMap[nodeInfo] = nodeInfo.split('-')[1]
    if (labelMap[nodeInfo] == 'build') {
        return {
            stage("Stage:${nodeInfo}") {
                node(labelMap[nodeInfo]) {
                    //testing failFast
                    //sh('abracadabra')
                    echo "Running on ${nodeInfo}"
                    sh('hostname')
                    sh('free -m')
                }
            }
        }
    } else if (labelMap[nodeInfo] == 'prod') {
        return {
            stage("Stage:${nodeInfo}") {
                node(labelMap[nodeInfo]) {
                    echo "Running on ${nodeInfo}"
                    sh('hostname')
                    sh('ifconfig ' + 'enp0s3')
                    //testing failFast
                    //sleep 5
                    //sleep 5
                }
            }
        }
    } else {
        return {
            stage("Stage:${nodeInfo}") {
                node(labelMap[nodeInfo]) {
                    echo "Running on ${nodeInfo}"
                    sh('hostname')
                    sh('python3 --version')
                }
            }
        }
    }
}

def parallelStagesMap = agents.collectEntries {
    ["${it}" : generateStage(it)]
}

pipeline {
    agent none
    //options {
        // Method 1
        //works with just parallel
        //parallelsAlwaysFailFast()
    //}
    stages {
        stage('non-parallel stage') {
            steps {
                echo 'This stage will be executed first.'
            }
        }
        stage('parallel stage') {
            steps {
                script {
                    // Method 2
                    parallelStagesMap.failFast = true
                    parallel parallelStagesMap
                }
            }
        }
    }
}
