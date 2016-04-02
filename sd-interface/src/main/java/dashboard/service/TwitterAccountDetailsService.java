package dashboard.service;

import model.TwitterUserDetails;
import org.springframework.social.twitter.api.AccountSettings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emmakhastings on 02/04/2016.
 *
 * @author emmakhastings
 *         <p>
 *         Service to create a representation of relevant twitter user details
 */
@Service
public class TwitterAccountDetailsService implements AccountDetailsService {

    /**
     * Create user details from authenticating user's account settings
     *
     * @param accountSettings authenticating user's account settings
     */
    public TwitterUserDetails createUserDetails(AccountSettings accountSettings) {
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

        twitterUserDetails.setTrendLocationName(String.join(" and ", locationNames));
        return twitterUserDetails;
    }
}



