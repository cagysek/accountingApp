package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.Invoice;
import cz.pia.cagy.accountingApp.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService
{
    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository)
    {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public List<Invoice> getInvoices()
    {
        return this.invoiceRepository.findAll();
    }

    @Override
    public void saveInvoice(Invoice invoice)
    {
        this.invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoiceById(long id)
    {
        return this.invoiceRepository.findById(id);
    }

    @Override
    public void deleteInvoiceById(long id)
    {
        this.invoiceRepository.deleteById(id);
    }
}
