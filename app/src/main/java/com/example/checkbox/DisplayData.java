package com.example.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayData extends AppCompatActivity {
    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    ListView listView;
    String[]name,name2;
    int[]id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        dBmain=new DBmain(DisplayData.this);
        findid();
        displayData();
    }

    private void displayData() {
        sqLiteDatabase=dBmain.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select *from course",null);
        if (cursor.getCount()>0){
            id=new int[cursor.getCount()];
            name=new String[cursor.getCount()];
            name2=new String[cursor.getCount()];
            int i=0;
            while(cursor.moveToNext()){
                id[i]=cursor.getInt(0);
                name[i]=cursor.getString(1);
                name2[i]=cursor.getString(2);
                i++;
            }
            Custom custom=new Custom();
            listView.setAdapter(custom);
        }
    }

    private void findid() {
        listView=findViewById(R.id.lv);
    }

    private class Custom extends BaseAdapter {
        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView,textView1;
            convertView= LayoutInflater.from(DisplayData.this).inflate(R.layout.singledata,parent,false);
            textView=(TextView)convertView.findViewById(R.id.textView);
            textView1=(TextView)convertView.findViewById(R.id.textView2);

            textView.setText(name[position]);
            textView1.setText(name2[position]);
            return convertView;
        }
    }
}