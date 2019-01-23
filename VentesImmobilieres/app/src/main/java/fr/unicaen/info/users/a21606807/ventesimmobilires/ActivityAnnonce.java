package fr.unicaen.info.users.a21606807.ventesimmobilires;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

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

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://ensweb.users.info.unicaen.fr/android-estate/mock-api/immobilier.json")
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
                    Headers responseHeaders = response.headers();
                    Log.i("val", responseHeaders.toString());
                    for (int i = 0, size = responseHeaders.size(); i<size; i++) {
                        Log.i("val", responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
                }
            }
        });

        ImageView image = (ImageView) findViewById(R.id.image_annonce);
        Picasso.get().load(
                "https://www.villadeale.fr/media/thumbnails/villatango_3_chambres_gd__032041100_1112_31102017.jpg"
        ).into(image);
    }
}
