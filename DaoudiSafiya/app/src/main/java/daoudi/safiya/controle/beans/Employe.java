package daoudi.safiya.controle.beans;

import java.util.Date;

public class Employe {
    private Long id;
    private String nom;
    private String prenom;

    private Date dateNaissance;
    private Employe chef;

    private Service service;

    public Employe(Long id, String nom, String prenom, Date dateNaissance, Service service,Employe chef) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.service = service;
        this.chef = chef;
    }

    public Service getservice() {
        return service;
    }

    public void setservice(Service service) {
        this.service = service;
    }

    public Employe() {
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
    public Employe getChef() {
        return chef;
    }

    public void setChef(Employe chef) {
        this.chef = chef;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setdateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

}
