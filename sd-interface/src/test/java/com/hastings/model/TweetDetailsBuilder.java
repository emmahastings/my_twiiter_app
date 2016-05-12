package com.hastings.model;

/**
 * Created by emmakhastings on 08/05/2016.
 *
 * @author emmakhastings
 */
public class TweetDetailsBuilder {
    private TweetDetails tweetDetails = new TweetDetails();

    public TweetDetailsBuilder setTweet(String tweet) {
        tweetDetails.setTweet(tweet);
        return this;
    }

    public TweetDetailsBuilder setUser(UserDetails user) {
        tweetDetails.setUser(user);
        return this;
    }

    public TweetDetails build() {
        return tweetDetails;
    }
}