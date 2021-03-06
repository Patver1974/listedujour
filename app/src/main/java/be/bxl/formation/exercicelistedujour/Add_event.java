package be.bxl.formation.exercicelistedujour;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.usage.UsageEvents;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import be.bxl.formation.exercicelistedujour.Adapter.ActiveOneAdapters;
import be.bxl.formation.exercicelistedujour.Adapter.ActiviteAdapters;
import be.bxl.formation.exercicelistedujour.Listener.EventRecyclerItemClickListener;
import be.bxl.formation.exercicelistedujour.db.dao.TacheDao;
import be.bxl.formation.exercicelistedujour.models.TacheData;

import static java.time.LocalDate.now;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Add_event extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_LOCALEDATE = "localeDate";
    public static final String EXTRA_LOCALEARRAY = "Arraydata";

    private Button btnmoinonday, btnplusoneday, btnAfficherEvent, btnAfficherAllEvent, btnExit;
    private Button btdelDay;

    private LocalDate dateevent = now();
    private ArrayList<TacheData> datatache = new ArrayList<>();
    private RecyclerView rvActivite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        //utiliser le bundle
        //datatache.add(new TacheData(23, 6, 2021, "manger", "prendre banane"));
        //datatache.add(new TacheData(23, 6, 2021, "manger", "prendre pomme"));


        btdelDay = findViewById(R.id.bt_addevent_deleteDay);


        btnExit = findViewById(R.id.bt_addevent_exit);
        btnAfficherAllEvent = findViewById(R.id.bt_addevent_tousevenement);

        btnmoinonday = findViewById(R.id.bt_addevent_moinsunjour);
        btnplusoneday = findViewById(R.id.bt_addevent_plusunjour);
        btnAfficherEvent = findViewById(R.id.bt_addevent_afficherevent);
        rvActivite = findViewById(R.id.rv_addevent_item);
        btnExit = findViewById(R.id.bt_addevent_exit);
        btnAfficherAllEvent = findViewById(R.id.bt_addevent_tousevenement);

        if (getIntent().hasExtra(EXTRA_LOCALEDATE)) {
            Bundle bundle = getIntent().getExtras();
            dateevent = LocalDate.parse(bundle.getString(EXTRA_LOCALEDATE), DateTimeFormatter.ISO_DATE);
            afficherdatebutton();

            btnplusoneday.setOnClickListener(this);
            btnmoinonday.setOnClickListener(this);
            btnAfficherEvent.setOnClickListener(this);
            btnExit.setOnClickListener(this);
            btnAfficherAllEvent.setOnClickListener(this);

        }
    }

    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.bt_addevent_moinsunjour:
                moinsoneday();
                break;
            case R.id.bt_addevent_plusunjour:
                plusoneday();
                break;
            case R.id.bt_addevent_afficherevent:
                afficherlestache();
                break;
            case R.id.bt_addevent_tousevenement:
                afficherlestacheAll();
                break;
            case R.id.bt_addevent_exit:
                goExit();
                break;

            case R.id.bt_addevent_deleteDay:
                goDeleteday();
                break;

            default:
                throw new RuntimeException("Bouton non implement?? !");
        }
    }


    private void goDeleteday() {
        TacheDao tacheDao = new TacheDao(getApplicationContext());
        tacheDao.openWritable();
        tacheDao.deletedatebefore(dateevent);
    }


    private void afficherlestacheAll() {
        TacheDao tacheDao = new TacheDao(getApplicationContext());
        tacheDao.openReadable();
        datatache = tacheDao.getAll();
        tacheDao.close();
        afficherrecyclerview();
    }

    private void goExit() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void afficherlestache() {


        TacheDao tacheDao = new TacheDao(getApplicationContext());
        tacheDao.openReadable();
        datatache = tacheDao.getAllWithDate(dateevent);
        tacheDao.close();
        afficherrecyclerview();
    }

    private void afficherrecyclerview() {


        ActiviteAdapters activiteAdapters = new ActiviteAdapters(
                getApplicationContext(),
                datatache
        );
        if (datatache.isEmpty()){
            Toast.makeText(getApplicationContext(),"Pas d'evenement aujourd'hui", Toast.LENGTH_LONG).show();


        }
            // Configurer le RecyclerView
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                    3, StaggeredGridLayoutManager.VERTICAL
            );
            rvActivite.setLayoutManager(layoutManager);

            rvActivite.setAdapter(activiteAdapters);
            rvActivite.setHasFixedSize(true);

            rvActivite.addOnItemTouchListener(
                    new EventRecyclerItemClickListener(getApplicationContext(), (view, position) -> {
                        Log.d("patrick", String.valueOf(position));
                        TacheData mytache = datatache.get(position);
                        onEventClick(mytache);
                    }));
        }

private void onEventClick(TacheData tache){

    Log.d("patrick",tache.getName());
    setContentView(R.layout.activity_main);
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Choix de l'action")

            .setMessage("Voulez vous modifier, supprimer ou copier cette tache")
            .setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    TacheDao tacheDao = new TacheDao(getApplicationContext());
                    tacheDao.openReadable();
                    datatache = tacheDao.getOne(tache);
                    tacheDao.close();

                    ActiveOneAdapters activeOneAdapters = new ActiveOneAdapters(
                            getApplicationContext(),
                            datatache
                    );

                    // Configurer le RecyclerView
                    StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                            3, StaggeredGridLayoutManager.VERTICAL
                    );
                    rvActivite.setLayoutManager(layoutManager);

                    rvActivite.setAdapter(activeOneAdapters);
                    rvActivite.setHasFixedSize(true);



                }
            })
            .setNegativeButton("Supprimer", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    TacheDao tacheDao = new TacheDao(getApplicationContext());
                    tacheDao.openWritable();
                    tacheDao.deleteonetache(tache);
                }
            })


            .setNeutralButton("Copier", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            })
            .setCancelable(false) // on peut plus coupe l'alert en cliquat a cote
            .show();


}

    public void moinsoneday() {
        dateevent = dateevent.minusDays(1);
        afficherdatebutton();
        afficherlestache();
    }

    private void afficherdatebutton() {

        btnAfficherEvent.setText("evenement du " + dateevent.toString());

    }


    public void plusoneday() {
        dateevent = dateevent.plusDays(1);
        afficherdatebutton();
        afficherlestache();

    }


}