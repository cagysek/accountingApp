package cz.pia.cagy.accountingApp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Address extends BaseEntity
{
    @Column(name = "street")
    @Size(min = 1, max = 40, message = "Ulice musí být v rozmezí {min} - {max} znaků.")
    private String street;

    @Column(name = "city")
    @Size(min = 1, max = 30, message = "Město musí být v rozmezí {min} - {max} znaků.")
    private String city;

    @Column(name = "zip")
    @Size(min = 5, max = 5, message = "Délka PSČ musi být {max} znaků.")
    private Integer zip;
}
