#!/bin/env bash

USER="raktim"
PASSWORD="XXXX"
JENKINSURL="http://127.0.0.1:8080/"

SRC_JOB="template"
NEW_JOB="newjob"

# Get the latest jenkins-cli.jar direct from the target Jenkins server
wget --no-check-certificate ${JENKINSURL}/jnlpJars/jenkins-cli.jar

# First part of Jenkins cli command line being used repeatedly
CLI_CMD="java -jar jenkins-cli.jar -auth ${USER}:${PASSWORD} -s ${JENKINSURL}"

# in case of authentication using private key
#$CLI_CMD -i ~/.ssh/id_rsa login

# check which commands supported by the client package
$CLI_CMD help

# get the configuration of the job
$CLI_CMD get-job $SRC_JOB > template.xml

# create a new job based on the configuration
$CLI_CMD create-job $NEW_JOB < template.xml

# clone a new job from a template job named SRC_JOB
$CLI_CMD copy-job $SRC_JOB $NEW_JOB

# get the config.xml of the new job
$CLI_CMD get-job $NEW_JOB > config.xml

# update the new job to apply the changes
$CLI_CMD update-job $NEW_JOB < config.xml

# the template job might be disabled by default, now enable the new job
$CLI_CMD enable-job $NEW_JOB

# disable a job
$CLI_CMD disable-job $NEW_JOB

# build the feature branch job
$CLI_CMD build $NEW_JOB

# logout from Jenkins in case of private key
#$CLI_CMD logout
