package fr.unicaen.info.users.a21606807.ventesimmobilires.view;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.unicaen.info.users.a21606807.ventesimmobilires.R;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.Propriete;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.Vendeur;

public class ListAnnoncesActivity extends AppCompatActivity {

    private List<Propriete> propriete_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        propriete_list = new ArrayList<>();
        setContentView(R.layout.activity_list_annonces);

        ArrayList<String> images = new ArrayList<>();
        images.add("https://www.villadeale.fr/media/thumbnails/villatango_3_chambres_gd__032041100_1112_31102017.jpg");
        Propriete propriete = new Propriete(
                "1",
                "Maison SWAG",
                "Super maison trop géniale !",
                5,
                new ArrayList<String>(),
                1005600,
                "Moncuq",
                new Vendeur(
                        "76",
                        "Marley",
                        "Bob",
                        "Boby@gmail.com",
                        "0215653289"
                ),
                images,
                new Date()
        );
        Propriete propriete2 = new Propriete(
                "1",
                "Carton",
                "Carton type meuble Ikea, 3m², 300 000€",
                1,
                new ArrayList<String>(),
                1005600,
                "Moncuq",
                new Vendeur(
                        "76",
                        "Marley",
                        "Bob",
                        "Boby@gmail.com",
                        "0215653289"
                ),
                images,
                new Date()
        );
        Propriete propriete3 = new Propriete(
                "1",
                "Maison traditionnelle normande",
                "Maison traditionnelle de type traditionnelle !",
                5,
                new ArrayList<String>(),
                1005600,
                "Moncuq",
                new Vendeur(
                        "76",
                        "Marley",
                        "Bob",
                        "Boby@gmail.com",
                        "0215653289"
                ),
                images,
                new Date()
        );
        Propriete propriete4 = new Propriete(
                "1",
                "Chalet de montagne",
                "Wow (pas comme WoW) !",
                5,
                new ArrayList<String>(),
                1005600,
                "Moncuq",
                new Vendeur(
                        "76",
                        "Marley",
                        "Bob",
                        "Boby@gmail.com",
                        "0215653289"
                ),
                images,
                new Date()
        );
        this.propriete_list.add(propriete);
        this.propriete_list.add(propriete2);
        this.propriete_list.add(propriete3);
        this.propriete_list.add(propriete4);
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


