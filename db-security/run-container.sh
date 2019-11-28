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
volume='alexandria_sec_pgdata'

docker volume create "${volume}"
volume="${volume}:/var/lib/postgresql/data"

if [ "${pgpwd}Z" = 'Z' ];
then
  pgpwd='postgres'
fi

docker run --rm -P \
  --name "${container}" \
  -p "${bind_address}" \
  -e POSTGRES_PASSWORD="${pgpwd}" \
  -v "${volume}" \
  -d "${image}"
