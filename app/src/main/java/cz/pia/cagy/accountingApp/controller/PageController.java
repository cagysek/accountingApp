package cz.pia.cagy.accountingApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for display static pages
 * Accessed for all users
 */
@Controller
public class PageController extends BaseController
{

    /**
     * Homepage string.
     *
     * @return the string
     */
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String homepage()
    {
        return "pages/homepage";
    }

    /**
     * Contact string.
     *
     * @return the string
     */
    @GetMapping(value = "/pages/contact")
    public String contact()
    {
        return "pages/contact.html";
    }

    /**
     * Info string.
     *
     * @return the string
     */
    @GetMapping(value = "/pages/info")
    public String info()
    {
        return "pages/info.html";
    }
}
