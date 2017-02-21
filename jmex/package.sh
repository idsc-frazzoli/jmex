#!/bin/bash

mvn clean
mvn package

cp target/*.jar ../matlab


