package roman.kononenko.busterminal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roman.kononenko.busterminal.entity.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {

}
