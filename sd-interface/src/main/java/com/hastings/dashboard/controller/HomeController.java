
package com.hastings.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by emmakhastings on 02/03/2016.
 *
 * @author emmakhastings
 *         <p>
 *         Home contoller returning the home view with a list of various options
 */

@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String getHome(Model model) {
        return "home";
    }
}