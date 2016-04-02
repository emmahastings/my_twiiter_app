package model;

/**
 * Created by emmakhastings on 02/04/2016.
 *
 * @author emmakhastings
 *         <p>
 *         Model class to hold twitter user details
 */
public class TwitterUserDetails extends UserDetails {
    private String language;

    private String trendLocationName;

    public TwitterUserDetails(String name) {
        super(name);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTrendLocationName() {
        return trendLocationName;
    }

    public void setTrendLocationName(String trendLocationName) {
        this.trendLocationName = trendLocationName;
    }
}
