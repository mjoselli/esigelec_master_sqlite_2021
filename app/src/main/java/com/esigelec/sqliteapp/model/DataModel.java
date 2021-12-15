package com.esigelec.sqliteapp.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class DataModel {
    private static DataModel instance = new DataModel();
    private DataModel(){

    }
    public static DataModel getInstance(){
        return instance;
    }
    private Context context;
    private CityDatabase database;
    private ArrayList<City> cities;
    public void setContext(Context context){
        this.context = context;
        database = new CityDatabase(context);
        cities = database.getCitiesFromDB();
        Log.v("CITY_DATABASE","Array size:"+cities.size());
        for (City c:cities) {
            Log.v("CITY_DATABASE","Retrived:"+c.getName());
        }
    }
    public void addCity(City city){
        long id = database.createCityInDB(city);
        if(id > 0){
            city.setId(id);
            cities.add(city);
            Log.v("CITY_DATABASE","Added["+id +"]:"+city.getName());
        }else{
            Log.e("CITY_DATABASE","error adding city:"+city.getName());
        }
    }
    public void updateCity(City city,int position){
        int count = database.updateCityInDB(city);
        if(count > 0){
            cities.set(position,city);
            Log.v("CITY_DATABASE","Updated["+city.getId() +"]:"+city.getName());
        }
    }
    public void removeCity(int position){
        City city = cities.get(position);
        int count = database.removeCityFromDB(
                city
        );
        if(count > 0){
            Log.v("CITY_DATABASE","removed["+city.getId() +"]:"+city.getName());
            cities.remove(city);
        }
    }
    public ArrayList<City> getCities(){
        return cities;
    }
}
