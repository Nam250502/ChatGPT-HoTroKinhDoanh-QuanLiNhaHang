package nam.nguyen.store.repository;

import nam.nguyen.store.model.Invoice;
import nam.nguyen.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    public  List<Product> findFoodByName(@Param("name") String name);
    @Query("SELECT p FROM Product p WHERE p.statussell = ?1")
    public List<Product> getAllProductForCustomer(Integer status);


}
