package twitter_fetch.Model;

/**
 * Created by emmakhastings on 02/03/2016.
 * @author emmakhastings
 */
public class SearchForm {

    private String query;

    public SearchForm() {
    }

    public SearchForm(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
