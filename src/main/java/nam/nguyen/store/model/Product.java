package nam.nguyen.store.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private  String url;
    private long price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private  Category category;
//    @OneToMany(mappedBy = "product" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
//    private List<CartItem> cartItems;
}
