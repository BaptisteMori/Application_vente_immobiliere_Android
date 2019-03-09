package fr.unicaen.info.users.a21606807.ventesimmobilires.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fr.unicaen.info.users.a21606807.ventesimmobilires.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openRandomAnnonce(View v) {
        Intent intent = new Intent(this, ActivityAnnonce.class);
        startActivity(intent);
    }

    public void openListAnnonces(View v) {
        Intent intent = new Intent(this, ListAnnoncesActivity.class);
        startActivity(intent);
    }
}
