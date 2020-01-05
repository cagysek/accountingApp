package cz.pia.cagy.accountingApp.validator;

import cz.pia.cagy.accountingApp.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import java.util.regex.Pattern;

/**
 * The type User validator.
 */
@Component
public class UserValidator implements Validator
{
    @Override
    public boolean supports(Class<?> aClass)
    {
        return User.class.equals(aClass);
    }


    /**
     * Validate user object
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors)
    {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "user.firstName.empty", "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "user.lastName.empty","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personalIdentificationNumber", "user.personalIdentificationNumber.empty","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountNumber", "user.accountNumber.empty","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardNumber", "user.cardNumber.empty","");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "user.username.empty","");


        User user = (User) o;

        Pattern emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        if (!user.getEmail().isEmpty() && !emailPattern.matcher(user.getEmail()).matches())
        {
            errors.reject("user.email.format");
        }

        Pattern bankAccountPattern = Pattern.compile("^\\s*(\\d\\d\\d)([\\w ]{1})?(\\d\\d\\d)([\\w ]{1})?(\\d\\d\\d)([\\w ]{1})?[ /]*([\\w ]{1})?(\\d\\d\\d\\d)\\s*$");
        if (!user.getAccountNumber().isEmpty() && !bankAccountPattern.matcher(user.getAccountNumber()).matches())
        {
            errors.reject("user.accountNumber.format");
        }

        Pattern telPattern = Pattern.compile("^(\\+420|\\+421) ?[0-9]{3} ?[0-9]{3} ?[0-9]{3}$");
        if (!user.getPhone().isEmpty() && !telPattern.matcher(user.getPhone()).matches())
        {
            errors.reject("user.phone.format");
        }

        Pattern personalIdentificationNumberPattern = Pattern.compile("^\\s*(\\d\\d)(\\d\\d)(\\d\\d)[ /]*(\\d\\d\\d)(\\d?)\\s*$");
        if (!user.getPersonalIdentificationNumber().isEmpty()
                && !personalIdentificationNumberPattern.matcher(user.getPersonalIdentificationNumber()).matches())
        {
            errors.reject("user.personalIdentificationNumber.format");
        }

        Pattern cardNumberPattern = Pattern.compile("^\\s*(\\d\\d\\d\\d)([\\w ]{1})?(\\d\\d\\d\\d)([\\w ]{1})?(\\d\\d\\d\\d)([\\w ]{1})?(\\d\\d\\d\\d)\\s*$");
        if (!user.getCardNumber().isEmpty() && !cardNumberPattern.matcher(user.getCardNumber()).matches())
        {
            errors.reject("user.cardNumber.format");
        }

        Pattern zipPattern = Pattern.compile("^\\s*(\\d\\d\\d)([\\w ]{1})?(\\d\\d)\\s*$");
        if (user.getAddress() != null && user.getAddress().getZip() != null && !zipPattern.matcher(user.getAddress().getZip().toString()).matches())
        {
            errors.reject("address.zip.format");
        }
    }
}
