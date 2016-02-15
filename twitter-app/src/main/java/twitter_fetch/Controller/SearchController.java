package twitter_fetch.Controller;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * Created by emmakhastings on 15/02/2016.
 * @author emmakhastings
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    private Twitter twitter;

    private ConnectionRepository connectionRepository;

    @Inject
    public SearchController(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method= RequestMethod.GET)
    public String helloTwitter(Model model) {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

        SearchParameters params = new SearchParameters("#dublin");
        params.lang("en");

        SearchResults results = twitter.searchOperations().search(params);
        model.addAttribute("results", results.getTweets());
        return "search";
    }

}
