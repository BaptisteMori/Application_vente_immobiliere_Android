package fr.unicaen.info.users.a21606807.ventesimmobilires.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VentesImmobilieresDBOpener extends SQLiteOpenHelper {

    private static final String DB_CREATE = "create table if not exists " + VentesImmobilieresContract.ProprieteEntry.TABLE_NAME + "(" +
            VentesImmobilieresContract.ProprieteEntry.COL_ID + " int primary key auto_increment," +
            VentesImmobilieresContract.ProprieteEntry.COL_TITRE + " varchar(50)," +
            VentesImmobilieresContract.ProprieteEntry.COL_DESCRIPTION + " varchar(250)," +
            VentesImmobilieresContract.ProprieteEntry.COL_PIECES + " int," +
            VentesImmobilieresContract.ProprieteEntry.COL_CARACTERISTIQUES + " varchar(300)," +
            VentesImmobilieresContract.ProprieteEntry.COL_PRIX + " int," +
            VentesImmobilieresContract.ProprieteEntry.COL_VILLE + " varchar(50)" +
            VentesImmobilieresContract.ProprieteEntry.COL_ID_VENDEUR + " int," +
            VentesImmobilieresContract.ProprieteEntry.COL_IMAGES + " varchar(500)," +
            VentesImmobilieresContract.ProprieteEntry.COL_DATE + " datetime" +
            ");" +
            "create table if not exists " + VentesImmobilieresContract.VendeurEntry.TABLE_NAME + "(" +
            VentesImmobilieresContract.VendeurEntry.COL_ID + " int primary key auto_increment," +
            VentesImmobilieresContract.VendeurEntry.COL_NOM + " varchar(30)," +
            VentesImmobilieresContract.VendeurEntry.COL_PRENOM + " varchar(30)," +
            VentesImmobilieresContract.VendeurEntry.COL_EMAIL + " varchar(50)," +
            VentesImmobilieresContract.VendeurEntry.COL_TEL + " varchar(10)" +
            ");";

    private static final String DB_DELETE = "drop table if exists " + VentesImmobilieresContract.ProprieteEntry.TABLE_NAME + ";" +
            "drop table if exists " + VentesImmobilieresContract.VendeurEntry.TABLE_NAME + ";";

    public VentesImmobilieresDBOpener(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DB_DELETE);
        onCreate(db);
    }

    public void deleteDatabase(SQLiteDatabase db) {
        db.execSQL(DB_DELETE);
        onCreate(db);
    }
}
