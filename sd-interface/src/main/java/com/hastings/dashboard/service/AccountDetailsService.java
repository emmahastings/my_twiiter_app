package com.hastings.dashboard.service;

import com.hastings.model.UserDetails;

/**
 * Created by emmakhastings on 02/04/2016.
 *
 * @author emmakhastings
 *         <p>
 *         Service to create a representation of relevant user details
 */
public interface AccountDetailsService {
    UserDetails getUserDetails();

    UserDetails getUserDetailsById(Long id);
}
