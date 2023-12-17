package nam.nguyen.store.repository;

import jakarta.persistence.Tuple;
import nam.nguyen.store.model.CalendarStaff;
import nam.nguyen.store.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarStaffRepository extends JpaRepository<CalendarStaff,Integer> {
    List<CalendarStaff> findByUserId(Integer id);
}
