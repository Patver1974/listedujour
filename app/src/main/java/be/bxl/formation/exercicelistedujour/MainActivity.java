package be.bxl.formation.exercicelistedujour;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import be.bxl.formation.exercicelistedujour.fragment.AjouterActivite;
import be.bxl.formation.exercicelistedujour.fragment.Meteo;

import static java.time.LocalDate.now;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnAddevent, btnmoinonday, btnplusoneday, btnAfficherEvent, btnMeteo;
    private LocalDate dateevent = now();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAddevent = findViewById(R.id.bt_main_addevent);
        btnmoinonday = findViewById(R.id.bt_main_moinsunjour);
        btnplusoneday = findViewById(R.id.bt_main_plusunjour);
        btnAfficherEvent = findViewById(R.id.bt_main_afficherevent);
        btnMeteo = findViewById(R.id.bt_main_meteo);

        btnAddevent.setOnClickListener(this);
        btnmoinonday.setOnClickListener(this);
        btnplusoneday.setOnClickListener(this);
        btnAfficherEvent.setOnClickListener(this);
        btnMeteo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_main_addevent:
                addevent();
                break;
            case R.id.bt_main_meteo:
                donnermeteo();
                break;
            case R.id.bt_main_moinsunjour:
                moinsoneday();
                break;
            case R.id.bt_main_plusunjour:
                plusoneday();
                break;
            case R.id.bt_main_afficherevent:
                plusoneday();
                break;

            default:
                throw new RuntimeException("Bouton non implementé !");
        }
    }

    private void donnermeteo() {
        // Création d'une nouvelle instance du fragment
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        //String text = dateevent.format(formatter);
        Meteo FragmentMeteo = Meteo.newInstance(dateevent);

        // Manipulation des Fragments via le FragmentManager
        FragmentManager fm = getSupportFragmentManager();

        // Définition d'une operation a réaliser via le Manager
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.container_fragment, FragmentMeteo);

        // Application de la transaction
        transaction.commit();


    }

    public void addevent() {
        // Création d'une nouvelle instance du fragment
        AjouterActivite FragmentAjouterActivite = AjouterActivite.newInstance();

        // Manipulation des Fragments via le FragmentManager
        FragmentManager fm = getSupportFragmentManager();

        // Définition d'une operation a réaliser via le Manager
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.container_fragment, FragmentAjouterActivite);

        // Application de la transaction
        transaction.commit();


    }

    public void moinsoneday() {
        dateevent = dateevent.minusDays(1);
        afficherdatebutton();
    }

    private void afficherdatebutton() {

        btnAfficherEvent.setText("evenement du " + dateevent.toString());

    }

    public void plusoneday() {
        dateevent = dateevent.plusDays(1);
        afficherdatebutton();

    }
}