#!/bin/bash
set -e

# create postgres user since it's not created when we specify a POSTGRES_USER user.
createuser --username "$POSTGRES_USER" --superuser

# create and populate Sakila
for i in postgres-sakila-schema.sql postgres-sakila-schema.sql
do
   psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" --file /postgres-sakila-db/$i
done

# tweak database to add a bit of missing data
for i in sakila-add-iso3166.sql sakila-add-states.sql
do
   psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" --file /postgres-sakila-extensions/$i
done

