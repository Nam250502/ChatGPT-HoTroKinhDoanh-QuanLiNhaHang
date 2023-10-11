package nam.nguyen.store.repository;


import nam.nguyen.store.model.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiningTableRepository extends JpaRepository<DiningTable,Integer> {
}
