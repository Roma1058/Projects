package roman.kononenko.busterminal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import roman.kononenko.busterminal.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
