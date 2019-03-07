package fr.unicaen.info.users.a21606807.ventesimmobilires.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fr.unicaen.info.users.a21606807.ventesimmobilires.R;

public class RemarqueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remarque);
    }

    public void retour(View v) {
        this.finish();
    }

    public void enregistrer(View v) {
        System.out.println("OKOKOK");
    }
}
