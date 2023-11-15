package daoudi.safiya.controle.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    @Lob
    private String photo;

    @ManyToOne
    @JoinColumn(name = "service_id")  // Assuming there's a column named 'service_id' in the Employe table
    private Service service;

    @ManyToOne
    @JsonBackReference
    private Employe chef;

    @OneToMany(mappedBy = "chef")
    @JsonManagedReference
    private List<Employe> subordinates;

    public Employe() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Employe getChef() {
        return chef;
    }

    public void setChef(Employe chef) {
        this.chef = chef;
    }

    public List<Employe> getSubordinates() {
        return subordinates;
    }

    public void setSubordinates(List<Employe> subordinates) {
        this.subordinates = subordinates;
    }
}
