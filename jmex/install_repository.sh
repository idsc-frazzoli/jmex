#!/bin/bash

mvn clean
mvn package

mvn install:install-file -Dfile=target/jmex-0.0.1.jar

