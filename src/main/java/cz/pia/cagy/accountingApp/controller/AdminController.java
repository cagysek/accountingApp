package cz.pia.cagy.accountingApp.controller;

import cz.pia.cagy.accountingApp.model.User;
import cz.pia.cagy.accountingApp.service.RoleService;
import cz.pia.cagy.accountingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController extends BaseController
{
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService)
    {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin/users")
    public ModelAndView usersOverview()
    {
        ModelAndView modelAndView = new ModelAndView("admin/userOverview");
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("users", this.userService.getUsers());

        return modelAndView;
    }


    @GetMapping(value = "/admin/user-edit")
    public ModelAndView userEdit(@RequestParam(value = "user-id", required = true) Long userId)
    {
        ModelAndView modelAndView = new ModelAndView("admin/userEdit");

        ModelMap modelMap = modelAndView.getModelMap();
        System.out.println(this.userService.getUserById(userId).getUsername()+ "cdsafdsafasdfadfadsf\n") ;
        modelMap.addAttribute("user", this.userService.getUserById(userId));
        modelMap.addAttribute("roles", this.roleService.getRoles());

        return modelAndView;
    }
}
