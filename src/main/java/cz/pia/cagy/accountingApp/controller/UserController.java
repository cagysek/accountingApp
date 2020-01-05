package cz.pia.cagy.accountingApp.controller;

import cz.pia.cagy.accountingApp.model.form.ChangePasswordForm;
import cz.pia.cagy.accountingApp.model.User;
import cz.pia.cagy.accountingApp.model.enums.EFlashMessageType;
import cz.pia.cagy.accountingApp.model.security.LoggedUser;
import cz.pia.cagy.accountingApp.service.RoleService;
import cz.pia.cagy.accountingApp.service.UserService;
import cz.pia.cagy.accountingApp.validator.ChangePasswordValidator;
import cz.pia.cagy.accountingApp.validator.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Controller for logged user
 * user setting, password change, ...
 */
@Controller
public class UserController extends BaseController
{
    private UserService userService;
    private RoleService roleService;
    private ChangePasswordValidator changePasswordValidator;
    private UserRegistrationValidator userRegistrationValidator;

    /**
     * Init binder.
     * Add changePasswordForm validation
     *
     * @param binder the binder
     */
    @InitBinder("changePasswordForm")
    protected void initBinder(WebDataBinder binder)
    {
        binder.addValidators(this.changePasswordValidator);
    }


    /**
     * Instantiates a new User controller.
     *
     * @param userService               the user service
     * @param roleService               the role service
     * @param changePasswordValidator   the change password validator
     * @param userRegistrationValidator the user registration validator
     */
    @Autowired
    public UserController(UserService userService, RoleService roleService, ChangePasswordValidator changePasswordValidator, UserRegistrationValidator userRegistrationValidator)
    {
        this.userService = userService;
        this.roleService = roleService;
        this.changePasswordValidator = changePasswordValidator;
        this.userRegistrationValidator = userRegistrationValidator;
    }

    /**
     * Shows user detail
     *
     * @param authentication the authentication
     * @return the model and view
     */
    @GetMapping(value = "/user-detail")
    public ModelAndView userDetail(Authentication authentication)
    {
        ModelAndView modelAndView = new ModelAndView("user/detail");

        LoggedUser loggedUser = (LoggedUser) authentication.getPrincipal();

        modelAndView.getModelMap().addAttribute("user", this.userService.getUserById(loggedUser.getUserId()));


        return modelAndView;
    }

    /**
     * Shows user edit form
     *
     * @param authentication the authentication
     * @return the model and view
     */
    @GetMapping(value = "/user-edit")
    public ModelAndView userEdit(Authentication authentication)
    {
        ModelAndView modelAndView = new ModelAndView("user/edit");

        LoggedUser loggedUser = (LoggedUser) authentication.getPrincipal();

        ModelMap modelMap = modelAndView.getModelMap();

        modelMap.addAttribute("user", this.userService.getUserById(loggedUser.getUserId()));
        this.setUpDataForForm(modelMap);

        return modelAndView;
    }

    /**
     * Handles user edit form
     *
     * @param userEdit      the user edit
     * @param bindingResult the binding result
     * @param atts          the atts
     * @return the model and view
     */
    @PostMapping(value = "/user-edit")
    public ModelAndView userEditSave(@Valid User userEdit, BindingResult bindingResult, RedirectAttributes atts, ModelMap modelMap)
    {
        if (bindingResult.hasErrors())
        {
            this.setUpDataForForm(modelMap);
            return new ModelAndView("user/edit");
        }

        this.userService.saveUser(userEdit);

        atts.addFlashAttribute(EFlashMessageType.SUCCESS.toString(), "Změny uloženy.");

        return new ModelAndView("redirect:/user-detail");
    }

    /**
     * Shows user password change form
     *
     * @return the model and view
     */
    @GetMapping(value = "/user-change-password")
    public ModelAndView userChangePassword()
    {
        ModelAndView modelAndView = new ModelAndView("user/changePassword");
        modelAndView.getModelMap().addAttribute("changePasswordForm", new ChangePasswordForm());

        return modelAndView;
    }

    /**
     * Handles change password form
     *
     * @param changePasswordForm the change password form
     * @param bindingResult      the binding result
     * @param authentication     the authentication
     * @param atts               the atts
     * @return the model and view
     */
    @PostMapping(value = "/user-change-password")
    public ModelAndView userChangePasswordSave(@Valid ChangePasswordForm changePasswordForm, BindingResult bindingResult, Authentication authentication, RedirectAttributes atts)
    {
        if (bindingResult.hasErrors())
        {
            return new ModelAndView("user/changePassword");
        }

        LoggedUser loggedUser = (LoggedUser) authentication.getPrincipal();

        this.userService.changeUserPassword(loggedUser.getUserId(), changePasswordForm.getNewPassword());

        atts.addFlashAttribute(EFlashMessageType.SUCCESS.toString(), "Heslo změněno.");

        return new ModelAndView("redirect:/user-detail");
    }

    private void setUpDataForForm(ModelMap modelMap)
    {
        modelMap.addAttribute("roles", this.roleService.getRoles());
        modelMap.addAttribute("formUrl", "/user-edit");
        modelMap.addAttribute("formSubmit", "Uložit");
    }
}
