package dashboard.controller;


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
 * Created by emmakhastings on 02/03/2016.
 *
 * @author emmakhastings
 */
@Controller
@RequestMapping("/home")
public class TwitterController {

    private Twitter twitter;

    @Autowired
    public TwitterController(Twitter twitter) {
        this.twitter = twitter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getHome(Model model) {
        model.addAttribute("searchForm", new SearchForm());
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String greetingSubmit(@ModelAttribute SearchForm searchForm, Model model) {
        SearchParameters params = new SearchParameters(searchForm.getQuery());
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

        model.addAttribute("searchForm", searchForm);
        model.addAttribute("tweets", tweets);
        return "home";
    }


}
