pipeline {
  agent any

  parameters {
    choice(
      description: 'Run flyway database migration using latest master branch from prices in what environment?',
      name: 'environment',
      choices: ['PRE', 'PRO']
    )
  }

  stages {
    stage("Env Selection") {
      steps {
        echo "selectedEnvironment: ${params.environment}"
      }
    }
  }
}
