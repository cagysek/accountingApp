package cz.pia.cagy.accountingApp.form;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginForm
{
    @NotNull
    @Size(max = 100, message = "Přihlašovací jméno musí být v rozmezí {min} - {max} znaků.")
    private String username;

    @NotNull
    private String password;
}
