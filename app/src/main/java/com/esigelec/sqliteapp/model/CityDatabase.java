package com.esigelec.sqliteapp.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class CityDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "cities.sqlite";
    private static final int DB_VERSION = 1;
    private static String DB_TABLE = "City";
    private static String COL_ID = "id";
    private static String COL_NAME = "name";
    private static String COL_POPULATION = "population";

    /*
    CRUD
    CREATE
    RETRIEVE
    UPDATE
    DELETE
     */
    private Context context;
    public CityDatabase(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query;/* = "CREATE TABLE IF NOT EXISTS "+DB_TABLE+"(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_POPULATION + " %s INTEGER)";*/
        query = String.format("CREATE TABLE IF NOT EXISTS %s(" +
                " %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " %s TEXT, " +
                " %s INTEGER)",
                DB_TABLE,COL_ID,COL_NAME,COL_POPULATION);
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //CRUD
    //CREATE - CREATE A CITY IN THE DB
    public long createCityInDB(City city){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME,city.getName());
        values.put(COL_POPULATION,city.getPopulation());
        long id = database.insert(DB_TABLE,null,values);
        database.close();
        return id;
        //database.execSQL("Insert into "+DB_TABLE);
    }

    //RETRIEVE
    @SuppressLint("Range")
    public ArrayList<City> getCitiesFromDB(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DB_TABLE,null,null,
                null,null,null,null);
        ArrayList<City>cities = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                long id = cursor.getLong(cursor.getColumnIndex(COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                int population = cursor.getInt(cursor.getColumnIndex(COL_POPULATION));
                cities.add(
                        new City(id,name,population)
                );
            }while(cursor.moveToNext());

        }
        database.close();
        return cities;
    }

    //UPDATE
    public int updateCityInDB(City city){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME,city.getName());
        values.put(COL_POPULATION,city.getPopulation());
        String id = String.valueOf(city.getId());
        int count = database.update(DB_TABLE,values,COL_ID + "=?",
                new String[]{id});
        database.close();
        return count;
    }
    //DELETE
    public int removeCityFromDB(City city){
        SQLiteDatabase database = getWritableDatabase();
        String id = String.valueOf(city.getId());
        int count = database.delete(DB_TABLE,
                COL_ID + "=?",new String[]{id});
        database.close();
        return count;
    }
}
