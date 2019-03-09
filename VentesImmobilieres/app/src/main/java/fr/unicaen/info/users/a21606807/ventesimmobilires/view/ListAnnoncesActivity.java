package fr.unicaen.info.users.a21606807.ventesimmobilires.view;

import android.content.Intent;
import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.unicaen.info.users.a21606807.ventesimmobilires.R;
import fr.unicaen.info.users.a21606807.ventesimmobilires.db.VentesImmobilieresDB;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.Propriete;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.Vendeur;

public class ListAnnoncesActivity extends AppCompatActivity {

    private List<Propriete> propriete_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        propriete_list = new ArrayList<>();
        setContentView(R.layout.activity_list_annonces);

        Cursor c = VentesImmobilieresDB.lireTousPropietes(this);
        if (c.moveToFirst()) {
            do {
                Vendeur vendeur = null;
                Log.i("val","ICI OK");
                Cursor cv = VentesImmobilieresDB.lireVendeur(this,c.getString(7));
                Log.i("val",cv.getCount()+"");
                if (cv.moveToFirst()) {
                    do {
                        Log.i("val","moveToFirst de cv");
                        vendeur = new Vendeur(
                                cv.getString(0),
                                cv.getString(1),
                                cv.getString(2),
                                cv.getString(3),
                                cv.getString(4)
                        );
                    } while (c.moveToNext());
                }
                if (vendeur != null) {
                    Log.i("val","VENDEUR PAS NUL");
                    Propriete propriete = new Propriete(
                            c.getString(0),
                            c.getString(1),
                            c.getString(2),
                            Integer.parseInt(c.getString(3)),
                            VentesImmobilieresDB.stringTolistString(c.getString(4),","),
                            Integer.parseInt(c.getString(5)),
                            c.getString(6),
                            vendeur,
                            VentesImmobilieresDB.stringTolistString(c.getString(8),","),
                            Long.parseLong(c.getString(9))
                    );
                    this.propriete_list.add(propriete);
                    Log.i("val","PROPR AJOUTEE A LA LISTE");
                }
            } while (c.moveToNext());
        }

        RecyclerView recycler = findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        AnnonceAdapter annonce_adapter = new AnnonceAdapter(this, this.propriete_list);
        recycler.setAdapter(annonce_adapter);
    }

    public void openAnnonce(View v, Propriete propriete) {
        Intent intent = new Intent(this, ActivityAnnonce.class);
        intent.putExtra("propriete", propriete);
        startActivity(intent);
    }

}


