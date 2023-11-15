package daoudi.safiya.controle.controller;

import daoudi.safiya.controle.entities.Employe;
import daoudi.safiya.controle.entities.Service;
import daoudi.safiya.controle.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employes")
public class EmployeController {

    @Autowired
    private EmployeService employeservice;

    @GetMapping
    public List<Employe> findAllEmployes() {
        return employeservice.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Employe employe = employeservice.findById(id);
        if (employe == null) {
            return new ResponseEntity<>("Employe avec Id " + id + " n'existe pas", HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(employe);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmploye(@PathVariable Long id) {
        Employe employe = employeservice.findById(id);
        if (employe == null) {
            return new ResponseEntity<>("Employe avec Id " + id + " n'existe pas", HttpStatus.BAD_REQUEST);
        } else {
            employeservice.delete(employe);
            return ResponseEntity.ok("employe avec id " + id + " supprimé");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmploye(@PathVariable Long id, @RequestBody Employe newEmploye) {
        Employe oldEmploye = employeservice.findById(id);
        if (oldEmploye == null) {
            return new ResponseEntity<>("employe avec id " + id + " n'existe pas ", HttpStatus.BAD_REQUEST);
        } else {
            // Set the id to ensure the update is for the correct entity
            newEmploye.setId(id);

            // Update relationships
            if (newEmploye.getChef() != null) {
                // Set the subordinate relationships if a chef is specified
                newEmploye.getChef().setSubordinates(List.of(newEmploye));
            }

            return ResponseEntity.ok(employeservice.update(newEmploye));
        }
    }

    @PostMapping
    public Employe createEmploye(@RequestBody Employe employe) {
        employe.setId(0L);
        return employeservice.create(employe);
    }

    @GetMapping("/Service")
    public List<Employe> findEmployeByService(@RequestBody Service service) {
        return employeservice.findByService(service);
    }
}
