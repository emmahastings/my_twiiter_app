/**
 * Created by emmakhastings on 28/02/2016.
 * @author emmakhastings
 *
 *  Model class to hold tweet details
 */
public class TweetDetails {

    private String tweet;

    private String userName;

    private String userLocation;

    private String userUrl;

    public TweetDetails() {
    }

    public TweetDetails(String tweet, String userName, String userLocation, String userUrl) {
        this.tweet = tweet;
        this.userName = userName;
        this.userLocation = userLocation;
        this.userUrl = userUrl;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    public String getUserUrl() {
        return userUrl;
    }

    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }
}
