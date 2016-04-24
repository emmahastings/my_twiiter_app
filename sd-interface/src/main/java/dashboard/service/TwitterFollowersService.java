package dashboard.service;

import model.TwitterFollower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emmakhastings on 17/04/2016.
 *
 * @author emmakhastings
 *         <p>
 *         Service to gather followers from authenticating user's account settings
 */
@Service
public class TwitterFollowersService {

    private Twitter twitter;

    @Autowired
    public TwitterFollowersService(Twitter twitter) {
        this.twitter = twitter;
    }

    public List<TwitterFollower> getFollowers() {

        // Get all followers IDs
        CursoredList<Long> followerIds = twitter.friendOperations().getFollowerIds();
        List<TwitterFollower> twitterFollowers = new ArrayList<>();

        followerIds.forEach(id -> {// Get follower profile
            TwitterProfile followerProfile = twitter.userOperations().getUserProfile(id);

            // Create new follower to return to view
            TwitterFollower twitterFollower = new TwitterFollower();
            twitterFollower.setName(followerProfile.getName());
            twitterFollower.setDescription(followerProfile.getDescription());
            twitterFollower.setLocation(followerProfile.getLocation());
            twitterFollower.setProfileUrl(followerProfile.getProfileUrl());
            twitterFollower.setScreenName(followerProfile.getScreenName());

            twitterFollowers.add(twitterFollower);
        });
        return twitterFollowers;
    }
}
