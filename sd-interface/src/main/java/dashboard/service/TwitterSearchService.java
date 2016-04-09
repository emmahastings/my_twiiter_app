package dashboard.service;

import model.TweetDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emmakhastings on 09/04/2016.
 *
 * @author emmakhastings
 *         <p>
 *         Service to perform a twitter search
 */
@Service
public class TwitterSearchService {

    private Twitter twitter;

    @Autowired
    public TwitterSearchService(Twitter twitter) {
        this.twitter = twitter;
    }

    public List<TweetDetails> search(String query) {
        SearchParameters params = new SearchParameters(query);
        params.lang("en");

        SearchResults results = twitter.searchOperations().search(params);
        List<TweetDetails> tweets = new ArrayList<>();

        for (Tweet tweet : results.getTweets()) {
            TweetDetails tweetDetails = new TweetDetails();
            tweetDetails.setTweet(tweet.getText());
            tweetDetails.setUserName(tweet.getFromUser());
            tweetDetails.setUserUrl(tweet.getUser().getUrl());
            tweetDetails.setUserLocation(tweet.getUser().getLocation());
            tweets.add(tweetDetails);
        }
        return tweets;
    }
}
