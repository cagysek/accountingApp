package cz.pia.cagy.accountingApp.validator;

import cz.pia.cagy.accountingApp.model.form.ChangePasswordForm;
import cz.pia.cagy.accountingApp.model.User;
import cz.pia.cagy.accountingApp.model.security.LoggedUser;
import cz.pia.cagy.accountingApp.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * The type Change password validator.
 */
@Component
public class ChangePasswordValidator implements Validator
{

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;

    /**
     * Instantiates a new Change password validator.
     *
     * @param bCryptPasswordEncoder the b crypt password encoder
     * @param userService           the user service
     */
    public ChangePasswordValidator(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService)
    {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return ChangePasswordForm.class.equals(aClass);
    }

    /**
     * Validation method
     *
     * @param o Object
     * @param errors Errors
     */
    @Override
    public void validate(Object o, Errors errors)
    {
        // check filled inputs
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword",
                "required.password", "");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword",
                "required.confirmPassword", "");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPasswordConfirm",
                "required.confirmPassword2", "");


        ChangePasswordForm form = (ChangePasswordForm) o;

        LoggedUser loggedUser = (LoggedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // load user from db
        User user = this.userService.getUserById(loggedUser.getUserId());

        // check user old password
        if (!bCryptPasswordEncoder.matches(form.getOldPassword(), user.getPassword()))
        {
            errors.reject("password.not.equal");
        }

        // check new password with confirm
        if(!(form.getNewPassword().equals(form.getNewPasswordConfirm()))){
            errors.reject("password.new.not.equal");
        }
    }
}
