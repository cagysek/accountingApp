package cz.pia.cagy.accountingApp.repository;

import cz.pia.cagy.accountingApp.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface repository for invoice_items.
 */
@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long>
{
    /**
     * Find all invoice items for invoice by invoice id.
     *
     * @param invoiceId the invoice id
     * @return the list
     */
    List<InvoiceItem> findAllByInvoiceId(long invoiceId);
}
