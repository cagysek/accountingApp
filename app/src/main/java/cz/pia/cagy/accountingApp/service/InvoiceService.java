package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.Invoice;

import java.util.List;

/**
 * The interface Invoice service.
 */
public interface InvoiceService
{
    /**
     * Gets invoices.
     *
     * @return the invoices
     */
    List<Invoice> getInvoices();

    /**
     * Save invoice long.
     *
     * @param invoice the invoice
     * @return the long
     */
    long saveInvoice(Invoice invoice);

    /**
     * Gets invoice by id.
     *
     * @param id the id
     * @return the invoice by id
     */
    Invoice getInvoiceById(long id);

    /**
     * Storno invoice.
     *
     * @param id the id
     */
    void stornoInvoice(long id);
}
