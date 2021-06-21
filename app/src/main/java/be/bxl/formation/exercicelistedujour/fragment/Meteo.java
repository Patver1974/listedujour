package be.bxl.formation.exercicelistedujour.fragment;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDate;

import be.bxl.formation.exercicelistedujour.R;
import be.bxl.formation.exercicelistedujour.webapi.RequestWeatherTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Meteo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Meteo extends Fragment {
private LocalDate DateMeteo;
   private TextView  tvWeather;
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

            RequestWeatherTask requestWeatherTask = new RequestWeatherTask();

            requestWeatherTask.setWeatherListener(data -> {
                tvWeather.setText(data.getCity() + " " + data.getTemp() + "°c");
            });

            //requestWeatherTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"Bruxelles, CA");






        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_meteo, container, false);


        return v;
    }
}