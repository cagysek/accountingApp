package cz.pia.cagy.accountingApp.service;

import cz.pia.cagy.accountingApp.model.Invoice;
import cz.pia.cagy.accountingApp.model.InvoiceItem;
import cz.pia.cagy.accountingApp.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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

    @Transactional
    @Override
    public long saveInvoice(Invoice invoice)
    {

        for (InvoiceItem item : invoice.getInvoiceItems())
        {
            item.setInvoice(invoice);
        }

        Invoice dbInvoice = this.invoiceRepository.save(invoice);
        return  dbInvoice.getId();
    }

    @Override
    public Invoice getInvoiceById(long id)
    {
        Invoice invoice = this.invoiceRepository.findById(id);
        long finalPrice = 0;
        long finalPriceDph = 0;

        for (InvoiceItem item : invoice.getInvoiceItems())
        {
            finalPrice += item.getPrice() * item.getQuantity();
            finalPriceDph += item.getPriceDph() * item.getQuantity();
        }

        invoice.setTotalPrice(finalPrice);
        invoice.setTotalPriceDph(finalPriceDph);

        return invoice;
    }

    @Override
    public void deleteInvoiceById(long id)
    {
        this.invoiceRepository.deleteById(id);
    }
}
