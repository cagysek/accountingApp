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
    public ModelAndView homepage()
    {
        ModelAndView modelAndView = new ModelAndView();
        ModelMap modelMap = modelAndView.getModelMap();

        modelAndView.setViewName("pages/homepage");
        modelMap.addAttribute("users", this.userManager.getUsers());

        return modelAndView;
    }

    @GetMapping(value = "/pages/contact")
    public ModelAndView contact()
    {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("pages/contact.html");

        return modelAndView;
    }

    @GetMapping(value = "/pages/info")
    public ModelAndView info()
    {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("pages/info.html");

        return modelAndView;
    }
}
