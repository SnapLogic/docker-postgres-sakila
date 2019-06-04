# TO DO List

* \[high\] Add mongodb-sakila.

* \[high\] Tweak the build process so it creates the sample database
in a live docker container (using Test Containers) instead of relying
on an external database.

* \[medium\] Tweak the maven project so it builds and pushes the maven images.
It wasn't worth the effort when the sample database would never change
but it's becoming clear that we'll want to have the ability enhance the
data. For instance we don't want to change the _payment_ table but we
could create a second one that uses the PostgreSQL _money_ type.

* \[low\] Tweak _staff_ and _customer_ tables so the password is pulled to
external table. This doesn't really affect us but it's good practice
and cutting corners in the test environment tends to lead to cutting
corners in production.