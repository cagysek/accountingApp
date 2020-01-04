package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.Invoice;
import cz.pia.cagy.accountingApp.model.InvoiceItem;

import java.util.List;

/**
 * The interface Invoice item service.
 */
public interface InvoiceItemService
{

    /**
     * Gets invoice items.
     *
     * @param id the id
     * @return the invoice items
     */
    List<InvoiceItem> getInvoiceItems(long id);

}
