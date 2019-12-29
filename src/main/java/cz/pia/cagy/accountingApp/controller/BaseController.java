package cz.pia.cagy.accountingApp.controller;

import cz.pia.cagy.accountingApp.security.LoggedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class BaseController
{

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
