#!/bin/bash
cd app/
mvn clean package
cd ..
docker-compose up --build
