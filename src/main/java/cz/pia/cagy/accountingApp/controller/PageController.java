package cz.pia.cagy.accountingApp.controller;

import cz.pia.cagy.accountingApp.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController extends BaseController
{

    private UserServiceImpl userManager;

    public PageController(UserServiceImpl userManager)
    {

        this.userManager = userManager;
    }

    @RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
    public String homepage()
    {
        return "pages/homepage";
    }

    @GetMapping(value = "/pages/contact")
    public String contact()
    {
        return "pages/contact.html";
    }

    @GetMapping(value = "/pages/info")
    public String info()
    {
        return "pages/info.html";
    }
}
