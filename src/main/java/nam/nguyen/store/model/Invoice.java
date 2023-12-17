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
    @OneToMany(mappedBy = "invoice" ,  cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private List<InvoiceProduct>  invoiceProducts;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime issueDate;
    private Integer idTable;
    private Integer statusPay;
    private Integer statusService;
    private long total;
    private String note;
    public Invoice() {
        this.issueDate = LocalDateTime.now();
        this.setStatusPay(0);
        this.setStatusService(0);
    }
}
