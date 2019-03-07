package fr.unicaen.info.users.a21606807.ventesimmobilires.model;

import com.squareup.moshi.JsonReader;

public class ProprieteResponse {

    private boolean success;
    private JsonReader response;

    public ProprieteResponse(boolean success, JsonReader response){
        this.success=success;
        this.response=response;
    }

    public JsonReader getResponse(){
        return this.response;
    }

    public boolean getSuccess(){
        return this.success;
    }

}
