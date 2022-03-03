#!/usr/bin/env bash

sudo apt update

# check for security packages
#apt list --upgradable | grep "\-security" | wc -l

sudo apt install ca-certificates -y

# install java jdk
sudo apt install default-jdk -y
java -version

# add jenkins key
wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -
sudo sh -c 'echo deb http://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'
sudo apt update

# install jenkins
sudo apt install jenkins -y

# if needed update config / port
sudo systemctl start jenkins
sudo systemctl status jenkins

# check initial admin password
sudo cat /var/lib/jenkins/secrets/initialAdminPassword
