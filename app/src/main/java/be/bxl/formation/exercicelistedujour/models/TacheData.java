package be.bxl.formation.exercicelistedujour.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TacheData implements Parcelable {
    private long id;
    private LocalDate DateTache;
    private String name;
    private String Description;

    public TacheData(LocalDate dateTache, String name, String description) {
        this.id=0;
        this.DateTache = dateTache;
        this.name = name;
        this.Description = description;
    }

    public TacheData(Long id, LocalDate dateTache, String name, String description) {
        this.id=id;
        this.DateTache = dateTache;
        this.name = name;
        this.Description = description;
    }

    public TacheData(Integer jour, Integer mois, Integer annee, String name, String description) {

        this.DateTache = LocalDate.of(annee,mois,jour);
        this.name = name;
        this.Description = description;
    }


    protected TacheData(Parcel in) {
        id = in.readLong();
        name = in.readString();
        Description = in.readString();
        DateTache = LocalDate.parse(in.readString(),DateTimeFormatter.ISO_DATE);
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(Description);
        dest.writeString(DateTache.format(DateTimeFormatter.ISO_DATE));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TacheData> CREATOR = new Creator<TacheData>() {
        @Override
        public TacheData createFromParcel(Parcel in) {
            return new TacheData(in);
        }

        @Override
        public TacheData[] newArray(int size) {
            return new TacheData[size];
        }
    };

    public LocalDate getDateTache() {
        return DateTache;
    }
    public String getDateTacheString() {
        return DateTache.format(DateTimeFormatter.ISO_DATE);
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
