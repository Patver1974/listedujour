package be.bxl.formation.exercicelistedujour.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class TacheData {
    private LocalDate DateTache;
    private String name;
    private String Description;

    public TacheData(LocalDate dateTache, String name, String description) {
        DateTache = dateTache;
        this.name = name;
        Description = description;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public TacheData(Integer jour, Integer mois, Integer annee, String name, String description) {
        DateTache = LocalDate.of(jour,mois,annee);
        this.name = name;
        Description = description;
    }

    public LocalDate getDateTache() {
        return DateTache;
    }

    public void setDateTache(LocalDate dateTache) {
        DateTache = dateTache;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
