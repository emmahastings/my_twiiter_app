package twitter_fetch.Controller;

import javax.inject.Inject;
import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;
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
public class ConnectionController {

    private Twitter twitter;

    private ConnectionRepository connectionRepository;

    @Autowired
    public ConnectionController(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String connectTwitter(Model model) {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        } else {
            return "redirect:/home";
        }
    }
}