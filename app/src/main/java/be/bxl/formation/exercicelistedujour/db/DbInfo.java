package be.bxl.formation.exercicelistedujour.db;

public class DbInfo {

    public static final String DB_NAME = "my_db";
    public static final int DB_VERSION = 1;

    public static class Tache {
        // Nom de la table
        public static final String TABLE_NAME = "tache";

        // Nom des colonnes
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DATEEVENT = "dateEvent";

        // Requetes (DDL)
        public static final String REQUEST_CREATE =
                "CREATE TABLE " + Tache.TABLE_NAME + " ( "
                        + Tache.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + Tache.COLUMN_NAME + " TEXT NOT NULL, "
                        + Tache.COLUMN_DESCRIPTION + " TEXT, "
                        + Tache.COLUMN_DATEEVENT + " Date "
                        + ");" ;

        public static final String REQUEST_DELETE = "DROP TABLE " + Tache.TABLE_NAME + ";" ;
    }

}
