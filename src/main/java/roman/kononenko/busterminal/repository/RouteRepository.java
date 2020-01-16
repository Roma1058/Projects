package roman.kononenko.busterminal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roman.kononenko.busterminal.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

}
