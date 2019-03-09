package fr.unicaen.info.users.a21606807.ventesimmobilires.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VentesImmobilieresDBOpener extends SQLiteOpenHelper {

    private static final String CREATE_PROP = "create table if not exists " + VentesImmobilieresContract.ProprieteEntry.TABLE_NAME + "(" +
            VentesImmobilieresContract.ProprieteEntry.COL_ID + " integer primary key autoincrement," +
            VentesImmobilieresContract.ProprieteEntry.COL_TITRE + " varchar(50)," +
            VentesImmobilieresContract.ProprieteEntry.COL_DESCRIPTION + " varchar(250)," +
            VentesImmobilieresContract.ProprieteEntry.COL_PIECES + " int," +
            VentesImmobilieresContract.ProprieteEntry.COL_CARACTERISTIQUES + " varchar(300)," +
            VentesImmobilieresContract.ProprieteEntry.COL_PRIX + " int," +
            VentesImmobilieresContract.ProprieteEntry.COL_VILLE + " varchar(50)," +
            VentesImmobilieresContract.ProprieteEntry.COL_ID_VENDEUR + " int," +
            VentesImmobilieresContract.ProprieteEntry.COL_IMAGES + " varchar(500)," +
            VentesImmobilieresContract.ProprieteEntry.COL_DATE + " datetime," +
            VentesImmobilieresContract.ProprieteEntry.COL_REMARQUES + " LONGTEXT, " +
            "UNIQUE(" + VentesImmobilieresContract.ProprieteEntry.COL_TITRE + ")" +
            ");";

    private static final String CREATE_VENDEUR = "create table if not exists " + VentesImmobilieresContract.VendeurEntry.TABLE_NAME + "(" +
            VentesImmobilieresContract.VendeurEntry.COL_ID + " integer primary key autoincrement," +
            VentesImmobilieresContract.VendeurEntry.COL_NOM + " varchar(30)," +
            VentesImmobilieresContract.VendeurEntry.COL_PRENOM + " varchar(30)," +
            VentesImmobilieresContract.VendeurEntry.COL_EMAIL + " varchar(50)," +
            VentesImmobilieresContract.VendeurEntry.COL_TEL + " varchar(10)" +
            ");";

    private  static final String CREATE_REMARQUE = "create table if not exists " + VentesImmobilieresContract.RemarqueEntry.TABLE_NAME + "(" +
            VentesImmobilieresContract.RemarqueEntry.COL_ID_PROP + " integer," +
            VentesImmobilieresContract.RemarqueEntry.COL_REMARQUE + " LONGTEXT" +
            ");";

    private static final String DELETE_PROP = "drop table if exists " + VentesImmobilieresContract.ProprieteEntry.TABLE_NAME + ";";

    private static final String DELETE_VENDEUR = "drop table if exists " + VentesImmobilieresContract.VendeurEntry.TABLE_NAME + ";";

    private static final String DELETE_REMARQUE = "drop table if exists " + VentesImmobilieresContract.RemarqueEntry.TABLE_NAME + ";";

    public VentesImmobilieresDBOpener(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROP);
        db.execSQL(CREATE_VENDEUR);
        db.execSQL(CREATE_REMARQUE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_PROP);
        db.execSQL(DELETE_VENDEUR);
        db.execSQL(DELETE_REMARQUE);
        onCreate(db);
    }

    public void deleteDatabase(SQLiteDatabase db) {
        db.execSQL(DELETE_PROP);
        db.execSQL(DELETE_VENDEUR);
        db.execSQL(DELETE_REMARQUE);
        onCreate(db);
    }
}
