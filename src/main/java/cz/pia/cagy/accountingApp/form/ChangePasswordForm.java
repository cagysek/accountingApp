package cz.pia.cagy.accountingApp.form;

import lombok.Data;

@Data
public class ChangePasswordForm
{
    private String oldPassword;

    private String newPassword;

    private String newPasswordConfirm;
}
