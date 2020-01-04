package cz.pia.cagy.accountingApp.controller;

import cz.pia.cagy.accountingApp.model.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Controller for process login
 */
@Controller
public class LoginController extends BaseController
{
    /**
     * Shows login form
     *
     * @return the model and view
     */
    @GetMapping(value = "/login")
    public ModelAndView login()
    {
        ModelAndView modelAndView = new ModelAndView("login/default");
        modelAndView.getModelMap().addAttribute("loginForm", new LoginForm());

        return modelAndView;
    }

    /**
     * Handle log in
     *
     * @param loginForm     the login form
     * @param bindingResult the binding result
     * @param logout        the logout
     * @return the string
     */
    @PostMapping(value = "/login")
    public String checkPersonInfo(@Valid LoginForm loginForm, BindingResult bindingResult, String logout)
    {

        if (bindingResult.hasErrors())
        {
            return "login/default";
        }

        return "redirect:/";
    }

    /**
     * Logout.
     *
     * @return the string
     */
    @PostMapping(value = "/logout")
    public String logout()
    {
        return "redirect:/login";
    }


}
