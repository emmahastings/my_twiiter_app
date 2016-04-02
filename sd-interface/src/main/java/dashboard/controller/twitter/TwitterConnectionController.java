
package dashboard.controller.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by emmakhastings on 02/03/2016.
 *
 * @author emmakhastings
 *         <p>
 *         Contoller to handle user connecting to twitter
 */

@Controller
@RequestMapping("/twitter_connect")
public class TwitterConnectionController {

    private Twitter twitter;

    private ConnectionRepository connectionRepository;

    @Autowired
    public TwitterConnectionController(Twitter twitter, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String connectTwitter(Model model) {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        } else {
            return "redirect:/twitter_dashboard";
        }
    }
}

