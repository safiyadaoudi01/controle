package daoudi.safiya.controle.services;

import daoudi.safiya.controle.dao.IDao;
import daoudi.safiya.controle.entities.Service;
import daoudi.safiya.controle.repository.EmployeRepository;
import daoudi.safiya.controle.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService implements IDao<Service> {
    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public Service create(Service o) {
        return serviceRepository.save(o);
    }

    @Override
    public List<Service> findAll() {return serviceRepository.findAll();
    }

    @Override
    public Service update(Service o) {return serviceRepository.save(o);
    }

    @Override
    public boolean delete(Service o) {
        try {
            serviceRepository.delete(o);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Service findById(Long id) {return serviceRepository.findById(id).orElse(null);
    }
}
