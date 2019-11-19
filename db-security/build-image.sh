#!/bin/bash

dir=$(dirname "${0}")
if [ "${dir}" = '.' ];
then
  dir=$(pwd)
fi

img=$(basename "${dir}")
docker build -t "${img}:latest" .
