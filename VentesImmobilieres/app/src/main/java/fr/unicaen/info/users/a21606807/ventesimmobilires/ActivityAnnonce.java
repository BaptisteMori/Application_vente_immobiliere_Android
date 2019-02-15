package fr.unicaen.info.users.a21606807.ventesimmobilires;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ActivityAnnonce extends AppCompatActivity {

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

        Propriete propriete;

        Intent intent = getIntent();
        if (intent.getParcelableExtra("propriete") != null) {
            propriete = getIntent().getParcelableExtra("propriete");
        } else {
            propriete = new Propriete(
                    "1",
                    "Maison SWAG",
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

        this.fillAnnonce(propriete);

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
            this.useCamera();
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Vous avez choisi l'item du menu " + item.toString();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }
        return super.onOptionsItemSelected(item);
    }

    public void useCamera() {
        int REQUEST_IMAGE_CAPTURE = 1;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void fillAnnonce(Propriete propriete) {
        ((TextView) findViewById(R.id.titre_annonce)).setText(propriete.getTitre());
        ((TextView) findViewById(R.id.text_prix)).setText(propriete.getPrix() + " €");
        ((TextView) findViewById(R.id.text_ville)).setText(propriete.getVille());
        ((TextView) findViewById(R.id.text_description)).setText(propriete.getDescription());
        ((TextView) findViewById(R.id.text_date)).setText(propriete.getDate().toString());
        ((TextView) findViewById(R.id.text_nomVendeur)).setText(propriete.getVendeur().getPrenomNom());
        ((TextView) findViewById(R.id.text_mailVendeur)).setText(propriete.getVendeur().getEmail());
        ((TextView) findViewById(R.id.text_telephoneVendeur)).setText(propriete.getVendeur().getTelephone());
        LinearLayout image_layout = (LinearLayout) findViewById(R.id.image_layout);
        for (int i = 0; i < propriete.getImages().size(); i++) {
            ImageView image = new ImageView(this);
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

                    Moshi moshi = new Moshi.Builder().add(new ProrieteAdapter()).build();

                    Log.i("val", "PASSAGE ICI");
                    JsonAdapter<Propriete> jsonAdapter = moshi.adapter(Propriete.class);
                    Log.i("val", "PASSAGE LA !!!!");

                    Propriete prop = jsonAdapter.fromJson(responseBody.string());
                    Log.i("val", "ENFIN !!!");
                    Log.i("val", prop.getTitre());

                }
            }
        });
    }
}
