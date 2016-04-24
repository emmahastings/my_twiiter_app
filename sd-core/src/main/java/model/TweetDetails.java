package model;

/**
 * Created by emmakhastings on 28/02/2016.
 * @author emmakhastings
 *
 *  Model class to hold tweet details
 */
public class TweetDetails {

    private String tweet;

    private UserDetails user;

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }
}
