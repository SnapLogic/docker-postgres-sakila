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
 * Common attributes for customers and staff
 */
public class Person {
    private Integer id;
    private String name;
    private String firstName;
    private String lastName;
    private String password;
    private Address address;
    private String email;
    private String phoneNumber;
    private Date dateCreated;
    private Date lastUpdate;
    private String status;

    // advanced authentication. Certificates are PEM-encoded strings.
    private String krbPrincipalName;
    private String userCertificate;
    private String caCertificate;

    // only required for staff
    private String homeDirectory;
    private String gecos;

    private String trim(String s) {
        if ((s != null) && !s.trim().isEmpty()) {
            return s.trim();
        }
        return null;
    }

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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = trim(firstName);
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = trim(lastName);
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = trim(password);
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the dateCreated
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * @param dateCreated the dateCreated to set
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * @return the whenCreated
     */
    public String getWhenCreated() {
        // not thread safe
        final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss.S'Z'");
        return (dateCreated != null) ? df.format(dateCreated) : null;
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
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return the whenChanged
     */
    public String getWhenChanged() {
        // not thread safe
        final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss.S'Z'");
        return (lastUpdate != null) ? df.format(lastUpdate) : null;
    }


    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the krbPrincipalName
     */
    public String getKrbPrincipalName() {
        return krbPrincipalName;
    }

    /**
     * @param krbPrincipalName the krbPrincipalName to set
     */
    public void setKrbPrincipalName(String krbPrincipalName) {
        this.krbPrincipalName = krbPrincipalName;
    }

    /**
     * @return the userCertificate
     */
    public String getUserCertificate() {
        return userCertificate;
    }

    /**
     * @param userCertificate the userCertificate to set
     */
    public void setUserCertificate(String userCertificate) {
        this.userCertificate = userCertificate;
    }

    /**
     * @return the caCertificate
     */
    public String getCaCertificate() {
        return caCertificate;
    }

    /**
     * @param caCertificate the caCertificate to set
     */
    public void setCaCertificate(String caCertificate) {
        this.caCertificate = caCertificate;
    }

    /**
     * @return the homeDirectory
     */
    public String getHomeDirectory() {
        return homeDirectory;
    }

    /**
     * @param homeDirectory the homeDirectory to set
     */
    public void setHomeDirectory(String homeDirectory) {
        this.homeDirectory = homeDirectory;
    }

    /**
     * @return the gecos
     */
    public String getGecos() {
        return gecos;
    }

    /**
     * @param gecos the gecos to set
     */
    public void setGecos(String gecos) {
        this.gecos = gecos;
    }
}
