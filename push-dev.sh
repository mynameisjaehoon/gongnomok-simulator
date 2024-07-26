#!/bin/sh

cd backend
./gradlew clean build
cd ..

docker compose -f docker-compose-dev.yml rm
docker compose -f docker-compose-dev.yml build --no-cache
docker compose -f docker-compose-dev.yml push

