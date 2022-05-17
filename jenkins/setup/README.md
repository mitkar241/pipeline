# Jenkins Installation
---

## Using `source.list`
---
- [install_jenkins.sh](script/install_jenkins.sh)

## Using `debian` package
---
[Jenkins Debian Packages](https://pkg.jenkins.io/debian-stable/direct/)

- `wget`
```bash
wget https://pkg.jenkins.io/debian-stable/direct/jenkins_2.319.3_all.deb
```
- `curl`
```bash
curl https://pkg.jenkins.io/debian-stable/direct/jenkins_2.319.3_all.deb -o jenkins_2.319.3_all.deb
```
- `install`
```bash
sudo apt install ./jenkins_2.319.3_all.deb
```

## Using `snap`
---
Jenkins is available as a snap package in all currently supported versions of Ubuntu. To install it open the terminal and type:
```bash
sudo snap install jenkins --classic
```
