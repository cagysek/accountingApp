package cz.pia.cagy.accountingApp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity for invoice item
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class InvoiceItem extends BaseEntity
{
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price_vat")
    private Integer priceVat;

    @Column(name = "price")
    private float price;

    @Column(name = "price_dph")
    private float priceDph;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Column(name = "name")
    private String name;
}
