package fr.unicaen.info.users.a21606807.ventesimmobilires.model;

import android.util.Log;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;

import java.io.IOException;
import java.util.ArrayList;

public class ProprieteResponseAdapter {

    @ToJson
    public String toJson(ProprieteResponse propriete) {
        return "";
    }

    @FromJson
    public ArrayList<Propriete> fromJson(JsonReader reader, JsonAdapter<Propriete> delegate) throws IOException {
        //Type type = Types.newParameterizedType(List.class, Propriete.class);

        ArrayList<Propriete> p = new ArrayList<>();

        Log.i("val","ouais ça passe ici");
        reader.beginObject();

        boolean success = false;
        JsonReader response = null;

        Log.i("val", "debut parser");
        while (reader.hasNext()) {
            String name = reader.nextName();
            Log.i("val","name "+name);
            if (name.equals("success")) {
                success = reader.nextBoolean();
                Log.i("val",""+success);
            } else if (name.equals("response")) {
                //response = reader.peekJson();

                reader.beginArray();
                Log.i("val","bonsoir begin array");
                while (reader.hasNext()){
                    Log.i("val","bonsoir begin array");
                    Moshi moshi = new Moshi.Builder().add(new ProprieteAdapter()).build();
                    JsonAdapter<Propriete> jsonAdapter = moshi.adapter(Propriete.class);
                    p.add(jsonAdapter.fromJson(reader));
                }
                reader.endArray();


                Log.i("val", "peekjson");
                return p;
            } else {
                throw new IOException("Response contient des données non conformes");
            }
            Log.i("val","has next "+reader.hasNext());
        }
        Log.i("val","c'est la mer qui prend l'homme");
        // fermer le reader
        reader.endObject();
        return p;
    }

}
