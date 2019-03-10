package fr.unicaen.info.users.a21606807.ventesimmobilires.view;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.unicaen.info.users.a21606807.ventesimmobilires.R;
import fr.unicaen.info.users.a21606807.ventesimmobilires.db.VentesImmobilieresDB;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.Propriete;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.ProprieteAdapter;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.ProprieteResponse;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.ProprieteResponseAdapter;
import fr.unicaen.info.users.a21606807.ventesimmobilires.model.Vendeur;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ActivityAnnonce extends AppCompatActivity implements DialogListener {

    private Propriete propriete;
    private RecyclerView recycler;
    private List<String> remarques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce);

        Toolbar myToolbar = findViewById(R.id.annonce_toolbar);
        setSupportActionBar(myToolbar);

        //VentesImmobilieresDB.initDatabase(this);

        /*
        ImageView image = (ImageView) findViewById(R.id.image_annonce);
        Picasso.get().load(
                "https://www.villadeale.fr/media/thumbnails/villatango_3_chambres_gd__032041100_1112_31102017.jpg"
        ).into(image);*/

        ArrayList<String> images = new ArrayList<>();
        images.add("https://www.villadeale.fr/media/thumbnails/villatango_3_chambres_gd__032041100_1112_31102017.jpg");
        images.add("https://www.maisons-vesta.com/2017/images/modeles-contemporains.jpg");
        images.add("http://www.plaisancia.fr/typo3temp/_processed_/csm_maison-plaisancia-hericourt_f1763b14a9.jpg");

        Propriete propriete;
        Intent intent = getIntent();
        if (intent.getParcelableExtra("propriete") != null) {
            propriete = getIntent().getParcelableExtra("propriete");
        } else {
            propriete = new Propriete(
                    "2",
                    "Maison cool",
                    "Super maison trop TROP géniale !",
                    5,
                    new ArrayList<String>(),
                    1005600,
                    "Moncuq",
                    new Vendeur(
                            "76",
                            "Marley",
                            "Bob",
                            "Boby@gmail.com",
                            "0215653289"
                    ),
                    new ArrayList<String>(),
                    new Date()
            );
        }
        this.propriete = propriete;

        remarques = new ArrayList<>();
        Cursor c = VentesImmobilieresDB.lireRemarquePropriete(this, propriete);
        if (c.moveToFirst()) {
            do {
                String remarque = c.getString(0);
                Log.i("val", "id = " + remarque);
                remarques.add(remarque);
            } while (c.moveToNext());
        }

        recycler = findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        RemarqueAdapter remarque_adapter = new RemarqueAdapter(remarques);
        recycler.setAdapter(remarque_adapter);


        this.fillAnnonce(propriete);
        //this.getPropriete("https://ensweb.users.info.unicaen.fr/android-estate/mock-api/immobilier.json");
        //this.getPropriete("https://ensweb.users.info.unicaen.fr/android-estate/mock-api/liste.json");

    } // FIN ONCREATE


    @Override
    public void onPositiveButton() {
        int hasDelete = VentesImmobilieresDB.supprimerPropriete(this, this.propriete);
        if (hasDelete > 0) {
            VentesImmobilieresDB.supprimerRemarquePropriete(this, this.propriete);
            this.fillRemarque();
            this.showSnackBarMessage("Propriété supprimée");
        } else {
            this.showSnackBarMessage("La propriété n'a pas été supprimée");
        }
    }

    @Override
    public void onNegativeButton() {

    }

    public void fillRemarque() {
        remarques = new ArrayList<>();
        Cursor c = VentesImmobilieresDB.lireRemarquePropriete(this, propriete);
        if (c.moveToFirst()) {
            do {
                String remarque = c.getString(0);
                remarques.add(remarque);
            } while (c.moveToNext());
        }
        RemarqueAdapter remarque_adapter = new RemarqueAdapter(remarques);
        recycler.setAdapter(remarque_adapter);
        recycler.refreshDrawableState();
    }

    public void openDialog() {
        DeleteProprieteDialog dialog = new DeleteProprieteDialog();
        dialog.show(getSupportFragmentManager(), "DeleteProprieteDialog");
    }

    // crée le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_annonce, menu);
        return true;
    }


    // callback déclenché lors d'une action sur le menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_accueil) {
            this.finish();
        } else if (id == R.id.action_camera) {
            if (VentesImmobilieresDB.proprieteInDatabase(this, this.propriete)) {
                this.useCamera();
            } else {
                this.showSnackBarMessage("La propriété doit être enregistrée");
            }
        } else if (id == R.id.action_save) {
            long idProp = VentesImmobilieresDB.ajouterPropriete(this, this.propriete);
            if (idProp >= 0) {
                this.showSnackBarMessage("Propriété enregistrée");
            } else {
                this.showSnackBarMessage("La propriété est déjà enregistrée");
            }
        } else if (id == R.id.action_delete) {
            if (VentesImmobilieresDB.proprieteInDatabase(this, this.propriete)) {
                this.openDialog();
            } else {
                this.showSnackBarMessage("La propriété n'est pas en local");
            }
        } else if (id == R.id.action_save_list) {
            Cursor c = VentesImmobilieresDB.lireTousPropietes(this);
            if (c.moveToFirst()) {
                do {
                    String[] row = new String[c.getColumnCount()];
                    for (int i = 0; i<c.getColumnCount(); i++) {
                        row[i] = c.getString(i);
                    }
                    String res = "";
                    for (String str : row) {
                        res += str + " _ ";
                    }
                    Log.i("val", res);
                } while (c.moveToNext());
            }
            Intent intent = new Intent(this,ListAnnoncesActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_remarque) {
            if (VentesImmobilieresDB.proprieteInDatabase(this, this.propriete)) {
                Intent intent = new Intent(this, RemarqueActivity.class);
                intent.putExtra("propriete", propriete);
                startActivity(intent);
                finish();
            } else {
                this.showSnackBarMessage("La propriété doit être enregistrée");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    public void useCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = this.createImageFile();
            } catch (IOException e) {
                this.showSnackBarMessage("Problème lors de l'enregistrement de la photo");
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,"fr.unicaen.info.users.a21606807.ventesimmobilires.android.fileprovider",photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                this.propriete.addImages(this.currentPhotoPath);
                Log.i("val",""+this.propriete.getImages());
                this.galleryAddPic();
                this.fillAnnonce(this.propriete);
            }
        }
    }

    private String currentPhotoPath;

    public File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_"+timeStamp+"_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );
        this.currentPhotoPath = "file://"+image.getAbsolutePath();
        return image;
    }

    public void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(this.currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    public void showSnackBarMessage(String message) {
        Snackbar.make(
                findViewById(R.id.annonce),
                ((CharSequence)message),
                Snackbar.LENGTH_LONG
        ).show();
    }

    public void fillAnnonce(Propriete propriete) {
        ((TextView) findViewById(R.id.titre_annonce)).setText(propriete.getTitre());
        ((TextView) findViewById(R.id.text_prix)).setText(propriete.getPrix() + " €");
        ((TextView) findViewById(R.id.text_ville)).setText(propriete.getVille());
        ((TextView) findViewById(R.id.description)).setText(propriete.getDescription());
        Date date = propriete.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        ((TextView) findViewById(R.id.text_date)).setText(sdf.format(date));
        ((TextView) findViewById(R.id.text_nomVendeur)).setText(propriete.getVendeur().getPrenomNom());
        ((TextView) findViewById(R.id.text_mailVendeur)).setText(propriete.getVendeur().getEmail());
        ((TextView) findViewById(R.id.text_telephoneVendeur)).setText(propriete.getVendeur().getTelephone());
        LinearLayout image_layout = (LinearLayout) findViewById(R.id.image_layout);
        List<String> listImages = propriete.getImages();
        if (listImages.size() > 0) {
            for (int i = 0; i < propriete.getImages().size(); i++) {
                ImageView image = new ImageView(this);
                String[] tmp = propriete.getImages().get(i).split(":");
                if (tmp[0].equals("file")) {
                    File file = new File(this.currentPhotoPath);
                    /*COMMENT QU'ON FAIT POUR LES IMAGES EN LOCAL*/
                    Picasso.get().load(
                            file
                    ).into(image);
                } else {
                    Picasso.get().load(
                            propriete.getImages().get(i)
                    ).into(image);
                }
                image_layout.addView(image);
            }
        } else {
            ImageView image = new ImageView(this);
            Picasso.get().load(
                    "https://cdn2.iconfinder.com/data/icons/lil-buildings/119/Building-10-512.png"
            ).into(image);
            image_layout.addView(image);
        }
    }
}
