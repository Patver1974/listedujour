package be.bxl.formation.exercicelistedujour;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import be.bxl.formation.exercicelistedujour.Adapter.ActiviteAdapters;
import be.bxl.formation.exercicelistedujour.db.dao.TacheDao;
import be.bxl.formation.exercicelistedujour.models.TacheData;

import static java.time.LocalDate.now;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Add_event extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_LOCALEDATE = "localeDate";
    public static final String EXTRA_LOCALEARRAY = "Arraydata";
    private Button btnmoinonday, btnplusoneday, btnAfficherEvent;
    private LocalDate dateevent = now();
    private ArrayList<TacheData> datatache = new ArrayList<>();
    private RecyclerView rvActivite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);



        //utiliser le bundle
        datatache.add(new TacheData(23,6,2021,"manger","prendre banane"));
        datatache.add(new TacheData(23,6,2021,"manger","prendre pomme"));

        btnmoinonday = findViewById(R.id.bt_addevent_moinsunjour);
        btnplusoneday = findViewById(R.id.bt_addevent_plusunjour);
        btnAfficherEvent = findViewById(R.id.bt_addevent_afficherevent);
        rvActivite=findViewById(R.id.rv_addevent_item);

        if(getIntent().hasExtra(EXTRA_LOCALEDATE)) {
            Bundle bundle = getIntent().getExtras();
            dateevent = LocalDate.parse(bundle.getString(EXTRA_LOCALEDATE), DateTimeFormatter.ISO_DATE);
            afficherdatebutton();

            btnplusoneday.setOnClickListener(this);
            btnmoinonday.setOnClickListener(this);
            btnAfficherEvent.setOnClickListener(this);
    }}

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

            default:
                throw new RuntimeException("Bouton non implementé !");
        }
    }

    private void afficherlestache() {
        TacheDao tacheDao = new TacheDao(getApplicationContext());
        tacheDao.openReadable();
        datatache = tacheDao.getAllWithDate(dateevent);

        tacheDao.close();



        ActiviteAdapters activiteAdapters = new ActiviteAdapters(
                getApplicationContext(),
                datatache
        );

        // Configurer le RecyclerView
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.VERTICAL
        );
        rvActivite.setLayoutManager(layoutManager);

        rvActivite.setAdapter(activiteAdapters);
        rvActivite.setHasFixedSize(true);



    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void moinsoneday() {
        dateevent = dateevent.minusDays(1);
        afficherdatebutton();
    }

    private void afficherdatebutton() {

        btnAfficherEvent.setText("evenement du " + dateevent.toString());

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void plusoneday() {
        dateevent = dateevent.plusDays(1);
        afficherdatebutton();

    }

}