package be.bxl.formation.exercicelistedujour.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.provider.FontsContract;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import be.bxl.formation.exercicelistedujour.db.DbHelper;
import be.bxl.formation.exercicelistedujour.db.DbInfo;
import be.bxl.formation.exercicelistedujour.models.TacheData;

public class TacheDao {


    //region Champs
    private Context context;
    private DbHelper dbHelper;
    private SQLiteDatabase db;
    //endregion

    //region Constructeur
    public TacheDao(Context context) {
        this.context = context;
    }
    //endregion


    //region Méthode de connexion
    public TacheDao openWritable() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public TacheDao openReadable() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        db.close();
        dbHelper.close();
    }
    //endregion


    //region Méthode CRUD
    @RequiresApi(api = Build.VERSION_CODES.O)
    private TacheData cursorToProduct(Cursor c) {
        // Permet de convertir les données du curseur en "Product" sur base du nom des colonnes.
        long id = c.getLong(c.getColumnIndex(DbInfo.Tache.COLUMN_ID));
        String name = c.getString(c.getColumnIndex(DbInfo.Tache.COLUMN_NAME));
        String descripton = c.getString(c.getColumnIndex(DbInfo.Tache.COLUMN_DESCRIPTION));
        String dateEvent = c.getString(c.getColumnIndex(DbInfo.Tache.COLUMN_DATEEVENT));
        LocalDate dateEventD = LocalDate.parse(dateEvent);


        return  new TacheData(id,dateEventD,name,descripton);
    }

    private ContentValues createCV(TacheData tachedata) {
        // Permet de convertir un "Product" en "ContentValues" pour utiliser les données lors d'un insert ou update
        ContentValues cv = new ContentValues();
        cv.put(DbInfo.Tache.COLUMN_NAME, tachedata.getName());
        cv.put(DbInfo.Tache.COLUMN_DESCRIPTION, tachedata.getDescription());
        cv.put(DbInfo.Tache.COLUMN_DATEEVENT, tachedata.getDateTache().toString());


        return cv;
    }

    // Create
    public long insert(TacheData tachedata) {
        ContentValues cv = createCV(tachedata);
        return db.insert(DbInfo.Tache.TABLE_NAME, null, cv);
    }
//
    // Read

    public TacheData getById(long id) {
        Cursor cursor = db.query( DbInfo.Tache.TABLE_NAME,
                null, // -> null : Toute les colonnes
                DbInfo.Tache.COLUMN_ID + " = ?",
                new String[]{ String.valueOf(id) },
                null,
                null,
                null);

        // S'il n'y a pas de résultat
        if(cursor.getCount() == 0) {
            return null;
        }

        // Place le cursor sur l'element trouvé
        cursor.moveToFirst();

        // Renvoie le produit extrait du curseur
        return cursorToProduct(cursor);
    }


    public ArrayList<TacheData> getAll() {
        Cursor cursor = db.query(DbInfo.Tache.TABLE_NAME, null, null, null, null, null, null);

        ArrayList<TacheData> taches = new ArrayList<>();

        if(cursor.getCount() == 0) {
            return taches;
        }

        cursor.moveToFirst();
        while(! cursor.isAfterLast()) {
            TacheData p = cursorToProduct(cursor);
            taches.add(p);

            cursor.moveToNext();
        }

        return taches;
    }

    // Update
    public boolean update(long id, TacheData tachedata) {
        ContentValues cv = createCV(tachedata);

        int nbRow = db.update(DbInfo.Tache.TABLE_NAME, cv,
                DbInfo.Tache.COLUMN_ID + " = ?",
                new String[]{ String.valueOf(id) });

        return nbRow == 1;
    }

    // Delete
    public boolean delete(long id) {
        // -> "DELETE FROM product WHERE _id = " + id;

        int nbRow = db.delete( DbInfo.Tache.TABLE_NAME,
                DbInfo.Tache.COLUMN_ID + " = ?",
                new String[]{ String.valueOf(id) });

        return nbRow == 1;
    }
    //endregion

    public ArrayList<TacheData> getAllWithDate(LocalDate localeDate) {
        Cursor cursor = db.query(DbInfo.Tache.TABLE_NAME, null,
                DbInfo.Tache.COLUMN_DATEEVENT+" = ?",
                new String[]{localeDate.format(DateTimeFormatter.ISO_DATE)},
                null, null, null);

        ArrayList<TacheData> taches = new ArrayList<>();

        if(cursor.getCount() == 0) {
            return taches;
        }

        cursor.moveToFirst();
        while(! cursor.isAfterLast()) {
            TacheData p = cursorToProduct(cursor);
            taches.add(p);

            cursor.moveToNext();
        }

        return taches;
    }

    // Delete
    public boolean deletedatebefore(LocalDate localDate) {
        // -> "DELETE FROM product WHERE _id = " + id;

        int nbRow = db.delete( DbInfo.Tache.TABLE_NAME,
                DbInfo.Tache.COLUMN_DATEEVENT+" = ?",
                new String[]{localDate.format(DateTimeFormatter.ISO_DATE)});

        return nbRow == 1;
    }


}


