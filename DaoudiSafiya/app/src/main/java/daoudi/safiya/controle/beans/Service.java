package daoudi.safiya.controle.beans;

public class Service {

    private Long id;

    private String nom;


    public Service(Long id, String nom) {
        this.id = id;
        this.nom = nom;

    }

    public Service() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnom() {
        return nom;
    }

    public void setnom(String nom) {
        this.nom = nom;
    }

}
