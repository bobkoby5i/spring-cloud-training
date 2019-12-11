#!/bin/bash
./mvnw clean package

docker rmi $(docker images | grep "<none>")

docker build -t "training/configuration" configuration