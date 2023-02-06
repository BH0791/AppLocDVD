package fr.hamtec;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DVD {
    long id;
    String titre;
    int annee;
    String acteurs[];
    String resume;
    private String cheminPhoto;
    long dateVisionnage;

    public DVD() {

    }

    private DVD(@NotNull Cursor cursor) {
        id = cursor.getLong(cursor.getColumnIndex("id"));
        titre = cursor.getString(cursor.getColumnIndex("titre"));
        annee = cursor.getInt(cursor.getColumnIndex("annee"));
        acteurs = cursor.getString(cursor.getColumnIndex("acteurs")).split(";");
        resume = cursor.getString(cursor.getColumnIndex("resume"));
    }

    public static DVD getDVD(Context context, long id) {
        DVD dvd = null;
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String where = "id = " + String.valueOf(id);
        Cursor cursor = db.query(
                true,
                "DVD",
                new String[]{"id", "titre", "annee", "acteurs", "resume", "dateVisionnage", "cheminPhoto"},
                where,
                null,
                null,
                null,
                "titre",
                null);

        if (cursor.moveToFirst())
            dvd = new DVD(cursor);

        cursor.close();
        db.close();

        return dvd;
    }

    public void update(Context context) {
        String whereClause = "id=" + String.valueOf(this.id);
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.update("DVD", getContentValues(), whereClause, null);
        db.close();
    }

    public void insert(Context context) {
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        this.id = db.insert("DVD", null, getContentValues());
        db.close();
    }

    public void delete(Context context) {
        String whereClause = "id= ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = String.valueOf(this.id);
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("DVD", whereClause, whereArgs);
        db.close();
    }

    private ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put("titre", this.titre);
        values.put("annee", this.annee);
        if (this.acteurs != null) {
            String listActeurs = new String();
            for (int i = 0; i < this.acteurs.length; i++) {
                listActeurs += this.acteurs[i];
                if (i < this.acteurs.length - 1)
                    listActeurs += ";";
            }
            values.put("acteurs", listActeurs);
        }
        values.put("resume", this.resume);
        values.put("dateVisionnage", this.dateVisionnage);
        values.put("cheminPhoto", this.cheminPhoto);
        return values;
    }

    public static ArrayList<DVD> getDVDList(Context context) {
        ArrayList<DVD> listDVD = new ArrayList<>();
        LocalSQLiteOpenHelper helper = new LocalSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(
                true,
                "DVD",
                new String[]{"id", "titre", "annee", "acteurs", "resume"},
                null,
                null,
                null,
                null,
                "titre",
                null);

        while (cursor.moveToNext()) {
            //---
            listDVD.add(new DVD(cursor));
        }

        cursor.close();
        db.close();

        //---
        return listDVD;
    }

    public long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public int getAnnee() {
        return annee;
    }

    public String[] getActeurs() {
        return acteurs;
    }

    public String getResume() {
        return resume;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public void setActeurs(String[] acteurs) {
        this.acteurs = acteurs;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
