#!/usr/bin/env groovy

def jobs = ["JobA", "JobB", "JobC"]

def generateStage(job) {
    return {
        stage("stage: ${job}") {
            echo "This is ${job}."
        }
    }
}

def parallelStagesMap = jobs.collectEntries {
    ["${it}" : generateStage(it)]
}

pipeline {
    agent none
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
