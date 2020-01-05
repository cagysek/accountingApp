package cz.pia.cagy.accountingApp.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity for company
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Company extends BaseEntity
{
    @Column(name = "name")
    private String name;

    @Column(name = "ic")
    private Integer ic;

    @Column(name = "dic")
    private String dic;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "account_number")
    private String accountNumber;
}
