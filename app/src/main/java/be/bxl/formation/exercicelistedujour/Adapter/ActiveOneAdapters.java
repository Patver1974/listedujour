package be.bxl.formation.exercicelistedujour.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import be.bxl.formation.exercicelistedujour.R;
import be.bxl.formation.exercicelistedujour.models.TacheData;

public class ActiveOneAdapters extends RecyclerView.Adapter<ActiveOneAdapters.ViewHolder>{

        public static class ViewHolder extends RecyclerView.ViewHolder {
            private EditText edName, edDescription, eddate;
            private CardView cvCategory;

            public ViewHolder(@NonNull View view) {
                super(view);

                edName = view.findViewById(R.id.item_actviteone_name);
                edDescription = view.findViewById(R.id.item_activiteone_description);
                eddate = view.findViewById(R.id.item_actviteone_date);

            }

            public EditText getTvName() {
                return edName;
            }

            public EditText getTvDescription() {
                return edDescription;
            }

            public EditText getTvdate() { return eddate; }
        }


    private List<TacheData> dataSet; // Utilisation du type interface (Découplage)
    private Context context;

    public ActiveOneAdapters(Context context, List<TacheData> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ActiveOneAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_one_task, parent, false);
        return new ActiveOneAdapters.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveOneAdapters.ViewHolder holder, int position) {
        TacheData tacheData = dataSet.get(position);
        holder.getTvName().setText(tacheData.getName());
        holder.getTvDescription().setText(tacheData.getDescription());
        holder.getTvdate().setText(tacheData.getDateTacheString());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
