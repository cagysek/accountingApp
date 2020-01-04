package cz.pia.cagy.accountingApp.repository;

import cz.pia.cagy.accountingApp.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>
{
    Invoice findById(long id);
}
