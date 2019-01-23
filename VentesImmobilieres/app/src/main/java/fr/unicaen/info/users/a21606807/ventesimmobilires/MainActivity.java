package fr.unicaen.info.users.a21606807.ventesimmobilires;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
}
