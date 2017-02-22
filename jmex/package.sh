#!/bin/bash

mvn -q clean
mvn -q package

cp target/*.jar ../matlab


