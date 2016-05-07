package model;

/**
 * Created by emmakhastings on 08/05/2016.
 *
 * @author emmakhastings
 */
public class TwitterFollowerBuilder {

    private TwitterFollower twitterFollower = new TwitterFollower();

    public TwitterFollowerBuilder setName(String name) {
        twitterFollower.setName(name);
        return this;
    }

    public TwitterFollowerBuilder setScreenName(String screenName) {
        twitterFollower.setScreenName(screenName);
        return this;
    }

    public TwitterFollowerBuilder setLocation(String location) {
        twitterFollower.setLocation(location);
        return this;
    }

    public TwitterFollowerBuilder setDescription(String description) {
        twitterFollower.setDescription(description);
        return this;
    }

    public TwitterFollowerBuilder setProfileUrl(String profileUrl) {
        twitterFollower.setProfileUrl(profileUrl);
        return this;
    }

    public TwitterFollower build() {
        return twitterFollower;
    }
}
