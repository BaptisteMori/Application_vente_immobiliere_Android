package fr.unicaen.info.users.a21606807.ventesimmobilires.model;

import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RequestAnnonce {

    private ProprieteResponse responseJson;

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
                    Moshi moshi = new Moshi.Builder().add(new ProprieteResponseAdapter()).build();
                    JsonAdapter<ProprieteResponse> jsonAdapter = moshi.adapter(ProprieteResponse.class);

                    RequestAnnonce.this.responseJson = jsonAdapter.fromJson(responseBody.string());
                }
            }
        });
    }

    public void getOnePropriete(String url) {
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
                    Moshi moshi = new Moshi.Builder().add(new ProprieteAdapter()).build();
                    JsonAdapter<Propriete> jsonAdapter = moshi.adapter(Propriete.class);
                    Propriete p = jsonAdapter.fromJson(responseBody.string());
                    ArrayList<Propriete> listp = new ArrayList<>();
                    listp.add(p);
                    boolean succes = false;
                    if (p!=null){
                        succes = true;
                    }
                    RequestAnnonce.this.responseJson = new ProprieteResponse(succes, listp);
                }
            }
        });
    }

    public ProprieteResponse getResponseJson() {
        while (this.responseJson == null) {
            continue;
        }
        return this.responseJson;
    }
}
