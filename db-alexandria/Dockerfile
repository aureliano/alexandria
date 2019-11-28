FROM postgres:12.1-alpine

EXPOSE 5432

ENV POSTGRES_DB alexandria
COPY alexandria-schema.sql /docker-entrypoint-initdb.d/
