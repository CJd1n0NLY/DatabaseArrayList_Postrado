package com.mobiledatabase_postrado;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class RecordsActivity extends ListActivity {

    public SQLiteDatabase Conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Conn = new SQLiteDatabase(this);
        try {
            ArrayList<String> Items = Conn.GetAllData();
            if(Items.size() > 0){

                setListAdapter(new ArrayAdapter<String>(RecordsActivity.this, android.R.layout.simple_list_item_1, Items));

            }else {
                Toast.makeText(getApplicationContext(), "NO RECORDS FOUND", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
