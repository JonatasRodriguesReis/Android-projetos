package com.lge.loadon;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IPActivity extends AppCompatActivity {
    IPHelper ipHelper;
    EditText txt;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        txt = (EditText) findViewById(R.id.txtIP);

        ipHelper = new IPHelper(getBaseContext());

        Cursor cursor = ipHelper.getWritableDatabase().rawQuery("select valor from ips where id = 0",null);
        if(cursor.moveToNext()){
            this.txt.setText(cursor.getString(0));
        }
    }

    public void trocarip(View v){
        // Gets the data repository in write mode
        SQLiteDatabase db = ipHelper.getReadableDatabase();

        ContentValues valores;
        String where;

        where = "id" + "= 0 ";

        valores = new ContentValues();
        valores.put("valor", this.txt.getText().toString());

        Log.d("Atu", Integer.toString(db.update("ips",valores,where,null)));

        Toast.makeText(this,"Endereço IP Atualizado",Toast.LENGTH_SHORT).show();


    }
}
