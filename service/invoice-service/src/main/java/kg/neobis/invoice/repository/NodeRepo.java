package kg.neobis.invoice.repository;


import kg.neobis.invoice.entities.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepo extends JpaRepository<Node, Long> {
}
