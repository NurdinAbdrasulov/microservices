package kg.neobis.test.repository;


import kg.neobis.test.entities.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepo extends JpaRepository<Node, Long> {
}
