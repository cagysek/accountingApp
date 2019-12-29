package cz.pia.cagy.accountingApp.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Invoice extends BaseEntity
{
    @OneToOne
    @JoinColumn(name = "supplier_company_id")
    private Company supplierCompany;

    @OneToOne
    @JoinColumn(name = "bill_to_company_id")
    private Company billToCompany;

    @Column(name = "datePublish")
    private Date datePublish;

    @Column(name = "datePayment")
    private Date datePayment;

    @Column(name = "is_storno")
    private boolean isStorno;

}
