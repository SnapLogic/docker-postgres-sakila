--
-- Update 'staff' so there's no overlap between staff_id and
-- customer_id. Normally we would want one table with all people
-- and then separate tables for each role so one person could
-- be both staff and customer but this database didn't follow
-- that model.
--
BEGIN;
-- these tables have foreign reference but not 'ON CASCADE' clauses
alter table payment_p2007_01 drop constraint payment_p2007_01_staff_id_fkey;
alter table payment_p2007_01 add foreign key (staff_id)
   references staff(staff_id) on update cascade on delete restrict;  
alter table payment_p2007_02 drop constraint payment_p2007_02_staff_id_fkey;
alter table payment_p2007_02 add foreign key (staff_id)
   references staff(staff_id) on update cascade on delete restrict;  
alter table payment_p2007_03 drop constraint payment_p2007_03_staff_id_fkey;
alter table payment_p2007_03 add foreign key (staff_id)
   references staff(staff_id) on update cascade on delete restrict;  
alter table payment_p2007_04 drop constraint payment_p2007_04_staff_id_fkey;
alter table payment_p2007_04 add foreign key (staff_id)
   references staff(staff_id) on update cascade on delete restrict;  
alter table payment_p2007_05 drop constraint payment_p2007_05_staff_id_fkey;
alter table payment_p2007_05 add foreign key (staff_id)
   references staff(staff_id) on update cascade on delete restrict;  
alter table payment_p2007_06 drop constraint payment_p2007_06_staff_id_fkey;
alter table payment_p2007_05 add foreign key (staff_id)
   references staff(staff_id) on update cascade on delete restrict;  

-- we can now update the saff number
update staff set staff_id = staff_id + 1000;
COMMIT;
