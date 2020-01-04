package cz.pia.cagy.accountingApp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import cz.pia.cagy.accountingApp.model.enums.EInvoiceType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Column(name = "date_publish")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date datePublish;

    @Column(name = "date_payment")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date datePayment;

    @Column(name = "date_taxable_supply")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date dateTaxableSupply;

    @Column(name = "is_storno")
    private boolean isStorno;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EInvoiceType type;

    @Transient
    private long totalPrice;

    @Transient
    private long totalPriceDph;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItem> invoiceItems = new ArrayList<>();
}
