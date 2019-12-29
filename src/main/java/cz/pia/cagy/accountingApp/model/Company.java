package cz.pia.cagy.accountingApp.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Company extends BaseEntity
{
    @Column(name = "name")
    private String name;

    @Column(name = "ic")
    private int ic;

    @Column(name = "dic")
    private String dic;

    @Column(name = "phone")
    private int phone;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "account_number")
    private String accountNumber;


}
