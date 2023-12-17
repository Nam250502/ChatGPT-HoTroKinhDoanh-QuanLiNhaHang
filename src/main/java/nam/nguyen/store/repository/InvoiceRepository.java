package nam.nguyen.store.repository;

import nam.nguyen.store.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
    @Query("SELECT i FROM Invoice i WHERE i.statusPay = 0")
    List<Invoice> findInvoicesByCurrentDateAndStatusService();
    @Query("SELECT i FROM Invoice i WHERE i.statusPay = :status ")
    List<Invoice> findInvoicesByStatusService(@Param("status") Integer status);
    @Query("SELECT i FROM Invoice i WHERE i.statusPay = 1")
    List<Invoice> findInvoicesByInvoicePaid();
    @Query("SELECT i FROM Invoice i WHERE i.idTable = :idtable AND i.statusPay =0")
    List<Invoice> findInvoiceByIdTable(@Param("idtable") Integer idtable);
    @Query("SELECT i FROM Invoice i WHERE i.issueDate >= :startDate AND i.issueDate < :endDate")
    List<Invoice> getInvoicesForToday(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    @Query("SELECT i FROM Invoice i WHERE i.issueDate >= :startDate AND i.issueDate < :endDate AND i.statusPay = 1")
    List<Invoice>  getInvoicesForTodaySuccess(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT i FROM Invoice i WHERE i.statusService <> 3")
    List<Invoice> getInvoiceChef();
    @Query("SELECT MONTH(issueDate) AS month, SUM(total) AS total " +
            "FROM Invoice " +
            "WHERE YEAR(issueDate) = :year AND statusPay = 1 " +
            "GROUP BY MONTH(issueDate)")
    List<Object[]> getMonthlyTotalsByYear(@Param("year") int year);





}
