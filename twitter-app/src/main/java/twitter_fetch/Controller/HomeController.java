package twitter_fetch.Controller;

import javax.inject.Inject;
import javax.naming.directory.SearchResult;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter_fetch.Model.TweetDetails;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private Twitter twitter;

    private ConnectionRepository connectionRepository;

    @Inject
    public HomeController(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String connectTwitter(Model model) {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

        SearchParameters params = new SearchParameters("#dublin #job");
        params.lang("en");

        SearchResults results = twitter.searchOperations().search(params);
        List<TweetDetails> tweets = new ArrayList<>();

        for (Tweet result : results.getTweets()) {
            TweetDetails tweetDetails = new TweetDetails();
            tweetDetails.setTweet(result.getText());
            tweetDetails.setUserName(result.getFromUser());
            tweetDetails.setUserUrl(result.getUser().getUrl());
            tweetDetails.setUserLocation(result.getUser().getLocation());
            tweets.add(tweetDetails);
        }

        model.addAttribute("tweets", tweets);
        return "dashboard";
    }

}