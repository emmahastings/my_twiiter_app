package com.hastings.dashboard.model;

/**
 * Created by emmakhastings on 08/05/2016.
 *
 * @author emmakhastings
 */
public class SearchFormBuilder {

    private SearchForm searchForm = new SearchForm();

    public SearchFormBuilder setQuery(String query) {
        searchForm.setQuery(query);
        return this;
    }

    public SearchForm build() {
        return searchForm;
    }
}
