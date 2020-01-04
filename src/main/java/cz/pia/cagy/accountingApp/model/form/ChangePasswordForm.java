package cz.pia.cagy.accountingApp.model.form;

import lombok.Data;

/**
 * Model which is used for change user password
 */
@Data
public class ChangePasswordForm
{
    /**
     * Current password
     */
    private String oldPassword;

    /**
     * New password
     */
    private String newPassword;

    /**
     * New password recapitulation
     */
    private String newPasswordConfirm;
}
