package fr.unicaen.info.users.a21606807.ventesimmobilires;

import java.util.ArrayList;
import java.util.Date;

public class Propriete {

    private String id;
    private String titre;
    private String description;
    private int nombre_piece;
    private ArrayList<String> caracteristiques;
    private int prix;
    private String ville;
    private Vendeur vendeur;
    private ArrayList<String> images;
    private Date date;

    public Propriete(String id, String titre, String description, int nombre_piece, ArrayList<String> caracteristiques, int prix,
                     String ville, Vendeur vendeur, ArrayList<String> images, Date date) {

        this.id = id;
        this.titre = titre;
        this.description = description;
        this.nombre_piece = nombre_piece;
        this.caracteristiques = caracteristiques;
        this.prix = prix;
        this.ville = ville;
        this.vendeur = vendeur;
        this.images = images;
        this.date = date;

    }

    public void addImages(String image) {

        this.images.add(image);
    }

    public String getId() {

        return id;
    }

    public String getTitre() {

        return titre;
    }

    public String getDescription() {

        return description;
    }

    public int getNombre_piece() {

        return nombre_piece;
    }

    public ArrayList<String> getCaracteristiques() {

        return caracteristiques;
    }

    public int getPrix() {

        return prix;
    }

    public String getVille() {

        return ville;
    }

    public Vendeur getVendeur() {

        return vendeur;
    }

    public ArrayList<String> getImages() {

        return images;
    }

    public Date getDate() {

        return date;
    }

}
