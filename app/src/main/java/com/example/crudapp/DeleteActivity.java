package com.example.crudapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeleteActivity extends AppCompatActivity {

    DbHelper dbHelper;
    EditText deleteitem;
    Button deletebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DbHelper(this);
        deleteitem = findViewById(R.id.deleteitem);
        deletebtn = findViewById(R.id.deletebtn);

        deletebtn.setOnClickListener(v ->{
            //get string, check empty, if not empty call deleleteitem function, toast, clear input
            String item = deleteitem.getText().toString().trim();

            if(item.isEmpty()){
                Toast.makeText(DeleteActivity.this, "Please fill the item name", Toast.LENGTH_LONG).show();
                return;
            }

            if(dbHelper.deleteItem(item)){
                Toast.makeText(DeleteActivity.this, "SUCCESS", Toast.LENGTH_LONG).show();
                finish();
            }
            else{
                Toast.makeText(DeleteActivity.this, "FAILED", Toast.LENGTH_LONG).show();
                deleteitem.setText("");
            }


        });


    }
}