FROM postgres:12.1-alpine

EXPOSE 5432

ENV POSTGRES_DB security
COPY security-schema.sql /docker-entrypoint-initdb.d/
