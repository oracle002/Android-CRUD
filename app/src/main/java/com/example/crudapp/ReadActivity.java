package com.example.crudapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class ReadActivity extends AppCompatActivity {

    DbHelper dbHelper;
    ListView listViewItems;
    SimpleAdapter adapter;
    ArrayList<HashMap<String,String>> itemlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DbHelper(this);
        listViewItems = findViewById(R.id.readitems);
        //keyvalue pair
        //hashmap<String,String>
        //arraylist<hashmap<String,String>>

        itemlist = new ArrayList<>();

        loadItems();

    }

    public void loadItems(){
        // get the cursor
        //iterate through all rows
        //each row as data to insert to arraylist
        // data hashmap
        //"itemname" = row.itemname
        //"quantity" = row.quantity
        //hashmap -> put to arraylist
        //simpleadaptor -> list

        Cursor cursor = dbHelper.readItems();
        if(cursor.moveToFirst()){
            do{
                String itemName = cursor.getString(0);
                int quantity = cursor.getInt(1);

                //hashmap for each data as key value
                HashMap<String,String> item = new HashMap<>();
                item.put("name", itemName);
                item.put("quantity", String.valueOf(quantity));

                //add to arraylist
                itemlist.add(item);

            }while(cursor.moveToNext());
        }
        //parameters - context, arraylist, each item list layout, string,ids
        adapter = new SimpleAdapter(
                this,
                itemlist,
                R.layout.list_items,
                new String[]{"name","quantity"},//string str [] = {}
                new int[]{R.id.readitemname,R.id.readitemqnty}
        );

        //show in list view
        listViewItems.setAdapter(adapter);

    }
}