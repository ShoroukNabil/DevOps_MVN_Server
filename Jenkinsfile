pipeline {
    agent any

    stages {
        stage('Hello World') {
            steps {
                echo 'Hello World'
            }
        }

        stage('Check Versions') {
            steps {
                echo 'Checking Java version...'
                sh 'java -version || echo "Java not found"'

                echo 'Checking Maven version...'
                sh 'mvn -v || echo "Maven not found"'
            }
        }
    }
}
