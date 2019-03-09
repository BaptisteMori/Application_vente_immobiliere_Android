package fr.unicaen.info.users.a21606807.ventesimmobilires.model;

import java.util.ArrayList;

public class ProprieteResponse {

    private boolean success;
    private ArrayList<Propriete> response;

    public ProprieteResponse(boolean success, ArrayList<Propriete> response){
        this.success=success;
        this.response=response;
    }

    public ArrayList<Propriete> getResponse(){
        return this.response;
    }

    public boolean getSuccess(){
        return this.success;
    }

    @Override
    public String toString() {
        return this.response.get(0).getTitre();
    }
}
