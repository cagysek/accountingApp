package cz.pia.cagy.accountingApp.controller;

import cz.pia.cagy.accountingApp.form.ChangePasswordForm;
import cz.pia.cagy.accountingApp.form.LoginForm;
import cz.pia.cagy.accountingApp.model.User;
import cz.pia.cagy.accountingApp.model.enums.EFlashMessageType;
import cz.pia.cagy.accountingApp.security.LoggedUser;
import cz.pia.cagy.accountingApp.service.RoleService;
import cz.pia.cagy.accountingApp.service.UserService;
import cz.pia.cagy.accountingApp.service.UserServiceImpl;
import cz.pia.cagy.accountingApp.validator.ChangePasswordValidator;
import cz.pia.cagy.accountingApp.validator.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController extends BaseController
{
    private UserService userService;
    private RoleService roleService;
    private ChangePasswordValidator changePasswordValidator;
    private UserRegistrationValidator userRegistrationValidator;

    @InitBinder("changePasswordForm")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(this.changePasswordValidator);
    }


    @Autowired
    public UserController(UserService userService, RoleService roleService, ChangePasswordValidator changePasswordValidator, UserRegistrationValidator userRegistrationValidator)
    {
        this.userService = userService;
        this.roleService = roleService;
        this.changePasswordValidator = changePasswordValidator;
        this.userRegistrationValidator = userRegistrationValidator;
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
    public ModelAndView userEditSave(@Valid User userEdit, BindingResult bindingResult, RedirectAttributes atts)
    {
        if (bindingResult.hasErrors())
        {
            return new ModelAndView("user/edit");
        }

        this.userService.saveUser(userEdit);

        atts.addFlashAttribute(EFlashMessageType.SUCCESS.toString(), "Změny uloženy.");

        return new ModelAndView("redirect:/user-detail");
    }

    @GetMapping(value = "/user-change-password")
    public ModelAndView userChangePassword()
    {
        ModelAndView modelAndView = new ModelAndView("user/changePassword");
        modelAndView.getModelMap().addAttribute("changePasswordForm", new ChangePasswordForm());

        return modelAndView;
    }

    @PostMapping(value = "/user-change-password")
    public ModelAndView userChangePasswordSave(@Valid ChangePasswordForm changePasswordForm, BindingResult bindingResult, Authentication authentication, RedirectAttributes atts)
    {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("user/changePassword");
        }

        LoggedUser loggedUser = (LoggedUser) authentication.getPrincipal();

        this.userService.changeUserPassword(loggedUser.getUserId(), changePasswordForm.getNewPassword());
        atts.addFlashAttribute(EFlashMessageType.SUCCESS.toString(), "Heslo změněno.");

        return new ModelAndView("redirect:/user-detail");
    }


}
