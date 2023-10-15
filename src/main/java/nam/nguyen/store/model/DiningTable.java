package nam.nguyen.store.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "dining_tables")
public class DiningTable {
    @Id
    private Integer id;
    @OneToOne(mappedBy = "diningTable")
    private Cart cart;
    private Integer status;
}
