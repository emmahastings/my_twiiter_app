package com.hastings.dashboard.model;

/**
 * Created by emmakhastings on 02/04/2016.
 *
 * @author emmakhastin gs
 *         <p>
 *         Parent class for all potential user details classes
 */

public abstract class UserDetails {

    private Long id;

    private String name;

    public UserDetails(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
