--
-- Update 'staff' so there's no overlap between staff_id and
-- customer_id. Normally we would want one table with all people
-- and then separate tables for each role so one person could
-- be both staff and customer but this database didn't follow
-- that model.
--
BEGIN;
update staff set staff_id = staff_id + 1000;
COMMIT;
