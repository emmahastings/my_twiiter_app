package com.hastings.dashboard.service;

import com.hastings.model.TwitterUserDetails;
import com.hastings.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.AccountSettings;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emmakhastings on 02/04/2016.
 *
 * @author emmakhastings
 *         <p>
 *         Create user details from authenticating user's account settings
 */
@Service
public class TwitterAccountDetailsService implements AccountDetailsService {

    private Twitter twitter;

    @Autowired
    public TwitterAccountDetailsService(Twitter twitter) {
        this.twitter = twitter;
    }

    public UserDetails getUserDetails() {
        AccountSettings accountSettings = twitter.userOperations().getAccountSettings();
        return createUserDetails(accountSettings);
    }

    public UserDetails getUserDetailsById(Long id) {
        TwitterProfile userProfile = twitter.userOperations().getUserProfile(id);
        return createUserDetailsFromId(userProfile);
    }

    private UserDetails createUserDetailsFromId(TwitterProfile userProfile) {
        TwitterUserDetails twitterUserDetails = new TwitterUserDetails(userProfile.getScreenName());
        twitterUserDetails.setLocation(userProfile.getLocation());
        twitterUserDetails.setLanguage(userProfile.getLanguage());

        if (userProfile.getProfileUrl() != null) {
            twitterUserDetails.setUrl(userProfile.getProfileUrl());
        } else {
            twitterUserDetails.setUrl(userProfile.getUrl());
        }
        return twitterUserDetails;
    }

    private TwitterUserDetails createUserDetails(AccountSettings accountSettings) {
        TwitterUserDetails twitterUserDetails = new TwitterUserDetails(accountSettings.getScreenName());
        twitterUserDetails.setLanguage(accountSettings.getLanguage());

        // Attempt to get some location data
        List<AccountSettings.TrendLocation> locations = accountSettings.getTrendLocation();
        List<String> locationNames = new ArrayList<>();

        if (locations != null && !locations.isEmpty())
            for (AccountSettings.TrendLocation location : locations) {
                locationNames.add(location.getName());
            }
            // default to timezone if no trend locations found
        else {
            locationNames.add(accountSettings.getTimeZone().getName());
        }
        twitterUserDetails.setLocation(String.join(" and ", locationNames));
        return twitterUserDetails;
    }
}



