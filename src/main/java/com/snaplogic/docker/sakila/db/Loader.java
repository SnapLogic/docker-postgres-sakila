/*
 * Copyright (C) 2019, SnapLogic, Inc.  All rights reserved.
 *
 * "SnapLogic" is a trademark of SnapLogic, Inc.
 */
package com.snaplogic.docker.sakila.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.snaplogic.docker.sakila.model.Address;
import com.snaplogic.docker.sakila.model.Person;
import com.snaplogic.docker.sakila.model.Store;

/**
 * Convert Sakila database to corresponding LDIF files. 
 *
 * This is a quickie conversion that isn't remotely production ready, e.g.,
 * people may share a name or an only have a single name, but it's good enough
 * for testing LDAP clients.
 *
 * Store table: store number, manager id, address
 *
 * Database source: https://github.com/jOOQ/jOOQ/tree/master/jOOQ-examples/Sakila
 */
public class Loader {

    // @formatter:off
    private static final String LIST_CUSTOMERS_QUERY =
            "select customer_id, store_id, cl.name, c.first_name, c.last_name, c.email, "
                    + "c.activebool, c.create_date, c.last_update, cl.phone, "
                    + "a.address, a.address2, cl.city, a.postal_code, cl.country, "
                    + "city.state, country.code2 "
            + "from customer c "
                + "join customer_list cl on (c.customer_id = cl.id)"
                + "join address a on (c.address_id = a.address_id)"
                + "join city on (a.city_id = city.city_id) "
                + "join country on (city.country_id = country.country_id)";
    // @formatter:on

    // @formatter:off
    private static final String LIST_STAFF_QUERY =
            "select staff_id, store_id, sl.name, s.first_name, s.last_name, s.email, "
                    + "s.username, s.password, s.active, s.last_update, sl.phone, "
                    + "a.address, a.address2, sl.city, a.postal_code, sl.country, "
                    + "city.state, country.code2 "
            + "from staff s "
                + "join address a on (s.address_id = a.address_id) "
                + "join staff_list sl on (s.staff_id = sl.id) "
                + "join city on (a.city_id = city.city_id) "
                + "join country on (city.country_id = country.country_id)";
    // @formatter:on

    // @formatter:off
    private static final String LIST_STORE_QUERY =
            "select store_id, s.last_update, a.phone, "
                    + "a.address, a.address2, city.city, a.postal_code, city.state, c.country, "
                    + "city.state, c.code2 "
            + "from store s "
                + "join address a on (s.address_id = a.address_id) "
                + "join city on (a.city_id = city.city_id) "
                + "join country c on (city.country_id = c.country_id)";
    // @formatter:on

    /**
     * Connect to database
     *
     * @return
     * @throws SQLException
     */
    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://192.168.1.15/sakila", "bgiles", "bgiles");
    }

    /**
     * Load customers table
     * @return
     * @throws SQLException
     */
    public List<Person> loadCustomers() throws SQLException {
        final List<Person> customers = new ArrayList<>();

        try (Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(LIST_CUSTOMERS_QUERY);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    final Person person = new Person();
                    person.setId(rs.getInt("customer_id"));
                    person.setName(rs.getString("name"));
                    person.setFirstName(rs.getString("first_name"));
                    person.setLastName(rs.getString("last_name"));
                    // person.setPassword("8cb2237d0679ca88db6464eac60da96345513964");
                    person.setPassword("password");

                    final String email = rs.getString("email");
                    if (!rs.wasNull() && !email.trim().isEmpty()) {
                        person.setEmail(email);
                    }

                    final String phoneNumber = rs.getString("phone");
                    if (!rs.wasNull() && !phoneNumber.trim().isEmpty()) {
                        person.setPhoneNumber(phoneNumber);
                    }

                    final Address address = new Address();
                    address.setStreet(rs.getString("address"));
                    final String street2 = rs.getString("address2");
                    if (!rs.wasNull() && !street2.trim().isEmpty()) {
                        address.setStreet2(street2);
                    }
                    address.setCity(rs.getString("city"));
                    final String state = rs.getString("state");
                    if (!rs.wasNull() && !state.trim().isEmpty()) {
                        address.setState(state);
                    }
                    final String country = rs.getString("code2");
                    if (!rs.wasNull() && !country.trim().isEmpty()) {
                        address.setCountry(country);
                    }
                    person.setAddress(address);

                    final Date dateCreated = rs.getDate("create_date");
                    if (!rs.wasNull()) {
                        person.setDateCreated(new java.util.Date(dateCreated.getTime()));
                    }
                    final Timestamp lastUpdate = rs.getTimestamp("last_update"); 
                    if (!rs.wasNull()) {
                        person.setLastUpdate(new java.util.Date(lastUpdate.getTime()));
                    }

                    final boolean active = rs.getBoolean("activebool");
                    if (!rs.wasNull()) {
                        person.setStatus((active) ? "active" : "inactive");
                    } else {
                        person.setStatus("active");
                    }

                    // ignore store_id

                    customers.add(person);
                }
        }

        return customers;
    }

    /**
     * Load staff table
     * @return
     * @throws SQLException
     */
    public List<Person> loadStaff() throws SQLException {
        final List<Person> staff = new ArrayList<>();

        try (Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(LIST_STAFF_QUERY);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    final Person person = new Person();
                    person.setId(rs.getInt("staff_id"));
                    person.setName(rs.getString("name"));
                    person.setFirstName(rs.getString("first_name"));
                    person.setLastName(rs.getString("last_name"));
                    // person.setPassword(rs.getString("password"));
                    person.setPassword("password");
                    person.setHomeDirectory(getHomeDirectory(rs));
                    person.setGecos(getGecos(rs));

                    final String phoneNumber = rs.getString("phone");
                    if (!rs.wasNull() && !phoneNumber.trim().isEmpty()) {
                        person.setPhoneNumber(phoneNumber);
                    }

                    final String email = rs.getString("email");
                    if (!rs.wasNull() && !email.trim().isEmpty()) {
                        person.setEmail(email);
                    }

                    final Address address = new Address();
                    address.setStreet(rs.getString("address"));
                    final String street2 = rs.getString("address2");
                    if (!rs.wasNull() && !street2.trim().isEmpty()) {
                        address.setStreet2(street2);
                    }

                    address.setCity(rs.getString("city"));

                    final String state = rs.getString("state");
                    if (!rs.wasNull() && !state.trim().isEmpty()) {
                        address.setState(state);
                    }

                    final String country = rs.getString("code2");
                    if (!rs.wasNull() && !country.trim().isEmpty()) {
                        address.setCountry(country);
                    }

                    person.setAddress(address);

                    final Timestamp whenChanged = rs.getTimestamp("last_update"); 
                    if (!rs.wasNull()) {
                        person.setLastUpdate(new java.util.Date(whenChanged.getTime()));
                    }

                    final boolean active = rs.getBoolean("active");
                    if (!rs.wasNull()) {
                        person.setStatus((active) ? "active" : "inactive");
                    } else {
                        person.setStatus("active");
                    }

                    // ignore store_id

                    staff.add(person);
                }
        }

        return staff;
    }

    /**
     * Load store table
     * @return
     * @throws SQLException
     */
    public List<Store> loadStores() throws SQLException {
        final List<Store> stores = new ArrayList<>();

        try (Connection conn = connect();
            PreparedStatement ps = conn.prepareStatement(LIST_STORE_QUERY);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    final Store store = new Store();
                    store.setId(rs.getInt("store_id"));
                    store.setName("Store " + rs.getString("store_id"));

                    final String phoneNumber = rs.getString("phone");
                    if (!rs.wasNull() && !phoneNumber.trim().isEmpty()) {
                        store.setPhoneNumber(phoneNumber);
                    }

                    final Address address = new Address();
                    address.setStreet(rs.getString("address"));
                    final String street2 = rs.getString("address2");
                    if (!rs.wasNull() && !street2.trim().isEmpty()) {
                        address.setStreet2(street2);
                    }

                    address.setCity(rs.getString("city"));

                    final String state = rs.getString("state");
                    if (!rs.wasNull() && !state.trim().isEmpty()) {
                        address.setState(state);
                    }

                    final String country = rs.getString("code2");
                    if (!rs.wasNull() && !country.trim().isEmpty()) {
                        address.setCountry(country);
                    }

                    store.setAddress(address);

                    final Timestamp whenChanged = rs.getTimestamp("last_update"); 
                    if (!rs.wasNull()) {
                        store.setLastUpdate(new java.util.Date(whenChanged.getTime()));
                    }

                    stores.add(store);
                }
        }

        return stores;
    }

    /**
     * Get home directory
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public String getHomeDirectory(ResultSet rs) throws SQLException {
        return "/home/" + rs.getString("last_name") + "." + rs.getString("first_name");
    }

    /**
     * Get GECOS value.
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    public String getGecos(ResultSet rs) throws SQLException {
        return rs.getString("name") + ",,,," + rs.getString("email");
    }
}
