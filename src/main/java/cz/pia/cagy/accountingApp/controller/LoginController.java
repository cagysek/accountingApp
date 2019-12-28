package cz.pia.cagy.accountingApp.controller;

import cz.pia.cagy.accountingApp.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController extends BaseController
{
    @GetMapping(value = "/login")
    public ModelAndView login(Model model)
    {
        ModelAndView modelAndView = new ModelAndView("forms/loginForm");
        modelAndView.getModelMap().addAttribute("loginForm", new LoginForm());

        return modelAndView;
    }

    @PostMapping(value = "/login")
    public String checkPersonInfo(@Valid LoginForm loginForm, BindingResult bindingResult, String logout) {

        if (bindingResult.hasErrors()) {
            return "forms/loginForm";
        }

        return "redirect:/";
    }

    @PostMapping(value = "/logout")
    public String logout()
    {
        return "redirect:/login";
    }


}
