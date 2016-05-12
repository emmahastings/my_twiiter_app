package com.hastings.dashboard.model;

/**
 * Created by emmakhastings on 08/05/2016.
 *
 * @author emmakhastings
 */
public class TwitterUserDetailsBuilder {
    private TwitterUserDetails userDetails = new TwitterUserDetails("test_user");

    public TwitterUserDetailsBuilder setId(Long id) {
        userDetails.setId(id);
        return this;
    }

    public TwitterUserDetailsBuilder setLanguage(String language) {
        userDetails.setLanguage(language);
        return this;
    }

    public TwitterUserDetailsBuilder setLocation(String location) {
        userDetails.setLocation(location);
        return this;
    }

    public TwitterUserDetailsBuilder setUrl(String url) {
        userDetails.setUrl(url);
        return this;
    }

    public UserDetails build() {
        return userDetails;
    }
}