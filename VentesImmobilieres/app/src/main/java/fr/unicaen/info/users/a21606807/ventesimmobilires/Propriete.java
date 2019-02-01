package fr.unicaen.info.users.a21606807.ventesimmobilires;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Propriete implements Parcelable {

    private String id;
    private String titre;
    private String description;
    private int nombre_piece;
    private List<String> caracteristiques;
    private int prix;
    private String ville;
    private Vendeur vendeur;
    private List<String> images;
    private Long date;

    //creator - used when un-parceling our parcle (creating the object)
    public static final Parcelable.Creator<Propriete> CREATOR = new Parcelable.Creator<Propriete>(){

        @Override
        public Propriete createFromParcel(Parcel parcel) {
            return new Propriete(parcel);
        }

        @Override
        public Propriete[] newArray(int size) {
            return new Propriete[0];
        }
    };

    public Propriete(String id, String titre, String description, int nombre_piece, ArrayList<String> caracteristiques, int prix,
                     String ville, Vendeur vendeur, ArrayList<String> images, Long date) {

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

    public Propriete(String id, String titre, String description, int nombre_piece, ArrayList<String> caracteristiques, int prix,
                     String ville, Vendeur vendeur, ArrayList<String> images, Date date){
        this(id,titre,description,nombre_piece,caracteristiques,prix,ville,vendeur,images,date.getTime());
    }

    public Propriete(Parcel parcel) {
        this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readInt(),
                parcel.readArrayList(String.class.getClassLoader()),
                parcel.readInt(),
                parcel.readString(),
                (Vendeur) parcel.readParcelable(Vendeur.class.getClassLoader()),
                parcel.readArrayList(String.class.getClassLoader()),
                parcel.readLong()
        );
    }

    //write object values to parcel for storage
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(id);
        dest.writeString(titre);
        dest.writeString(description);
        dest.writeInt(nombre_piece);
        dest.writeList(caracteristiques);
        dest.writeInt(prix);
        dest.writeString(ville);
        dest.writeParcelable(vendeur, 1);
        dest.writeList(images);
        dest.writeLong(date);
    }

    //return hashcode of object
    @Override
    public int describeContents() {
        return hashCode();
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

        return ((ArrayList)caracteristiques);
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

        return ((ArrayList)images);
    }

    public Date getDate() {

        return new Date(date);
    }

}
