package be.bxl.formation.exercicelistedujour.fragment;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.IntegerRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import be.bxl.formation.exercicelistedujour.R;
import be.bxl.formation.exercicelistedujour.db.dao.TacheDao;
import be.bxl.formation.exercicelistedujour.models.TacheData;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AjouterActivite#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AjouterActivite extends Fragment {
    private DatePicker dp_date;
    private EditText et_nomactivite, et_description;
    private Button bt_valider, bt_annuler;
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


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ajouter_activite, container, false);
        dp_date = v.findViewById(R.id.dp_frag_addevent_dateevent);
        et_nomactivite = v.findViewById(R.id.et_frag_addevent_nomactivite);
        et_description = v.findViewById(R.id.et_frag_addevent_description);
        bt_valider = v.findViewById(R.id.bt_frag_addevent_valider);
        bt_annuler = v.findViewById(R.id.bt_frag_addevent_annuler);

        initializeViewData();


        return v;
    }

    private void initializeViewData() {

        et_nomactivite.setText("Nom1");
        et_description.setText("Desc1");


        bt_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogClearTask();



            }
        });





        bt_valider.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                TacheData ATaches = new TacheData(getLocatDatePicker(dp_date), et_nomactivite.getText().toString(), et_description.getText().toString());
                if (taskListener != null ){
                    taskListener.onClickItem(ATaches);
                    TacheDao tacheDao =  new TacheDao(getContext());
                    tacheDao.openWritable();
                    long id = tacheDao.insert(ATaches);
                    tacheDao.close();
                }
            }
        });

    }

    private LocalDate getLocatDatePicker(DatePicker dp_date) {

        LocalDate ldate = LocalDate.of(dp_date.getYear(),dp_date.getMonth()+1,dp_date.getDayOfMonth());

        return  ldate;
    }

    private void openDialogClearTask() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Are you sure ?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        et_nomactivite.setText("");
                        et_description.setText("");
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //appuie sur back
                        //getActivity().getSupportFragmentManager().popBackStackImmediate();
                        //generer un evement pour effacer
                    }
                })
                .show();
    }





    @FunctionalInterface
    public interface OnTaskClick {
        void onClickItem(TacheData ATaches);
    }

    private OnTaskClick taskListener;
    public void setTaskListener(OnTaskClick event) { this.taskListener = event;};















}