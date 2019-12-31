package cz.pia.cagy.accountingApp.controller;

import cz.pia.cagy.accountingApp.form.LoginForm;
import cz.pia.cagy.accountingApp.model.User;
import cz.pia.cagy.accountingApp.security.LoggedUser;
import cz.pia.cagy.accountingApp.service.RoleService;
import cz.pia.cagy.accountingApp.service.UserService;
import cz.pia.cagy.accountingApp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController extends BaseController
{
    private UserService userService;
    private RoleService roleService;

    public UserController(UserService userService, RoleService roleService)
    {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/user-detail")
    public ModelAndView userDetail(Authentication authentication)
    {
        ModelAndView modelAndView = new ModelAndView("user/detail");

        LoggedUser loggedUser = (LoggedUser) authentication.getPrincipal();

        modelAndView.getModelMap().addAttribute("user", this.userService.getUserById(loggedUser.getUserId()));


        return modelAndView;
    }

    @GetMapping(value = "/user-edit")
    public ModelAndView userEdit(Authentication authentication)
    {
        ModelAndView modelAndView = new ModelAndView("user/edit");

        LoggedUser loggedUser = (LoggedUser) authentication.getPrincipal();

        ModelMap modelMap = modelAndView.getModelMap();

        modelMap.addAttribute("user", this.userService.getUserById(loggedUser.getUserId()));
        modelMap.addAttribute("roles", this.roleService.getRoles());
        modelMap.addAttribute("formUrl", "/user-edit");
        modelMap.addAttribute("formSubmit", "Uložit");

        return modelAndView;
    }

    @PostMapping(value = "/user-edit")
    public String userEditSave(@Valid User user, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }

        this.userService.saveUser(user);

        return "redirect:/user-detail";
    }


}
