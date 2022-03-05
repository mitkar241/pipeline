# Jenkins CLI
---

- download cli
```bash
wget http://localhost:8080/jnlpJars/jenkins-cli.jar
```

- set creds
```bash
USER="raktim";PASSWORD="12345678";JENKINSURL="http://127.0.0.1:8080/";PLUGIN_NAME="blueocean:1.25.3"
```

- install plugin
```bash
java -jar jenkins-cli.jar -auth ${USER}:${PASSWORD} -s ${JENKINSURL} install-plugin ${PLUGIN_NAME}
```

http://localhost:8080/cli/


http://localhost:8080/pluginManager/available
--> Available
blueocean

https://plugins.jenkins.io/blueocean/#releases
```
Install via cli: jenkins-plugin-cli --plugins blueocean:1.25.3
```
