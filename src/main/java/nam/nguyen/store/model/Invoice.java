package nam.nguyen.store.model;

import jakarta.persistence.*;
import lombok.*;


import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter
@Setter
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "invoice" ,  cascade = CascadeType.ALL ,fetch = FetchType.LAZY)
    private List<InvoiceProduct>  invoiceProducts;
    private String issueDate;
    private Integer idTable;
    private Integer statusPay;
    private Integer statusService;
    public long sumPriceInvoice(){
        long sum = 0;
        for ( InvoiceProduct i:invoiceProducts) {
            sum+=i.sumPrice();
        }
        return sum;
    }
    public Invoice() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        this.issueDate = formattedDateTime;
        this.setStatusPay(0);
        this.setStatusService(0);
    }
}
