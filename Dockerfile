FROM postgres:latest


RUN install --directory /docker-entrypoint-initdb.d

ADD postgres-sakila-db /postgres-sakila-db

ADD scripts/init-sakila-db.sh /docker-entrypoint-initdb.d
#ADD scripts/init-sakila-db.sh /postgres-sakila-db

