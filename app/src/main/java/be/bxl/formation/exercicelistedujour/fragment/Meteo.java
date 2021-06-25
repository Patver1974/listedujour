package be.bxl.formation.exercicelistedujour.fragment;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.time.LocalDate;

import be.bxl.formation.exercicelistedujour.R;
import be.bxl.formation.exercicelistedujour.models.TacheData;
import be.bxl.formation.exercicelistedujour.webapi.RequestWeatherTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Meteo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Meteo extends Fragment {
    private LocalDate DateMeteo;
    private TextView tvWeather;
    private EditText etVille;
    private FrameLayout flmeteo;
    private Button btValiderNom;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DATE_METEO = "DateMeteo";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Meteo() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static Meteo newInstance(LocalDate dateMeteo) {
        Meteo fragment = new Meteo();
        Bundle args = new Bundle();
        args.putString(ARG_DATE_METEO, dateMeteo.toString());
        fragment.setArguments(args);
        return fragment;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            DateMeteo = LocalDate.parse(getArguments().getString(ARG_DATE_METEO));




        }
    }

    private void Donnermeteo(String Ville    ) {
        RequestWeatherTask requestWeatherTask = new RequestWeatherTask();

        requestWeatherTask.setWeatherListener(data -> {
            tvWeather.setText(data.getCity() + " " + data.getTemp() + "°c" + " humidite " + data.getHumidity());
        });

        requestWeatherTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Ville);





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_meteo, container, false);
        tvWeather = v.findViewById(R.id.tv_frag_weather);
        etVille = v.findViewById(R.id.et_frag_meteo_nomville);
        btValiderNom = v.findViewById(R.id.bt_frag_meteo_validernom);
        flmeteo = v.findViewById(R.id.Fl_meteo_Frame);
        initialiseMeteo();
        return v;
    }


    private void initialiseMeteo() {




        btValiderNom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Ville = etVille.getText().toString().trim();
                Donnermeteo(etVille.getText().toString().trim());
                if (meteoListener != null) {
                    meteoListener.onClickItem(Ville);

                }
            }
        });
    }

    @FunctionalInterface
    public interface OnMeteoClick {
        void onClickItem(String Ville);
    }

    private Meteo.OnMeteoClick meteoListener;

    public void setMeteoListener(Meteo.OnMeteoClick event) {
        this.meteoListener = event;
    }


}








