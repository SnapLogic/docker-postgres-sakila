/*
 * Copyright (C) 2019, SnapLogic, Inc.  All rights reserved.
 *
 * "SnapLogic" is a trademark of SnapLogic, Inc.
 */
package com.snaplogic.docker.sakila.ldap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.snaplogic.docker.sakila.db.Loader;
import com.snaplogic.docker.sakila.model.Person;
import com.snaplogic.docker.sakila.model.Store;

/**
 * Convert Sakila database to corresponding LDIF files. 
 *
 * This is a quickie conversion that isn't remotely production ready, e.g.,
 * people may share a name or an only have a single name, but it's good enough
 * for testing LDAP clients.
 *
 * Limitations:
 *  - I don't know an appropriate structual objectClass for a store
 *  - I don't know how to represent the many-to-many relationship between Staff and Store.
 *
 * Database source: https://github.com/jOOQ/jOOQ/tree/master/jOOQ-examples/Sakila
 */
public class ConvertToLdif {
    private static final File DESTDIR = new File("image/openldap-sakila/sakila");

    /**
     * Create 'person.ldif' file.
     *
     * @param staff
     * @param customers
     * @throws IOException
     */
    public void createPersonFile(List<Person> staff, List<Person> customers) throws IOException {
        final URL url = Thread.currentThread().getContextClassLoader().getResource("templates/ldap/person.ldif.vm");
        final File file = new File(url.getFile());
        final Properties props = new Properties();
        props.put("file.resource.loader.path", file.getParent().toString());
        final VelocityEngine engine = new VelocityEngine();
        engine.init(props);

        final Template t = engine.getTemplate(file.getName());
        final VelocityContext context = new VelocityContext();
        context.put("staffList", staff);
        context.put("customerList", customers);

        File ldif = new File(DESTDIR, "person.ldif");
        ldif.getParentFile().mkdirs();
        try (Writer writer = new FileWriter(ldif)) {
            t.merge(context, writer);
        }
    }

    /**
     * Create 'store.ldif' file.
     *
     * @param stores
     * @throws IOException
     */
    public void createStoreFile(List<Store> stores) throws IOException {
        final URL url = Thread.currentThread().getContextClassLoader().getResource("templates/ldap/store.ldif.vm");
        final File file = new File(url.getFile());
        final Properties props = new Properties();
        props.put("file.resource.loader.path", file.getParent().toString());
        final VelocityEngine engine = new VelocityEngine();
        engine.init(props);

        final Template t = engine.getTemplate(file.getName());
        final VelocityContext context = new VelocityContext();
        context.put("storeList", stores);

        File ldif = new File(DESTDIR, "store.ldif");
        ldif.getParentFile().mkdirs();
        try (Writer writer = new FileWriter(ldif)) {
            t.merge(context, writer);
        }
    }

    public static void main(String[] args) throws Exception {
        final Loader loader = new Loader();
        final List<Person> staff = loader.loadStaff();
        final List<Person> customers = loader.loadCustomers();
        final List<Store> stores = loader.loadStores();

        ConvertToLdif converter = new ConvertToLdif();
        converter.createPersonFile(staff, customers);
        converter.createStoreFile(stores);
    }
}
