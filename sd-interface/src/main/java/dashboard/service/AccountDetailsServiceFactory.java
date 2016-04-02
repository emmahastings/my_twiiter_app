package dashboard.service;

/**
 * Created by emmakhastings on 02/04/2016.
 *
 * @author emmakhastings
 *         <p>
 *         Factory to create the relevant AccountDetailsService
 */
public class AccountDetailsServiceFactory {

    public AccountDetailsService getService(String application) {

        AccountDetailsService accountDetailsService = null;

        switch (application) {
            case "twitter":
                accountDetailsService = new TwitterAccountDetailsService();
                break;
            default:
                accountDetailsService = new TwitterAccountDetailsService();
                break;
        }
        return accountDetailsService;
    }
}
