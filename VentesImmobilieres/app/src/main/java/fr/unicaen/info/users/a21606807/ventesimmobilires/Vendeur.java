package fr.unicaen.info.users.a21606807.ventesimmobilires;

public class Vendeur {

    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    public Vendeur(String id, String nom, String prenom, String email, String telephone){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.telephone=telephone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
