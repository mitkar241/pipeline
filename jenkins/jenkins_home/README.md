# JENKINS_HOME
---

default value = `/var/lib/jenkins/`

## .ssh
---
copy `.ssh` from home directory to `JENKINS_HOME` 
```
id_rsa  id_rsa.pub  known_hosts
```
This will allow `Known hosts file Verification Strategy` as `Host Key Verification Strategy`
```
Checks the known_hosts file (~/.ssh/known_hosts) for the user Jenkins is executing under, to see if an entry exists that matches the current connection.
This method does not make any updates to the Known Hosts file, instead using the file as a read-only source and expecting someone with suitable access to the appropriate user account on the Jenkins controller to update the file as required, potentially using the ssh hostname command to initiate a connection and update the file appropriately.
(from SSH Build Agents plugin)
```
## contents
---
```
.cache
config.xml
copy_reference_file.log
credentials.xml
docker-compose.yaml
fingerprints
.groovy
hudson.model.UpdateCenter.xml
hudson.plugins.git.GitTool.xml
identity.key.enc
.java
jenkins.install.InstallUtil.lastExecVersion
jenkins.install.UpgradeWizard.state
jenkins.model.JenkinsLocationConfiguration.xml
jenkins.telemetry.Correlator.xml
jobs
.lastStarted
logs
nodeMonitors.xml
nodes
org.jenkinsci.plugins.resourcedisposer.AsyncResourceDisposer.xml
org.jenkinsci.plugins.workflow.flow.FlowExecutionList.xml
.owner
plugins
queue.xml
secret.key
secret.key.not-so-secret
secrets
.ssh
updates
userContent
users
war
workflow-libs
```
