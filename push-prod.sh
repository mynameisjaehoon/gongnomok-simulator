#!/bin/sh

# docker compose build --no-cache
# docker compose push

cd backend
./gradlew clean build
cd ..

docker compose rm
docker compose build --no-cache
docker compose push
