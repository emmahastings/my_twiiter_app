package com.hastings.dashboard.model;

/**
 * Created by emmakhastings on 02/04/2016.
 *
 * @author emmakhastings
 *         <p>
 *         Model class to hold twitter user details
 */
public class TwitterUserDetails extends UserDetails {
    private String language;

    private String location;

    private String url;

    public TwitterUserDetails(String name) {
        super(name);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
