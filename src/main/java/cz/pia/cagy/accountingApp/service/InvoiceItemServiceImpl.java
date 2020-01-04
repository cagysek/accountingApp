package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.InvoiceItem;
import cz.pia.cagy.accountingApp.repository.InvoiceItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Invoice item service.
 */
@Service
public class InvoiceItemServiceImpl implements InvoiceItemService
{

    private InvoiceItemRepository invoiceItemRepository;

    /**
     * Instantiates a new Invoice item service.
     *
     * @param invoiceItemRepository the invoice item repository
     */
    public InvoiceItemServiceImpl(InvoiceItemRepository invoiceItemRepository)
    {

        this.invoiceItemRepository = invoiceItemRepository;
    }

    @Override
    public List<InvoiceItem> getInvoiceItems(long id)
    {
        return this.invoiceItemRepository.findAllByInvoiceId(id);
    }
}
