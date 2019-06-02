FROM osixia/openldap:1.2.4
MAINTAINER Bear Giles <bgiles@snaplogic.com>

# copy .ldif files containing Sakila data
ADD sakila/person.ldif /container/service/slapd/assets/config/bootstrap/ldif/01-person.ldif
# ADD sakila/store.ldif /container/service/slapd/assets/config/bootstrap/ldif/02-store.ldif

# note: how to add certificates and modify environment
# ADD certs /container/service/slapd/assets/certs
# ADD environment /container/environment/01-custom
