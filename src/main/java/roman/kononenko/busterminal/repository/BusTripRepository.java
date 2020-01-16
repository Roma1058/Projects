package roman.kononenko.busterminal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roman.kononenko.busterminal.entity.BusTrip;

@Repository
public interface BusTripRepository extends JpaRepository<BusTrip, Long> {

}
