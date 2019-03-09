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
    public ProprieteResponse fromJson(JsonReader reader, JsonAdapter<Propriete> delegate) throws IOException {
        ProprieteResponse result = null;

        ArrayList<Propriete> p = new ArrayList<>();
        reader.beginObject();

        boolean success = false;
        JsonReader response = null;

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("success")) {
                success = reader.nextBoolean();
                Log.i("val",""+success);
            } else if (name.equals("response")) {
                //response = reader.peekJson();
                reader.beginArray();
                while (reader.hasNext()){
                    p.add(delegate.fromJson(reader));
                }
                reader.endArray();
                reader.endObject();
                result=new ProprieteResponse(success,p);
                return result;
            } else {
                throw new IOException("Response contient des données non conformes");
            }
        }
        // fermer le reader
        reader.endObject();

        return result;
    }

}
