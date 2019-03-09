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
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.Propriete;

public class RemarqueActivity extends AppCompatActivity {

    Propriete propriete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remarque);
        Intent intent = getIntent();
        propriete = intent.getParcelableExtra("propriete");
    }

    public void retour(View v) {
        this.restartActivityAnnonce();
    }

    public void enregistrer(View v) {
        String remarque = ((EditText) findViewById(R.id.remarque_multiligne)).getText().toString();
        long nb = VentesImmobilieresDB.ajouterRemarque(this, propriete.getId(), remarque);
        this.restartActivityAnnonce();
    }

    public void restartActivityAnnonce() {
        Intent intent = new Intent(this, ActivityAnnonce.class);
        intent.putExtra("propriete", propriete);
        startActivity(intent);
        finish();
    }
}
