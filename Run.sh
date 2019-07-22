#!/usr/bin/env bash

echo "Start database"
docker run -d --net=host --name mongo-solar-system mongo
echo "Done start database"
sleep 5

echo "Start boot application"
docker run -d --name ml-solar-system --net=host -m=512m ml-solar-system
echo "Done boot application"