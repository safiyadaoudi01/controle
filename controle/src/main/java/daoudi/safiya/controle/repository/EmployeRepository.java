package daoudi.safiya.controle.repository;

import daoudi.safiya.controle.entities.Employe;
import daoudi.safiya.controle.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
    public List<Employe> findByService(Service service);
}

