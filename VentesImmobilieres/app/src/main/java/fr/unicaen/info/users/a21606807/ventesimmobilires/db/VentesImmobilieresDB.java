package fr.unicaen.info.users.a21606807.ventesimmobilires.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import fr.unicaen.info.users.a21606807.ventesimmobilires.model.Propriete;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.Vendeur;

public class VentesImmobilieresDB {

    public static final String DB_NAME = VentesImmobilieresContract.DB_NAME;

    public static final String TABLE_PROP = VentesImmobilieresContract.ProprieteEntry.TABLE_NAME;
    public static final String COL_ID = VentesImmobilieresContract.ProprieteEntry.COL_ID;
    public static final String COL_TITRE = VentesImmobilieresContract.ProprieteEntry.COL_TITRE;
    public static final String COL_DESCRIPTION = VentesImmobilieresContract.ProprieteEntry.COL_DESCRIPTION;
    public static final String COL_PIECES = VentesImmobilieresContract.ProprieteEntry.COL_PIECES;
    public static final String COL_CARACTERISTIQUES = VentesImmobilieresContract.ProprieteEntry.COL_CARACTERISTIQUES;
    public static final String COL_PRIX = VentesImmobilieresContract.ProprieteEntry.COL_PRIX;
    public static final String COL_VILLE = VentesImmobilieresContract.ProprieteEntry.COL_VILLE;
    public static final String COL_ID_VENDEUR = VentesImmobilieresContract.ProprieteEntry.COL_ID_VENDEUR;
    public static final String COL_IMAGES = VentesImmobilieresContract.ProprieteEntry.COL_IMAGES;
    public static final String COL_DATE = VentesImmobilieresContract.ProprieteEntry.COL_DATE;

    public static final String V_TABLE_NAME = VentesImmobilieresContract.VendeurEntry.TABLE_NAME;
    public static final String V_COL_ID = VentesImmobilieresContract.VendeurEntry.COL_ID;
    public static final String V_COL_NOM = VentesImmobilieresContract.VendeurEntry.COL_NOM;
    public static final String V_COL_PRENOM = VentesImmobilieresContract.VendeurEntry.COL_PRENOM;
    public static final String V_COL_EMAIL = VentesImmobilieresContract.VendeurEntry.COL_EMAIL;
    public static final String V_COL_TEL = VentesImmobilieresContract.VendeurEntry.COL_TEL;

    public static void initDatabase(Context ctx) {
        VentesImmobilieresDBOpener mdb = new VentesImmobilieresDBOpener(ctx, VentesImmobilieresContract.DB_NAME, null, 1);
        SQLiteDatabase db = mdb.getWritableDatabase();
        mdb.deleteDatabase(db);
    }

    public static Cursor lireTousPropietes(Context ctx) {
        VentesImmobilieresDBOpener dbo = new VentesImmobilieresDBOpener(ctx, DB_NAME, null, 1);
        SQLiteDatabase db = dbo.getReadableDatabase();

        String[] colonnes = new String[] {
            COL_ID,
                COL_TITRE,
                COL_DESCRIPTION,
                COL_PIECES,
                COL_CARACTERISTIQUES,
                COL_PRIX,
                COL_VILLE,
                COL_ID_VENDEUR,
                COL_IMAGES,
                COL_DATE
        };

        String sortOrder = COL_DATE + " DESC";
        Cursor cursor = db.query(
          TABLE_PROP,
          colonnes,
          null,
                null,
                null,
                null,
                sortOrder,
                null
        );
        return cursor;
    }

    public static boolean proprieteInDatabase(Context ctx, Propriete propriete) {
        VentesImmobilieresDBOpener dbo = new VentesImmobilieresDBOpener(ctx, DB_NAME, null, 1);
        SQLiteDatabase db = dbo.getReadableDatabase();

        String[] colonnes = new String[] {
                COL_TITRE
        };

        String[] selectionArgs = new String[] {
                propriete.getTitre()
        };

        Cursor cursor = db.query(
                TABLE_PROP,
                colonnes,
                COL_TITRE + "=?",
                selectionArgs,
                null,
                null,
                null,
                null
        );
        return cursor.getCount() == 1;
    }

    public static long ajouterPropriete(Context ctx, Propriete propriete) {
        ContentValues insertValues = new ContentValues();
        insertValues.put(COL_TITRE, propriete.getTitre());
        insertValues.put(COL_DESCRIPTION, propriete.getDescription());
        insertValues.put(COL_PIECES, propriete.getNombre_piece());
        insertValues.put(COL_CARACTERISTIQUES, listStringToString(propriete.getCaracteristiques(), ","));
        insertValues.put(COL_PRIX, propriete.getPrix());
        insertValues.put(COL_VILLE, propriete.getVille());
        insertValues.put(COL_ID_VENDEUR, propriete.getVendeur().getId());
        insertValues.put(COL_IMAGES, listStringToString(propriete.getImages(),","));
        insertValues.put(COL_DATE, propriete.getDate().getTime());

        VentesImmobilieresDBOpener dbo = new VentesImmobilieresDBOpener(ctx, DB_NAME, null, 1);
        SQLiteDatabase db = dbo.getWritableDatabase();
        return db.insert(TABLE_PROP, null, insertValues);
    }

    public static long ajouterVendeur(Context ctx, Vendeur vendeur) {
        ContentValues insertValues = new ContentValues();
        insertValues.put(V_COL_ID, vendeur.getId());
        insertValues.put(V_COL_NOM, vendeur.getNom());
        insertValues.put(V_COL_PRENOM, vendeur.getPrenom());
        insertValues.put(V_COL_EMAIL, vendeur.getEmail());
        insertValues.put(V_COL_TEL, vendeur.getTelephone());

        VentesImmobilieresDBOpener dbo = new VentesImmobilieresDBOpener(ctx, DB_NAME, null, 1);
        SQLiteDatabase db = dbo.getWritableDatabase();
        return db.insert(V_TABLE_NAME, null, insertValues);
    }

    public static void ajouterImages(Context ctx, Propriete propriete, String pathImage) {

    }

    public static int supprimerPropriete(Context ctx, Propriete propriete) {
        String where = COL_TITRE + "=?";
        String[] whereArgs = { propriete.getTitre() };
        VentesImmobilieresDBOpener dbo = new VentesImmobilieresDBOpener(ctx, DB_NAME, null, 1);
        SQLiteDatabase db = dbo.getWritableDatabase();
        int hasdelete = db.delete(TABLE_PROP, where, whereArgs);
        return hasdelete;
    }

    public static String listStringToString(List<String> listString, String separator) {
        String res = "";
        Iterator<String> iter = listString.iterator();
        while (iter.hasNext()) {
            String value = iter.next();
            res += value;
            if (iter.hasNext()) {
                res += separator;
            }
        }
        return res;
    }

    public static List<String> stringTolistString(String str, String separator) {
        String[] splitStr = str.split(separator);
        return new ArrayList<>(Arrays.asList(splitStr));
    }

}
