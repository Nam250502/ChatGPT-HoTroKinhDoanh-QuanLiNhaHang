package nam.nguyen.store.repository;

import nam.nguyen.store.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    @Query("SELECT c FROM CartItem c WHERE c.diningTable.id = :idtable")
    List<CartItem> findCartsByTableId(@Param("idtable") Integer idtable);

}
