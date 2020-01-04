package cz.pia.cagy.accountingApp.repository;

import cz.pia.cagy.accountingApp.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface repository for invoice.
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>
{
    /**
     * Find invoice by id.
     *
     * @param id the id
     * @return the invoice
     */
    Invoice findById(long id);
}
