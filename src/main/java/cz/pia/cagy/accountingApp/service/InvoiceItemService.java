package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.Invoice;
import cz.pia.cagy.accountingApp.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemService
{

    List<InvoiceItem> getInvoiceItems(long id);

}
