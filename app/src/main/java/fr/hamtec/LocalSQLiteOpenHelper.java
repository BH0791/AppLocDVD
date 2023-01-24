package fr.hamtec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalSQLiteOpenHelper extends SQLiteOpenHelper {
    static String DB_NAME = "locDVD.db";
    static int DB_VERSION = 1;

    public LocalSQLiteOpenHelper(
            Context context,
            String name,
            SQLiteDatabase.CursorFactory factory,
            int version) {

        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlFilTable = "CREATE TABLE DVD(id INTEGER PRIMARY KEY," +
                "titre TEXT, annee NUMERIC, acteurs TEXT, resume TEXT);";
        db.execSQL(sqlFilTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = oldVersion; i < newVersion; i++) {
            int versionToUpdate = i + 1;
            if (versionToUpdate == 2) {
                // mettre a verion 2
            } else if (versionToUpdate == 3) {
                // mettre a version 3
            }
        }
    }
}
