#
# Extension to official PostgreSQL docker image to automatically install 
# (modified) Sakila database.
#
FROM postgres:11
MAINTAINER Bear Giles <bgiles@snaplogic.com>

RUN install --directory /docker-entrypoint-initdb.d

# copy files required to initialize the Sakila database
ADD src/main/resources/postgres-sakila-db /postgres-sakila-db
ADD src/main/resources/postgres-sakila-extensions /postgres-sakila-extensions
ADD src/main/scripts/init-sakila-db.sh /docker-entrypoint-initdb.d

