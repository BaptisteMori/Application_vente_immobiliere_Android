package fr.unicaen.info.users.a21606807.ventesimmobilires;

import android.util.Log;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

public class ProrieteAdapter {

    @ToJson
    public String toJson(Propriete propriete) {
        return propriete.getId() + propriete.getTitre().substring(0,1);
    }

    @FromJson
    public Propriete fromJson(String propiete) {
        Log.i("val", "WOUUUAA");
        char id = propiete.charAt(0);
        char titre = propiete.charAt(1);
        Log.i("val", "id " + id + "  titre " + titre);
        return null;
    }
}
