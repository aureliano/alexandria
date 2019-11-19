#!/bin/bash

dir=$(dirname "${0}")
if [ "${dir}" = '.' ];
then
  dir=$(pwd)
fi

container=$(basename "${dir}")

pgpwd=$1
image="${container}:latest"
bind_address='127.0.0.1:5433:5432'

if [ "${pgpwd}Z" = 'Z' ];
then
  pgpwd='postgres'
fi

docker run --name "${container}" --rm -P -p "${bind_address}" -e POSTGRES_PASSWORD="${pgpwd}" -d "${image}"
