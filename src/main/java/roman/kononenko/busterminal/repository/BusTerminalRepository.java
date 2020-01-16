package roman.kononenko.busterminal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roman.kononenko.busterminal.entity.BusTerminal;

@Repository
public interface BusTerminalRepository extends JpaRepository<BusTerminal, Long> {

}

