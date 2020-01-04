package cz.pia.cagy.accountingApp.controller;

import cz.pia.cagy.accountingApp.model.User;
import cz.pia.cagy.accountingApp.model.enums.EFlashMessageType;
import cz.pia.cagy.accountingApp.service.RoleService;
import cz.pia.cagy.accountingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
        ModelAndView modelAndView = new ModelAndView("admin/usersList");
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("users", this.userService.getUsers());

        return modelAndView;
    }


    @GetMapping(value = "/admin/user-edit")
    public ModelAndView userEdit(@RequestParam(value = "user-id", required = true) Long userId)
    {
        ModelAndView modelAndView = new ModelAndView("admin/userEdit");

        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("user", this.userService.getUserById(userId));
        modelMap.addAttribute("roles", this.roleService.getRoles());
        modelMap.addAttribute("formUrl", "/user-edit");
        modelMap.addAttribute("formSubmit", "Uložit");

        return modelAndView;
    }

    @GetMapping(value = "/admin/user-add")
    public ModelAndView userAdd()
    {
        ModelAndView modelAndView = new ModelAndView("admin/userAdd");

        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("roles", this.roleService.getRoles());
        modelMap.addAttribute("formUrl", "/admin/user-add");
        modelMap.addAttribute("formSubmit", "Vytvořit");
        modelMap.addAttribute("showPassword", true);

        return modelAndView;
    }

    @PostMapping(value = "/admin/user-add")
    public String saveUserEdit(@Valid User user, BindingResult bindingResult, RedirectAttributes atts)
    {
        if (bindingResult.hasErrors()) {
            return "admin/userAdd";
        }

        this.userService.saveUser(user);

        atts.addFlashAttribute(EFlashMessageType.SUCCESS.toString(), "Uživatel byl úspěšně vytvořen.");

        return "redirect:/admin/user-edit?user-id=" + user.getId();
    }

    @GetMapping(value = "/admin/user-delete")
    public String userDelete(@RequestParam(value = "user-id", required = true) Long userId, RedirectAttributes atts)
    {
        this.userService.deleteUserById(userId);

        atts.addFlashAttribute(EFlashMessageType.SUCCESS.toString(), "Uživatel úspěšně odstraněn");

        return "redirect:/admin/users";
    }
}
