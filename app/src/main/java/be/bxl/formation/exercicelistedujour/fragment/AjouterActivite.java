package be.bxl.formation.exercicelistedujour.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.ArrayList;

import be.bxl.formation.exercicelistedujour.R;
import be.bxl.formation.exercicelistedujour.models.TacheData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AjouterActivite#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AjouterActivite extends Fragment {
private DatePicker dp_date;
private EditText et_nomactivite, et_description;
private Button bt_valider,bt_annuler;
private ArrayList<TacheData> ArrayTache;


    public AjouterActivite() {
        // Required empty public constructor
    }


    public static AjouterActivite newInstance() {
        AjouterActivite fragment = new AjouterActivite();

        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bt_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TacheData ATaches= new TacheData(dp_date.getDayOfMonth(),dp_date.getMonth(),dp_date.getYear(),et_nomactivite.getText().toString(),et_description.getText().toString());
                et_description.setText("555");
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_ajouter_activite, container, false);
        dp_date=v.findViewById(R.id.dp_frag_addevent_dateevent);
        et_nomactivite=v.findViewById(R.id.et_frag_addevent_nomactivite);
        et_description=v.findViewById(R.id.et_frag_addevent_description);
        bt_valider=v.findViewById(R.id.bt_frag_addevent_valider);
        bt_annuler=v.findViewById(R.id.bt_frag_addevent_annuler);


        return v;
    }
}