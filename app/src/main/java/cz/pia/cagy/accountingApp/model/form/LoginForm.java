package cz.pia.cagy.accountingApp.model.form;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Model which represent login form
 */
@Data
public class LoginForm
{
    @NotNull
    private String username;

    @NotNull
    private String password;
}
