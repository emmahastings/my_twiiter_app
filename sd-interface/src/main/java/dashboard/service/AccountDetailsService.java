package dashboard.service;

import model.UserDetails;
import org.springframework.social.twitter.api.AccountSettings;

/**
 * Created by emmakhastings on 02/04/2016.
 *
 * @author emmakhastings
 *         <p>
 *         Service to create a representation of relevant user details
 */
public interface AccountDetailsService {
    UserDetails createUserDetails(AccountSettings accountSettings);
}
