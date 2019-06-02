# docker-postgres-sakila
Adds sakila database to official postgres docker image.

## Sakila

The database is initialized with the Sakila database as ported by the jOOQ
project. See: https://github.com/jOOQ/jOOQ/tree/master/jOOQ-examples/Sakila

The scripts are unmodified except for commenting out the creation of the
plpgsql language since it's already been defined. Tsk tsk - it forgot to
add 'if not exists'.

## Enhancements

We needed to add a few fields our purposes:

* adds 'state' column to city table
* populate 'state' column for US and Canadian cities
* adds 'code, 'code2', and 'code3' column to country table.
* populate these columns with ISO-3166 data.

Note: there's no ISO-3166 entry for Yugoslavia.

This allows us to create LDAP entries for the 'staff' and 'customer'
tables that contain the 'ST' and 'C' attributes.

### LDAP .ldif files

(to be done)

The 'staff' and 'customer' tables are exported as .ldif files that
can be loaded into an LDAP server using 'ldapmodify'.

### Digital certificates

(to be done)

Digital certificates and private keys will be created for every
record in the 'staff' and 'customer' tables. This allows testing
applications that require strong mutual authentication. The LDAP
.ldif files will be modified accordingly.

