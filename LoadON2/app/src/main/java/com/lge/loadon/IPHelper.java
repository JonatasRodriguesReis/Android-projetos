package com.lge.loadon;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by francisco.pereira on 15/01/2019.
 */

public class IPHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_IPS =
            "CREATE TABLE IPS ( id INT PRIMARY KEY, valor TEXT)";
    private static final String SQL_DELETE_IPS =
            "DROP TABLE IF EXISTS ips";
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "IPBANCO.db";
    public IPHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("Teste","Veio");
    }
    public void onCreate(SQLiteDatabase db) {
        Log.d("Jona","cREATE");
        try {
            db.execSQL(SQL_CREATE_IPS);
            ContentValues valores;

            valores = new ContentValues();

            valores.put("id", 0);
            valores.put("valor", "10.193.236.94");
            Log.d("Jona",Long.toString(db.insert("ips", null, valores)));
        }catch (SQLException e){
            Log.e("Erro do Banco",e.getMessage());
        }
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_IPS);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
