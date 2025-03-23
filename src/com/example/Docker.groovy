#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {
    Docker(script) {
        this.script = script
    }
    def buildDockerImage(String imageName) {
        script.echo "building the docker images"
        script.withCredentials([usernamePassword(credentialsId: 'docker-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh 'docker build -t $imageName .'
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
            script.sh 'docker push $imageName'
        }
    }
}