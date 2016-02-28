package twitter_fetch.Controller;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        model.addAttribute("results", results.getTweets());
        return "dashboard";
    }

}