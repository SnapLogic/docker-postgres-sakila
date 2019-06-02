--
-- Add 'state' column for US and Canadian cities.
--
-- Note: the "correct" way to do this is to create a new 'state' table,
-- populate it with ISO-3166(?) data, and add a reference to that table
-- instead of only adding the name of the state here. I might make that
-- tweak in the future.
--
BEGIN;
alter table city add column if not exists state varchar(20);

update city set state = 'Ohio' where city = 'Akron' and country_id = 103;
update city set state = 'Virginia' where city = 'Arlington' and country_id = 103;
update city set state = 'Georgia' where city = 'Augusta-Richmond County' and country_id = 103;
update city set state = 'Colorado' where city = 'Aurora' and country_id = 103;
update city set state = 'Washington' where city = 'Bellevue' and country_id = 103;
update city set state = 'Massachuetts' where city = 'Brockton' and country_id = 103;
update city set state = 'Florida' where city = 'Cape Coral' and country_id = 103;
update city set state = 'California' where city = 'Citrus Heights' and country_id = 103;
update city set state = 'Tennessee' where city = 'Clarksville' and country_id = 103;
update city set state = 'California' where city = 'Compton' and country_id = 103;
update city set state = 'Texas' where city = 'Dallas' and country_id = 103;
update city set state = 'Ohio' where city = 'Dayton' and country_id = 103;
update city set state = 'California' where city = 'El Monte' and country_id = 103;
update city set state = 'California' where city = 'Fontana' and country_id = 103;
update city set state = 'California' where city = 'Garden Grove' and country_id = 103;
update city set state = 'Texas' where city = 'Garland' and country_id = 103;
update city set state = 'Texas' where city = 'Grand Prairie' and country_id = 103;
update city set state = 'North Carolina' where city = 'Greensboro' and country_id = 103;
update city set state = 'Illinois' where city = 'Joliet' and country_id = 103;
update city set state = 'Missouri' where city = 'Kansas City' and country_id = 103;
update city set state = 'California' where city = 'Lancaster' and country_id = 103;
update city set state = 'Texas' where city = 'Laredo' and country_id = 103;
update city set state = 'Nebraska' where city = 'Lincoln' and country_id = 103;
update city set state = 'New Hampshire' where city = 'Manchester' and country_id = 103;
update city set state = 'Tennessee' where city = 'Memphis' and country_id = 103;
update city set state = 'Illinois' where city = 'Peoria' and country_id = 103;
update city set state = 'Virginia' where city = 'Roanoke' and country_id = 103;
update city set state = 'Illinois' where city = 'Rockford' and country_id = 103;
update city set state = 'Missouri' where city = 'Saint Louis' and country_id = 103;
update city set state = 'California' where city = 'Salinas' and country_id = 103;
update city set state = 'California' where city = 'San Bernardino' and country_id = 103;
update city set state = 'Michigan' where city = 'Sterling Heights' and country_id = 103;
update city set state = 'California' where city = 'Sunnyvale' and country_id = 103;
update city set state = 'Florida' where city = 'Tallahassee' and country_id = 103;
update city set state = 'Michigan' where city = 'Warren' and country_id = 103;

update city set state = 'Quebec' where city = 'Gatineau' and country_id = 20;
update city set state = 'Nova Scotia' where city = 'Halifax' and country_id = 20;
update city set state = 'Alberta' where city = 'Lethbridge' and country_id = 20;
update city set state = 'Ontario' where city = 'London' and country_id = 20;
update city set state = 'Ontario' where city = 'Richmond Hill' and country_id = 20;
update city set state = 'Ontario' where city = 'Oshawa' and country_id = 20;
update city set state = 'British Columbia' where city = 'Vancouver' and country_id = 20;
COMMIT;
