package nam.nguyen.store.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private DiningTable diningTable;
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private  Integer quantity=1;
}
