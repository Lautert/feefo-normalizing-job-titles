#!/bin/bash

cd normalizing-job
mvn clean install
cd ..

docker-compose up --build -d
