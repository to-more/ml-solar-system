#!/usr/bin/env bash

rootDir=${PWD}

if [[ -n $1 ]]
then
    rootDir=$1
fi

cd ${rootDir}

echo "Building directory ${rootDir}"

echo "Building boot application"
./gradlew clean build

docker build . --build-arg builddir=build/libs --build-arg appname=ml-solar-system --build-arg version=1.0.0-SNAPSHOT -t ml-solar-system
echo "Done building boot application"