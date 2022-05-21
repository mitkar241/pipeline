def buildApp()
{
    sh 'npm install'
    sh 'mvn install'
    echo "building version ${NEW_VERSION}"
}

def testApp()
{
    echo 'testing the application...'
}

def deployApp()
{
    echo "deploying with ${SERVER_CREDENTIALS}"
    echo "deploying verson ${params.VERSION}"
    withCredentials([
        usernamePassword(credentials: 'server-credentials', usernameVariable: USER, passwordVariable: PWD)
        ])
        {
             sh "some script ${USER} ${PWD}"
        }
}

return this
