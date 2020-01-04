package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.Invoice;

import java.util.List;

public interface InvoiceService
{
    List<Invoice> getInvoices();

    long saveInvoice(Invoice invoice);

    Invoice getInvoiceById(long id);

    void stornoInvoice(long id);
}
