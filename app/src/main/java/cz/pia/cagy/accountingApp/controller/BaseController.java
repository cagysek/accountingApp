package cz.pia.cagy.accountingApp.controller;

import cz.pia.cagy.accountingApp.model.security.LoggedUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Base controller of all controllers. Used for add common information to all controllers
 */
@Controller
public class BaseController
{

    /**
     * Add basic user info to data model.
     *
     * @param model          the model
     * @param authentication the authentication
     */
    @ModelAttribute
    public void addUserInfo(Model model, Authentication authentication)
    {
        if (authentication == null)
        {
            return;
        }

        LoggedUser loggedUser = (LoggedUser) authentication.getPrincipal();

        model.addAttribute("username", loggedUser.getUsername());
        model.addAttribute("role", loggedUser.getAuthorities());
        model.addAttribute("userId", loggedUser.getUserId());
    }
}
