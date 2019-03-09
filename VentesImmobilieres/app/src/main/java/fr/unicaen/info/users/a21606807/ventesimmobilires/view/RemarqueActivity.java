package fr.unicaen.info.users.a21606807.ventesimmobilires.view;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import fr.unicaen.info.users.a21606807.ventesimmobilires.R;
import fr.unicaen.info.users.a21606807.ventesimmobilires.db.VentesImmobilieresDB;

public class RemarqueActivity extends AppCompatActivity {

    String idPropriete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remarque);
        Intent intent = getIntent();
        idPropriete = intent.getStringExtra("id_propriete");
    }

    public void retour(View v) {

        //this.finish();
        Cursor c = VentesImmobilieresDB.lireRemarquePropriete(this, idPropriete);

        if (c.moveToFirst()) {
            do {
                String id = c.getString(0);
                Log.i("val", "id = " + id);
            } while (c.moveToNext());
        } else {
            Log.i("val", "PAS DE REMARQUE");
        }
    }

    public void enregistrer(View v) {
        String remarque = ((EditText) findViewById(R.id.remarque_multiligne)).getText().toString();
        long nb = VentesImmobilieresDB.ajouterRemarque(this, idPropriete, remarque);
        finish();
    }
}
