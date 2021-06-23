package be.bxl.formation.exercicelistedujour.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import be.bxl.formation.exercicelistedujour.R;
import be.bxl.formation.exercicelistedujour.models.TacheData;

public class ActiviteAdapters extends RecyclerView.Adapter<ActiviteAdapters.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvDescription;
        private CardView cvCategory;

        public ViewHolder(@NonNull View view) {
            super(view);

            tvName = view.findViewById(R.id.item_actvite_name);
            tvDescription = view.findViewById(R.id.item_activite_description);
            //cvCategory = view.findViewById(R.id.item_cv_color);
        }

        public TextView getTvName() {
            return tvName;
        }

        public TextView getTvDescription() {
            return tvDescription;
        }

        //public CardView getCvCategory() { return cvCategory; }
    }


    private List<TacheData> dataSet; // Utilisation du type interface (Découplage)
    private Context context;

    public ActiviteAdapters(Context context, List<TacheData> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActiviteAdapters.ViewHolder holder, int position) {
        TacheData tacheData = dataSet.get(position);



        holder.getTvName().setText(tacheData.getName());
        holder.getTvDescription().setText(tacheData.getDescription());


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
