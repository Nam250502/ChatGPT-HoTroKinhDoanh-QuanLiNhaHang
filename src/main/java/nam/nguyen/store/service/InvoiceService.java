package nam.nguyen.store.service;

import nam.nguyen.store.model.*;
import nam.nguyen.store.repository.InvoiceProductRepository;
import nam.nguyen.store.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;


    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }
    public List<Invoice> getInvoiceByStatus(Integer status) {
        return invoiceRepository.findInvoicesByStatusService(status);
    }
    public List<Invoice> getAllInvoicePaid() {
        return invoiceRepository.findInvoicesByInvoicePaid();
    }
    public List<Invoice> getInvoicesForToday() {
        LocalDateTime startDate = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999);

        return invoiceRepository.getInvoicesForToday(startDate, endDate);
    }
    public List<Invoice> getInvoicesForTodaySuccess() {
        LocalDateTime startDate = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999);

        return invoiceRepository.getInvoicesForTodaySuccess(startDate, endDate);
    }
    public List<Invoice> findUnpaidInvoices() {

        return invoiceRepository.findInvoicesByCurrentDateAndStatusService();
    }
    public List<Invoice> findInvoicesChef() {

        return invoiceRepository.getInvoiceChef();
    }
    public List<Invoice> findInvoiceByIdTable(Integer id){
        return invoiceRepository.findInvoiceByIdTable(id);
    }


    public Invoice saveInvoice(Invoice invoice) {
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return savedInvoice;
    }
    public List<Invoice> savesInvoice(List<Invoice> invoices) {
        List<Invoice> savedInvoices = invoiceRepository.saveAll(invoices);
        return savedInvoices;
    }


    public Invoice getInvoiceById(int id) {
        Optional<Invoice> optional = invoiceRepository.findById(id);
        Invoice invoice=null;
        if(optional.isPresent()){
            invoice=optional.get();

        }else {
            throw new RuntimeException("Product not found for id::"+id);
        }
        return invoice;
    }

    public long total( Integer id) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);
        List<InvoiceProduct> invoiceProducts = invoiceOptional.get().getInvoiceProducts();
        long total = 0;
        for ( InvoiceProduct c: invoiceProducts) {
            total+=c.getProduct().getPrice()*c.getQuantity();

        }
        return total;
    }
    public List<Object[]> getDataChartByYear(Integer year){
        return invoiceRepository.getMonthlyTotalsByYear(year);
    }

}
