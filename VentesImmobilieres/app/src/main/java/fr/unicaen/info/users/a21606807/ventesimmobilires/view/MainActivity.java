package fr.unicaen.info.users.a21606807.ventesimmobilires.view;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.unicaen.info.users.a21606807.ventesimmobilires.R;
import fr.unicaen.info.users.a21606807.ventesimmobilires.db.VentesImmobilieresDB;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.Propriete;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.ProprieteResponse;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.RequestAnnonce;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.Vendeur;

public class MainActivity extends AppCompatActivity {

    private List<Propriete> propriete_list;
    final static String url = "https://ensweb.users.info.unicaen.fr/android-estate/mock-api/liste.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        propriete_list = new ArrayList<>();

        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);

        RequestAnnonce request = new RequestAnnonce();
        request.getPropriete(this.url);
        ProprieteResponse response = request.getResponseJson();

        if (response.getSuccess()) {
            this.propriete_list = response.getResponse();
        }

        RecyclerView recycler = findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        MainAnnonceAdapter annonce_adapter = new MainAnnonceAdapter(this, this.propriete_list);
        recycler.setAdapter(annonce_adapter);
    }

    public void openDialog() {
        DeleteProprieteDialog dialog = new DeleteProprieteDialog();
        dialog.show(getSupportFragmentManager(), "DeleteProprieteDialog");
    }

    // crée le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }


    // callback déclenché lors d'une action sur le menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save_list) {
            Intent intent = new Intent(this,ListAnnoncesActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void openAnnonce(View v, Propriete propriete) {
        Intent intent = new Intent(this, ActivityAnnonce.class);
        intent.putExtra("propriete", propriete);
        startActivity(intent);
    }


}
