package be.bxl.formation.exercicelistedujour;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.time.LocalDate;
import java.util.ArrayList;

import be.bxl.formation.exercicelistedujour.models.TacheData;

import static java.time.LocalDate.now;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Add_event extends AppCompatActivity implements View.OnClickListener{
    public static final String EXTRA_LOCALEDATE = "localeDate";
    public static final String EXTRA_LOCALEARRAY = "Arraydata";
    private Button btnmoinonday, btnplusoneday, btnAfficherEvent;
    private LocalDate dateevent = now();
    private ArrayList<TacheData> Datatache = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        btnmoinonday = findViewById(R.id.bt_addevent_moinsunjour);
        btnplusoneday = findViewById(R.id.bt_addevent_plusunjour);
        btnAfficherEvent = findViewById(R.id.bt_addevent_afficherevent);

        if(getIntent().hasExtra(EXTRA_LOCALEDATE)) {
            Bundle bundle = getIntent().getExtras();
            dateevent = LocalDate.parse(bundle.getString(EXTRA_LOCALEDATE));
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
                plusoneday();
                break;

            default:
                throw new RuntimeException("Bouton non implementé !");
        }
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