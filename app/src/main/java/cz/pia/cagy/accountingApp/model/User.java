package cz.pia.cagy.accountingApp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Entity for user
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "user")
public class User extends BaseEntity
{
    @Column(name = "firstname")
    @Size(max = 20)
    private String firstName;

    @Column(name = "lastname")
    @Size(max = 30)
    private String lastName;

    @Column(name = "personal_identification_number")
    @Size(max = 20)
    private String personalIdentificationNumber;

    @Column(name = "email")
    @Size(max = 40)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "username")
    @Size(max = 20)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
