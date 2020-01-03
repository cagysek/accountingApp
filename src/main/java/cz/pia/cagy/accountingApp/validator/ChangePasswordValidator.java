package cz.pia.cagy.accountingApp.validator;

import cz.pia.cagy.accountingApp.form.ChangePasswordForm;
import cz.pia.cagy.accountingApp.model.User;
import cz.pia.cagy.accountingApp.security.LoggedUser;
import cz.pia.cagy.accountingApp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ChangePasswordValidator implements Validator
{

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService userService;

    public ChangePasswordValidator(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService)
    {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return true;
    }

    @Override
    public void validate(Object o, Errors errors)
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "oldPassword",
                "required.password", "Staré heslo musí být vyplněno.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword",
                "required.confirmPassword", "Nové heslo musí být vyplněno.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPasswordConfirm",
                "required.confirmPassword", "Nové heslo znovu musí být vyplněno.");


        ChangePasswordForm form = (ChangePasswordForm) o;

        LoggedUser loggedUser = (LoggedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = this.userService.getUserById(loggedUser.getUserId());

        if (!bCryptPasswordEncoder.matches(form.getOldPassword(), user.getPassword()))
        {
            errors.reject("Staré heslo se neshoduje");
        }


        if(!(form.getNewPassword().equals(form.getNewPasswordConfirm()))){
            errors.reject("Nové heslo se neshoduje s potvrzením");
        }
    }
}
