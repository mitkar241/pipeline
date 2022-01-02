#!/usr/bin/env groovy

/*
@description:
The script triggers PayloadJob on every node.
It uses Node and Label Parameter plugin to pass the job name to the payload job.
The code will require approval of several Jenkins classes in the Script Security mode

http://localhost:5000/scriptApproval/
Signatures already approved:
- field hudson.model.Slave name
- method hudson.model.AbstractCIBase getNodes
- staticMethod jenkins.model.Jenkins getInstance

if not approved, following error message can be found in console logs:
- Scripts not permitted to use field hudson.model.Slave name. Administrators can decide whether to approve or reject this signature.
*/

def branches = [:]
def names = nodeNames()
for (int i=0; i<names.size(); ++i) {
  def nodeName = names[i];
  // Into each branch we put the pipeline code we want to execute
  branches["node_" + nodeName] = {
    node(nodeName) {
      echo "Triggering on " + nodeName
    }
  }
}

// Now we trigger all branches
parallel branches

// This method collects a list of Node names from the current Jenkins instance
@NonCPS
def nodeNames() {
  return jenkins.model.Jenkins.instance.nodes.collect { node -> node.name }
}
