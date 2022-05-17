# Role-based Authorization Strategy
---

[link](https://plugins.jenkins.io/role-strategy/)

The Role Strategy plugin is meant to be used from Jenkins to add a new role-based mechanism to manage users' permissions. Supported features

- Creating global roles, such as admin, job creator, anonymous, etc., allowing to set Overall, Agent, Job, Run, View and SCM permissions on a global basis.
- Creating project roles, allowing to set only Job and Run permissions on a project basis.
- Creating agent roles, allowing to set node-related permissions.
- Assigning these roles to users and user groups
- Extending role and permissions matching via Macro extensions

## Installing and enabling the plugin
---
The Role Strategy plugin can be installed from any Jenkins installation connected to the Internet using the Plugin Manager screen. Activate the Role-Based Strategy by using the standard *Manage Jenkins > Manage Global Security screen*

After the installation, the plugin can be configured using the Manage and Assign Roles screen accessible from Manage Jenkins.

## Configuring roles
---
You can define roles by using the Manages Roles screen. It is possible to define global and project/agent-specific roles.

- Global roles apply to any item in Jenkins and override anything you specify in the Project Roles. That is, when you give a role the right to Job-Read in the Global Roles, then this role is allowed to read all Jobs, no matter what you specify in the Project Roles.
- For project and agent roles you can set a regular expression pattern for matching items. The regular expression aimed at matching the full item name.
  - For example, if you set the field to Roger-.*, then the role will match all jobs which name starts with Roger-.
  - Patterns are case-sensitive. To perform a case-insensitive match, use (?i) notation: upper, Roger-.* vs. lower, roger-.* vs. case-insensitive, (?i)roger-.*.
  - Folders can be matched using expressions like ^foo/bar.*

## Assigning roles
---
You can assign roles to users and user groups using the Assign Roles screen

- User groups represent authorities provided by the Security Realm (e.g. LDAP plugin can provide groups)
- There are also two built-in groups: authenticated (users who logged in) and anonymous (any users, including ones who have not logged in)
