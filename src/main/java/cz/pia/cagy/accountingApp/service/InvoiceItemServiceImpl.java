package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.InvoiceItem;
import cz.pia.cagy.accountingApp.repository.InvoiceItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceItemServiceImpl implements InvoiceItemService
{

    private InvoiceItemRepository invoiceItemRepository;

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
