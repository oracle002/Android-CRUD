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

public class updateActivity extends AppCompatActivity {

    DbHelper dbHelper;
    EditText itemfield, qntyfeild;
    Button updatebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DbHelper(this);
        itemfield = findViewById(R.id.updateitem);
        qntyfeild = findViewById(R.id.updateqnty);
        updatebtn = findViewById(R.id.updatebtn);


        updatebtn.setOnClickListener(v ->{
            //get both fields, check empty, if not empty - > updateitem pass values

            String item = itemfield.getText().toString().trim();
            String quantity = qntyfeild.getText().toString().trim();

            if( item.isEmpty() || quantity.isEmpty()){

                Toast.makeText(updateActivity.this, "PLEASE FILL ALL THE FIELDS" , Toast.LENGTH_LONG).show();
                return;
            }
            int quant = Integer.parseInt(quantity);
            if( dbHelper.updateItem(item,quant)){

                Toast.makeText(updateActivity.this, "SUCCESS", Toast.LENGTH_LONG).show();
                finish();
            }
            else{
                Toast.makeText(updateActivity.this, "FAILED", Toast.LENGTH_LONG).show();
                itemfield.setText("");
                qntyfeild.setText("");
            }
        });


    }
}