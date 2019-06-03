#
# Extension to official PostgreSQL docker image to automatically install 
# (modified) Sakila database.
#
FROM postgres:11
MAINTAINER Bear Giles <bgiles@snaplogic.com>

RUN install --directory /docker-entrypoint-initdb.d

# copy files required to initialize the Sakila database
ADD postgres-sakila-db /postgres-sakila-db
ADD postgres-sakila-extensions /postgres-sakila-extensions
ADD init-sakila-db.sh /docker-entrypoint-initdb.d
