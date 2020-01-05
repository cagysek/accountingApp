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

/**
 * Controller for admin users, allows add, edit, remove users
 */
@Controller
public class AdminController extends BaseController
{
    private UserService userService;
    private RoleService roleService;

    private final String DEFAULT_REDIRECT = "redirect:/admin/users";


    /**
     * Instantiates a new Admin controller.
     *
     * @param userService the user service
     * @param roleService the role service
     */
    @Autowired
    public AdminController(UserService userService, RoleService roleService)
    {
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * Shows users overview
     *
     * @return the model and view
     */
    @GetMapping(value = "/admin/users")
    public ModelAndView usersOverview()
    {
        ModelAndView modelAndView = new ModelAndView("admin/usersList");
        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("users", this.userService.getUsers());

        return modelAndView;
    }


    /**
     * Shows user edit.
     *
     * @param userId the user id
     * @param atts   the atts
     * @return the model and view
     */
    @GetMapping(value = "/admin/user-edit")
    public ModelAndView userEdit(@RequestParam(value = "user-id") Long userId, RedirectAttributes atts)
    {
        User user = this.userService.getUserById(userId);

        // check if user exists
        if (user == null)
        {
            atts.addFlashAttribute(EFlashMessageType.ERROR.toString(), "Uživatel nebyl nalezen.");
            return new ModelAndView(this.DEFAULT_REDIRECT);
        }

        ModelAndView modelAndView = new ModelAndView("admin/userEdit");

        ModelMap modelMap = modelAndView.getModelMap();
        user.setPassword(null);
        modelMap.addAttribute("user", user);

        this.serUpDataForUserFormEdit(modelMap);

        return modelAndView;
    }

    /**
     * Handler for user save after edit
     *
     * @param user          the user
     * @param bindingResult the binding result
     * @param atts          the atts
     * @return the string
     */
    @PostMapping(value = "/admin/user-edit")
    public String saveUserEdit(@Valid User user, BindingResult bindingResult, RedirectAttributes atts, ModelMap modelMap)
    {
        if (bindingResult.hasErrors())
        {
            this.serUpDataForUserFormEdit(modelMap);

            return "admin/userAdd";
        }

        this.userService.saveUser(user);

        atts.addFlashAttribute(EFlashMessageType.SUCCESS.toString(), "Uživatel byl uložen.");

        return this.DEFAULT_REDIRECT;
    }

    /**
     * Shows form for add user
     *
     * @return the model and view
     */
    @GetMapping(value = "/admin/user-add")
    public ModelAndView userAdd()
    {
        ModelAndView modelAndView = new ModelAndView("admin/userAdd");

        ModelMap modelMap = modelAndView.getModelMap();
        modelMap.addAttribute("user", new User());

        this.setUpDataForUserFormAdd(modelMap);

        return modelAndView;
    }

    /**
     * Handler for user save after add
     *
     * @param user          the user
     * @param bindingResult the binding result
     * @param atts          the atts
     * @return the string
     */
    @PostMapping(value = "/admin/user-add")
    public String saveUserAdd(@Valid User user, BindingResult bindingResult, RedirectAttributes atts, ModelMap modelMap)
    {
        if (bindingResult.hasErrors())
        {
            this.setUpDataForUserFormAdd(modelMap);

            return "admin/userAdd";
        }

        this.userService.saveUser(user);

        atts.addFlashAttribute(EFlashMessageType.SUCCESS.toString(), "Uživatel byl úspěšně vytvořen.");

        return this.DEFAULT_REDIRECT;
    }

    /**
     * Handler for user delete.
     *
     * @param userId the user id
     * @param atts   the atts
     * @return the string
     */
    @GetMapping(value = "/admin/user-delete")
    public String userDelete(@RequestParam(value = "user-id") Long userId, RedirectAttributes atts)
    {
        this.userService.deleteUserById(userId);

        atts.addFlashAttribute(EFlashMessageType.SUCCESS.toString(), "Uživatel úspěšně odstraněn");

        return this.DEFAULT_REDIRECT;
    }

    private void setUpDataForUserFormAdd(ModelMap modelMap)
    {
        modelMap.addAttribute("roles", this.roleService.getRoles());
        modelMap.addAttribute("formUrl", "/admin/user-add");
        modelMap.addAttribute("formSubmit", "Vytvořit");
        modelMap.addAttribute("showPassword", true);
    }

    private void serUpDataForUserFormEdit(ModelMap modelMap)
    {
        modelMap.addAttribute("roles", this.roleService.getRoles());
        modelMap.addAttribute("formUrl", "/admin/user-edit");
        modelMap.addAttribute("formSubmit", "Uložit");
        modelMap.addAttribute("showPassword", true);
    }
}
