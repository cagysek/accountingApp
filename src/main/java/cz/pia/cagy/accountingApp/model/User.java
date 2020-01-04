package cz.pia.cagy.accountingApp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseEntity
{
    @Column(name = "firstname")
    @NotEmpty(message = "Jméno musí být vyplněno.")
    @Size(min = 1, max = 30, message = "Jméno musí být v rozmezí {min} - {max} znaků.")
    private String firstName;

    @Column(name = "lastname")
    @NotEmpty(message = "Příjmení musí být vyplněno.")
    @Size(min = 1, max = 30, message = "Příjmení musí být v rozmezí {min} - {max} znaků.")
    private String lastName;

    @Column(name = "personal_identification_number")
    @NotEmpty(message = "Rodné číslo musí být vyplněno.")
    @Size(min = 11, max = 11, message = "Rodné číslo musí mít {max} znaků.")
    private String personalIdentificationNumber;

    @Column(name = "email")
    @NotEmpty(message = "Email musí být vyplněn.")
    @Email(message = "Emailová adresa musí být ve správném formátu.")
    @Size(min = 1, max = 50, message = "Email musí být v rozmezí {min} - {max} znaků.")
    private String email;

    @Column(name = "phone")
    @Size(min = 1, max = 13, message = "Délka čísla musí být v rozmezí {min} - {max} znaků.")
    private String phone;

    @Column(name = "account_number")
    @NotEmpty(message = "Číslo účtu musí být vyplněno.")
    @Size(min = 11, max = 11, message = "Dělka čísla účtu musí být {max} znaků.")
    private String accountNumber;

    @Column(name = "card_number")
    @NotEmpty(message = "Číslo karty musí být vyplněno.")
    @Size(min = 16, max = 16, message = "Délka čísla karty musí být {max} znaků.")
    private String cardNumber;

    @Column(name = "username")
    @NotEmpty(message = "Přezdívka musí být vyplněna.")
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "role_id", nullable = false, insertable = false, updatable = false)
    private Role role;
}
