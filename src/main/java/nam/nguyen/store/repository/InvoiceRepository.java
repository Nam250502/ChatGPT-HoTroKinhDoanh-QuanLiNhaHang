package nam.nguyen.store.repository;

import nam.nguyen.store.model.Invoice;
import nam.nguyen.store.model.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
    @Query("SELECT i FROM Invoice i WHERE i.issueDate LIKE CONCAT(:currentDate, ' %')")
    List<Invoice> findInvoicesByCurrentDateAndStatusService(@Param("currentDate") String currentDate);

}
