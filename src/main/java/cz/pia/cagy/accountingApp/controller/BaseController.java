package cz.pia.cagy.accountingApp.controller;

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

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("role", userDetails.getAuthorities());
    }
}
