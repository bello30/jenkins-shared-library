#!/usr/bin/env groovy

def call() {
    echo "building docker image…"
    withCredentials([usernamePassword(credentialsId: 'github-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]){
        sh 'docker build -t bello3035/demo-app:jma-2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push bello3035/demo-app:jma-2.0'
    }
}
