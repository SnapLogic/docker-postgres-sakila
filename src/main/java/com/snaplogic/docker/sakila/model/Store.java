/*
 * Copyright (C) 2019, SnapLogic, Inc.  All rights reserved.
 *
 * "SnapLogic" is a trademark of SnapLogic, Inc.
 */
package com.snaplogic.docker.sakila.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Store attributes
 */
public class Store {
    private Integer id;
    private String name;
    private String phoneNumber;
    private Address address;
    private Date lastUpdate;
    private String whenChanged;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the lastUpdate
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate the lastUpdate to set
     */
    public void setLastUpdate(Date lastChanged) {
        this.lastUpdate = lastChanged;
    }

    /**
     * @return the whenChanged
     */
    public String getWhenChanged() {
        // not thread safe
        final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss.S'Z'");
        return (lastUpdate != null) ? df.format(lastUpdate) : null;
    }
}
