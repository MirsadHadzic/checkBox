package com.example.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    Button button,button2;
    CheckBox checkBox,checkBox2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dBmain=new DBmain(MainActivity.this);
        findid();
        insertData();
    }

    private void insertData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase=dBmain.getWritableDatabase();
                ContentValues contentValues=new ContentValues();
                if (checkBox.isChecked())
                    contentValues.put("sub",checkBox.getText().toString());
                if (checkBox2.isChecked())
                    contentValues.put("sub2",checkBox2.getText().toString());
                Long rec=sqLiteDatabase.insert("course",null,contentValues);
                if (rec!=null){
                    Toast.makeText(MainActivity.this, "data inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "data not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //display data
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DisplayData.class));
            }
        });
    }

    private void findid() {
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        checkBox2=(CheckBox)findViewById(R.id.checkBox2);
        checkBox=(CheckBox)findViewById(R.id.checkBox);
    }
}