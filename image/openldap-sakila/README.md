# snapdevteam/openldap-sakila
Osixia/openldap initialized with the user information from the Sakila
sample database.

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
* staff_id are bumped by 1000 so they don't overlap customer_id.

Note: there's no ISO-3166 entry for Yugoslavia.

This allows us to create LDAP entries for the 'staff' and 'customer'
tables that contain the 'ST' and 'C' attributes.

### LDAP .ldif files

The 'staff' and 'customer' tables are exported as .ldif files
that can be loaded into an LDAP server using 'ldapmodify'.

The 'store' table is also exported as an .ldif file but it can't
be loaded due to the lack of a structual objectClass. (Suggestions
welcome).

All .ldif files have {{ LDAP_BASE_DN }} instead of an explicit
base dn. This allows the base dn to be specified at runtime. It
works transparently with osixia/openldap.

These files can be recreated by running the java application included.
You will need to edit the database connection properties.

Passwords are stored as plaintext 'password' instead of encoded
as {SSHA}. It can be set after initialization with a call to 'ldapmodify'.
(I'll change the .ldif file if I can find a clean way to calculate it
dynamically.)
