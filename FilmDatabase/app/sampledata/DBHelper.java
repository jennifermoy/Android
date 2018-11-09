package edu.chapman.jennifer.filmdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper{

    static String DATABASE_NAME = "FilmDB";
    public static final String TABLE_NAME = "FilmList";

    public static final String FilmID = "FilmID";
    public static final String Name = "Name";
    public static final String DateReleased = "DateReleased";
    public static final String FileName = "FileName";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +"("
                +FilmID+" INTEGER PRIMARY KEY UNIQUE, "
                + Name + " TEXT, "
                + DateReleased + " TEXT, "
                + FileName + " Text" + ")";
        db.execSQL(CREATE_TABLE);

        /*db.execSQL("INSERT INTO FilmList (FilmID, Name, DateReleased, FileName) " +
                "VALUES('1', 'Ratatouille', '2007', '/movies/Ratatouille.mp4')");*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String id, String name, String dateReleased, String fileName) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FilmID, id);
        contentValues.put(Name, name);
        contentValues.put(DateReleased, dateReleased);
        contentValues.put(FileName, fileName);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }

        else {
            return true;
        }

        //db.close();
    }

    public boolean updateData(String id, String name, String dateReleased, String fileName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FilmID, id);
        contentValues.put(Name, name);
        contentValues.put(DateReleased, dateReleased);
        contentValues.put(FileName, fileName);

        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});

        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }

    /*public ArrayList<HashMap<String, String>> GetUsers() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT Name, DateReleased, FileName FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("FilmID", cursor.getString(cursor.getColumnIndex(FilmID)));
            user.put("Name", cursor.getString(cursor.getColumnIndex(Name)));
            user.put("DateReleased", cursor.getString(cursor.getColumnIndex(DateReleased)));
            user.put("FileName", cursor.getString(cursor.getColumnIndex(FileName)));

            userList.add(user);
        }

        return userList;
    }

    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT Name, DateReleased, FileName FROM " + TABLE_NAME;
    }*/

}
