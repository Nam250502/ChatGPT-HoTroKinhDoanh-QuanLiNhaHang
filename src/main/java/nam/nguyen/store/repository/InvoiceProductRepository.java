package nam.nguyen.store.repository;

import nam.nguyen.store.model.InvoiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceProductRepository extends JpaRepository<InvoiceProduct,Integer> {
    @Query("SELECT c FROM InvoiceProduct c WHERE c.invoice.id = :idinvoice")
    List<InvoiceProduct> findInvoiceProductsByTableId(@Param("idinvoice") Integer idinvoice);
}
