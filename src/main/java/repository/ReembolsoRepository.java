package repository;

import model.Reembolso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReembolsoRepository extends JpaRepository<Reembolso, String> {
}
