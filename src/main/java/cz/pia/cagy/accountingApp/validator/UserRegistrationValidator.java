package cz.pia.cagy.accountingApp.validator;

import cz.pia.cagy.accountingApp.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import javax.validation.Validation;

@Component
public class UserRegistrationValidator implements Validator
{
    @Override
    public boolean supports(Class<?> aClass)
    {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors)
    {
        System.out.println("kokotko");
       // ValidationUtils.rejectIfEmpty(errors, "firstName", "test","bbb");

    }
}
