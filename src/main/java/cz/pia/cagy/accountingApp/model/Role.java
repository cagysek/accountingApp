package cz.pia.cagy.accountingApp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Entity for role
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Role extends BaseEntity
{
    @Column(name = "name")
    private String name;
}
