#!/bin/bash

set -e

dir=$(dirname "${0}")
if [ "${dir}" = '.' ];
then
  dir=$(pwd)
fi

cd "${dir}"
. .env

mvn clean package
$JAVA_HOME/bin/java -jar target/web-alexandria-0.1.0.jar
