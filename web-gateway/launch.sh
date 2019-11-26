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
$JAVA_HOME/bin/java -jar target/web-gateway-0.1.0.jar
