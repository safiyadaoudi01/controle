package daoudi.safiya.controle.services;

import daoudi.safiya.controle.dao.IDao;
import daoudi.safiya.controle.entities.Employe;
import daoudi.safiya.controle.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeService implements IDao<Employe> {

    @Autowired
    EmployeRepository employeRepository;
    @Override
    public Employe create(Employe o) {
        return employeRepository.save(o);
    }

    @Override
    public List<Employe> findAll() {return employeRepository.findAll();
    }

    @Override
    public Employe update(Employe o) {return employeRepository.save(o);
    }

    @Override
    public boolean delete(Employe o) {
        try {
            employeRepository.delete(o);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Employe findById(Long id) {return employeRepository.findById(id).orElse(null);
    }

    public List<Employe> findByService(daoudi.safiya.controle.entities.Service service) {
        return employeRepository.findByService(service);
    }


}
