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
    @Column(length = 1000)
    private String description;
    private  String url;
    private long price;
    private Integer statussell;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private  Category category;

}
