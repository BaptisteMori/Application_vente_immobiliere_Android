package fr.unicaen.info.users.a21606807.ventesimmobilires;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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

        /*
        ImageView image = (ImageView) findViewById(R.id.image_annonce);
        Picasso.get().load(
                "https://www.villadeale.fr/media/thumbnails/villatango_3_chambres_gd__032041100_1112_31102017.jpg"
        ).into(image);*/

        ArrayList<String> images = new ArrayList<>();
        images.add("https://www.villadeale.fr/media/thumbnails/villatango_3_chambres_gd__032041100_1112_31102017.jpg");

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
        ImageView image = (ImageView) findViewById(R.id.image_annonce);
        Picasso.get().load(
                propriete.getImages().get(0)
        ).into(image);
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

                    Moshi moshi = new Moshi.Builder().build();

                    Log.i("val", "PASSAGE ICI");
                    JsonAdapter<Propriete> adapter = moshi.adapter(Propriete.class);
                    Log.i("val", "PASSAGE LA !!!!");

                    Propriete prop = adapter.fromJson(responseBody.string());
                    Log.i("val", "ENFIN !!!");
                    Log.i("val", prop.toString());

                }
            }
        });
    }
}
