package nam.nguyen.store.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class InvoiceProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    private Integer quantity;

    public long sumPrice(){
        return quantity*product.getPrice();
    }
}
