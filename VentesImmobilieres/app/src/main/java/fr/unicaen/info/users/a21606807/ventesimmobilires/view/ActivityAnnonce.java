package fr.unicaen.info.users.a21606807.ventesimmobilires.view;

import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.unicaen.info.users.a21606807.ventesimmobilires.R;
import fr.unicaen.info.users.a21606807.ventesimmobilires.db.VentesImmobilieresDB;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.Propriete;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.ProprieteAdapter;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.ProprieteResponseAdapter;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.Vendeur;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ActivityAnnonce extends AppCompatActivity {

    private Propriete propriete;
    private List<String> remarques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce);

        Toolbar myToolbar = findViewById(R.id.annonce_toolbar);
        setSupportActionBar(myToolbar);


        /*
        ImageView image = (ImageView) findViewById(R.id.image_annonce);
        Picasso.get().load(
                "https://www.villadeale.fr/media/thumbnails/villatango_3_chambres_gd__032041100_1112_31102017.jpg"
        ).into(image);*/

        ArrayList<String> images = new ArrayList<>();
        images.add("https://www.villadeale.fr/media/thumbnails/villatango_3_chambres_gd__032041100_1112_31102017.jpg");
        images.add("https://www.maisons-vesta.com/2017/images/modeles-contemporains.jpg");
        images.add("http://www.plaisancia.fr/typo3temp/_processed_/csm_maison-plaisancia-hericourt_f1763b14a9.jpg");


        remarques = new ArrayList<>();
        String remarque1 = "ouais c'était cool ok je prend";
        String remarque2 = "bon en vrai je sais pas c'est cher c'te merde";
        String remarque3 = "blablabla bon en vrai je sais pas c'est cher c'te merdebon en vrai je sais pas c'est cher c'te merdebon en vrai je sais pas c'est cher c'te merdebon en vrai je sais pas c'est cher c'te merde";
        String remarque4 = "blablabla bon en vrai je sais pas c'est cher c'te merdebon en vrai je sais pas c'est cher c'te merdebon en vrai je sais pas c'est cher c'te merdebon en vrai je sais pas c'est cher c'te merde";
        String remarque5 = "blablabla bon en vrai je sais pas c'est cher c'te merdebon en vrai je sais pas c'est cher c'te merdebon en vrai je sais pas c'est cher c'te merdebon en vrai je sais pas c'est cher c'te merde";
        String remarque6 = "blablabla bon en vrai je sais pas c'est cher c'te merdebon en vrai je sais pas c'est cher c'te merdebon en vrai je sais pas c'est cher c'te merdebon en vrai je sais pas c'est cher c'te merde";
        remarques.add(remarque1);
        remarques.add(remarque2);
        remarques.add(remarque3);
        remarques.add(remarque4);
        remarques.add(remarque5);
        remarques.add(remarque6);
        RecyclerView recycler = findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        RemarqueAdapter remarque_adapter = new RemarqueAdapter(remarques);
        recycler.setAdapter(remarque_adapter);

        Propriete propriete;

        Intent intent = getIntent();
        if (intent.getParcelableExtra("propriete") != null) {
            propriete = getIntent().getParcelableExtra("propriete");
        } else {
            propriete = new Propriete(
                    "2",
                    "Maison cool",
                    "Super maison trop TROP géniale !",
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
        }
        this.propriete = propriete;
        this.fillAnnonce(propriete);
        //VentesImmobilieresDB.initDatabase(this);
        this.getPropriete("https://ensweb.users.info.unicaen.fr/android-estate/mock-api/immobilier.json");
        //this.getPropriete("https://ensweb.users.info.unicaen.fr/android-estate/mock-api/liste.json");
    }

    // crée le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_annonce, menu);
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
        if (id == R.id.action_accueil) {
            this.finish();
        } else if (id == R.id.action_camera) {
            if (this.propriete.getIsOnLocal()) {
                this.useCamera();
            } else {
                this.showSnackBarMessage("La propriété doit être enregistrée");
            }
        } else if (id == R.id.action_save) {
            if (!this.propriete.getIsOnLocal()) {
                VentesImmobilieresDB.ajouterPropriete(this, this.propriete);
                this.showSnackBarMessage("Propriété enregistrée");
                this.propriete.setIsOnLocal(true);
            } else {
                this.showSnackBarMessage("La propriété déjà enregistrée");
            }
        } else if (id == R.id.action_delete) {
            if (this.propriete.getIsOnLocal()) {
                VentesImmobilieresDB.supprimerPropriete(this, this.propriete);
                this.showSnackBarMessage("Propriété supprimée");
                this.propriete.setIsOnLocal(false);
            } else {
                this.showSnackBarMessage("La propriété n'est pas en local");
            }
        } else if (id == R.id.action_save_list) {
            Cursor c = VentesImmobilieresDB.lireTousPropietes(this);
            if (c.moveToFirst()) {
                do {
                    String[] row = new String[c.getColumnCount()];
                    for (int i = 0; i<c.getColumnCount(); i++) {
                        row[i] = c.getString(i);
                    }
                    String res = "";
                    for (String str : row) {
                        res += str + " _ ";
                    }
                    Log.i("val", res);
                } while (c.moveToNext());
            }
        } else if (id == R.id.action_remarque) {
            if (this.propriete.getIsOnLocal()) {
                Intent intent = new Intent(this, RemarqueActivity.class);
                startActivity(intent);
            } else {
                this.showSnackBarMessage("La propriété doit être enregistrée");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void useCamera() {
        int REQUEST_IMAGE_CAPTURE = 1;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            /*String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File photoFile = File.create
            }*/
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void showSnackBarMessage(String message) {
        Snackbar.make(
                findViewById(R.id.annonce),
                ((CharSequence)message),
                Snackbar.LENGTH_LONG
        ).show();
    }

    public void fillAnnonce(Propriete propriete) {
        ((TextView) findViewById(R.id.titre_annonce)).setText(propriete.getTitre());
        ((TextView) findViewById(R.id.text_prix)).setText(propriete.getPrix() + " €");
        ((TextView) findViewById(R.id.text_ville)).setText(propriete.getVille());
        ((TextView) findViewById(R.id.description)).setText(propriete.getDescription());
        ((TextView) findViewById(R.id.text_date)).setText(propriete.getDate().toString());
        ((TextView) findViewById(R.id.text_nomVendeur)).setText(propriete.getVendeur().getPrenomNom());
        ((TextView) findViewById(R.id.text_mailVendeur)).setText(propriete.getVendeur().getEmail());
        ((TextView) findViewById(R.id.text_telephoneVendeur)).setText(propriete.getVendeur().getTelephone());
        LinearLayout image_layout = (LinearLayout) findViewById(R.id.image_layout);
        for (int i = 0; i < propriete.getImages().size(); i++) {
            ImageView image = new ImageView(this);
            Log.i("val",propriete.getImages().get(i));
            Picasso.get().load(
                    propriete.getImages().get(i)
            ).into(image);
            image_layout.addView(image);
        }


    }

    public void getPropriete(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected HTTP code " + response);
                    }

                    Moshi moshi = new Moshi.Builder().add(new ProprieteResponseAdapter()).add(new ProprieteAdapter()).build();
                    Type type = Types.newParameterizedType(List.class, Propriete.class);
                    Log.i("val", "PASSAGE ICI");
                    JsonAdapter<ArrayList<Propriete>> jsonAdapter = moshi.adapter(type);
                    Log.i("val", "PASSAGE LA !!!!");

                    ArrayList<Propriete> responseJson = jsonAdapter.fromJson(responseBody.string());
                    Log.i("val", "ENFIN !!!");
                    Log.i("val", ""+responseJson);

                }
            }
        });
    }

    public class RemarqueAdapter extends RecyclerView.Adapter<ActivityAnnonce.RemarqueViewHolder> {

        private List<String> list;

        public RemarqueAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public ActivityAnnonce.RemarqueViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_annonce_list_remarque, viewGroup,false);
            return new ActivityAnnonce.RemarqueViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ActivityAnnonce.RemarqueViewHolder remarque_view_holder, final int position) {
            String remarque = list.get(position);
            remarque_view_holder.bind(remarque);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public class RemarqueViewHolder extends RecyclerView.ViewHolder {

        private TextView remarque;
        private ConstraintLayout layout_item;

        public RemarqueViewHolder(View item_view) {
            super(item_view);
            this.remarque = (TextView) item_view.findViewById(R.id.description);
            this.layout_item = (ConstraintLayout) item_view.findViewById(R.id.layout_item);
        }

        public void bind(String r){
            this.remarque.setText(r);
        }
    }
}
