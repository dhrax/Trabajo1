package com.daisa.trabajo1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.daisa.trabajo1.Constantes.BBDD;

public class Database extends SQLiteOpenHelper {

    private static final int VERSION = 1;


    public Database(Context context) {
        super(context, BBDD, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
