#!/bin/bash
set -e

#
# Install sakila database
#
# We don't have to worry about this being executed multiple times -
# the parent docker image will only run scripts in the
# /docker-endpoint-initdb.d directory if the data directory is empty.
# That means this script will never be run if there's already data
# in the database.
#

# create postgres user since it's not created when we specify a POSTGRES_USER user.
if [ $POSTGRES_USER != 'postgres' ]; then
   createuser --username "$POSTGRES_USER" --superuser postgres
fi

# create and populate Sakila
for i in postgres-sakila-schema.sql postgres-sakila-data.sql
do
   psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" --file /postgres-sakila-db/$i
done

# tweak database to add a bit of missing data
for i in sakila-add-iso3166.sql sakila-add-states.sql sakila-update-staff.sql
do
   psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" --file /postgres-sakila-extensions/$i
done

