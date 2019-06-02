/*
 * Copyright (C) 2019, SnapLogic, Inc.  All rights reserved.
 *
 * "SnapLogic" is a trademark of SnapLogic, Inc.
 */
package com.snaplogic.docker.sakila.model;

/**
 * Address
 */
public class Address {
    private String street;
    private String street2;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    private String trim(String s) {
        if ((s != null) && !s.trim().isEmpty()) {
            return s.trim();
        }
        return null;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = trim(street);
    }

    /**
     * @return the street2
     */
    public String getStreet2() {
        return street2;
    }

    /**
     * @param street2 the street2 to set
     */
    public void setStreet2(String street2) {
        this.street2 = trim(street2);
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = trim(city);
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = trim(state);
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the state to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = trim(postalCode);
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = trim(country);
    }

    /**
     * Get address on a single line
     */
    public String getAddress() {
        final StringBuffer address = new StringBuffer();
        address.append(getStreet());

        if (getStreet2() != null) {
            address.append(" " + getStreet2());
        }

        address.append(", ");
        address.append(getCity());

        if (getState() != null) {
            address.append(" ");
            address.append(getState());
        }

        if (getPostalCode() != null) {
            address.append(" ");
            address.append(getPostalCode());
        }

        if (getCountry() != null) {
            address.append(", ");
            address.append(getCountry());
        }

        return address.toString();
    }
}
