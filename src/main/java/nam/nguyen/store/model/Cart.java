package nam.nguyen.store.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Cart {
    @Id
    private  Integer id;
    @OneToOne
    private DiningTable diningTable;
    @OneToMany(mappedBy = "cart" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
}
