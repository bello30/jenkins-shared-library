#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {
    Docker(script) {
        this.script = script
    }
    def buildDockerImage(String imageName) {
        script.echo "building the docker images"
        script.sh "docker build -t $imageName ."
    }
    def dockerLogin() {
        script.withCredentials([usernamePassword(credentialsId: 'docker-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
        }
    }
    def dockerPush(String imageName) {
        script.sh "docker push $imageName"
    }
}