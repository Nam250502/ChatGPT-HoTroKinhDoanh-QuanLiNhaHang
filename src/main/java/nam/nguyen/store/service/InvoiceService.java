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
    public List<Invoice> getInvoiceToday() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDateTime = currentDateTime.format(formatter);
        return invoiceRepository.findInvoicesByCurrentDateAndStatusService(formattedDateTime);
    }


    public Integer saveInvoice(Invoice invoice) {
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return savedInvoice.getId();
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

}
