package dashboard.controller.twitter;

import dashboard.service.AccountDetailsService;
import dashboard.service.TwitterFollowersService;
import dashboard.service.TwitterSearchService;
import model.SearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by emmakhastings on 02/04/2016.
 *
 * @author emmakhastings
 *         <p>
 *         Controller to handle twitter functionality
 */
@Controller
@RequestMapping("/twitter_dashboard")
public class TwitterDashboardController {

    private AccountDetailsService accountDetailsService;

    private TwitterSearchService twitterSearchService;

    private TwitterFollowersService twitterFollowersService;

    @Autowired
    public TwitterDashboardController(@Qualifier("twitterAccountDetailsService") AccountDetailsService accountDetailsService,
                                      TwitterSearchService twitterSearchService,
                                      TwitterFollowersService twitterFollowersService) {
        this.accountDetailsService = accountDetailsService;
        this.twitterSearchService = twitterSearchService;
        this.twitterFollowersService = twitterFollowersService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getDashboard(Model model) {
        model.addAttribute("searchForm", new SearchForm());
        return "twitter/twitter_dashboard";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String searchTwitter(@ModelAttribute SearchForm searchForm, Model model) {
        model.addAttribute("searchForm", searchForm);
        model.addAttribute("tweets", twitterSearchService.search(searchForm.getQuery()));
        return "twitter/twitter_dashboard";
    }

    @RequestMapping(value = "/user_details",
            produces = MediaType.TEXT_HTML_VALUE,
            method = RequestMethod.GET)
    public String getUserDetails(Model model) {
        model.addAttribute("twitterUserDetails", accountDetailsService.getUserDetails());
        return "twitter/user_details";
    }

    @RequestMapping(value = "/user_details/{userId}",
            produces = MediaType.TEXT_HTML_VALUE,
            method = RequestMethod.GET)
    public String getUserDetailsFromId(Model model, @PathVariable Long userId) {
        model.addAttribute("twitterUserDetails", accountDetailsService.getUserDetailsById(userId));
        return "twitter/user_details";
    }


    @RequestMapping(value = "/followers",
            produces = MediaType.TEXT_HTML_VALUE,
            method = RequestMethod.GET)
    public String getFollowers(Model model) {
        model.addAttribute("twitterFollowers", twitterFollowersService.getFollowers());
        return "twitter/followers";
    }
}
