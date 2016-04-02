package dashboard.controller.twitter;

import model.SearchForm;
import model.TweetDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchParameters;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emmakhastings on 02/04/2016.
 *
 * @author emmakhastings
 *
 * Controller to handle Twiiter functionality
 */
@Controller
@RequestMapping("/twitter_dashboard")
public class TwitterDashboardController {

    private Twitter twitter;

    @Autowired
    public TwitterDashboardController(Twitter twitter) {
        this.twitter = twitter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(Model model) {
        model.addAttribute("searchForm", new SearchForm());
        return "twitter_dashboard";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String searchTwitter(@ModelAttribute SearchForm searchForm, Model model) {
        SearchParameters params = new SearchParameters(searchForm.getQuery());
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

        model.addAttribute("searchForm", searchForm);
        model.addAttribute("tweets", tweets);
        return "twitter_dashboard";
    }
}
