package com.esigelec.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.esigelec.sqliteapp.model.City;
import com.esigelec.sqliteapp.model.DataModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataModel.getInstance().setContext(MainActivity.this);
        /* TEST CREATION
        DataModel.getInstance().addCity(
                new City("Rouen",500000)
        );
        DataModel.getInstance().addCity(
                new City("Curitiba",2000000)
        );
        DataModel.getInstance().addCity(
                new City("Rio de Janeiro",7000000)
        );*/
        /* TEST UPDATE
        City c = DataModel.getInstance().getCities().get(0);
        c.setName("New York");
        DataModel.getInstance().updateCity(c,0);
        */
        //DataModel.getInstance().removeCity(1);
    }
}