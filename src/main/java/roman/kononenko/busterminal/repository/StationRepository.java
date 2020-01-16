package roman.kononenko.busterminal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roman.kononenko.busterminal.entity.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

}
