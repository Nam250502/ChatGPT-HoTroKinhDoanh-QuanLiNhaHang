package nam.nguyen.store.service;

import nam.nguyen.store.model.Invoice;
import nam.nguyen.store.model.InvoiceProduct;
import nam.nguyen.store.model.Product;
import nam.nguyen.store.repository.InvoiceProductRepository;
import nam.nguyen.store.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;


    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
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



}
