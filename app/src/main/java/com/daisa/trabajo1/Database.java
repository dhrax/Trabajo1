package com.daisa.trabajo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.daisa.trabajo1.Constantes.ANHOSALIDA;
import static com.daisa.trabajo1.Constantes.BBDD;
import static com.daisa.trabajo1.Constantes.DESARROLLADORA;
import static com.daisa.trabajo1.Constantes.FAVORITO;
import static com.daisa.trabajo1.Constantes.GENERO;
import static com.daisa.trabajo1.Constantes.IMAGEN;
import static com.daisa.trabajo1.Constantes.NOMBRE;
import static com.daisa.trabajo1.Constantes.PC;
import static com.daisa.trabajo1.Constantes.PLASTATION;
import static com.daisa.trabajo1.Constantes.SW;
import static com.daisa.trabajo1.Constantes.TABLA_VIDEOJUEGOS;
import static com.daisa.trabajo1.Constantes.TIENDA;
import static com.daisa.trabajo1.Constantes.VALORACION;
import static com.daisa.trabajo1.Constantes.XBOX;

public class Database extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private final String[] SELECT = new String[] {_ID, NOMBRE, DESARROLLADORA, GENERO, ANHOSALIDA, PC, XBOX, PLASTATION, SW, VALORACION, TIENDA, FAVORITO, IMAGEN};
    //private final String[] tiendas = new String[]{"GAME"};

    public Database(Context context) {
        super(context, BBDD, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLA_VIDEOJUEGOS + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NOMBRE + " TEXT,  " + DESARROLLADORA + " TEXT," +
                GENERO+" TEXT, "+ANHOSALIDA+" TEXT, "+ PC + " INTEGER, "+ XBOX +" INTEGER, "+ PLASTATION +" INTEGER, "+ SW + " INTEGER, "+VALORACION+" REAL, "+
                TIENDA+ " TEXT, "+FAVORITO+" INTEGER, "+ IMAGEN +" BLOB)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }


    public void nuevoVideojuego(Videojuego videojuego){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOMBRE, videojuego.getNombre());
        values.put(DESARROLLADORA, videojuego.getDesarrolladora());
        values.put(GENERO, videojuego.getGenero());
        values.put(ANHOSALIDA, videojuego.getAnhoSalida());
        values.put(PC, videojuego.isPC());
        values.put(XBOX, videojuego.isXbox());
        values.put(PLASTATION, videojuego.isPlayStation());
        values.put(SW, videojuego.isSW());
        values.put(VALORACION, videojuego.getValoracion());
        values.put(TIENDA, videojuego.getTienda());
        values.put(FAVORITO, videojuego.isFavorito());
        values.put(IMAGEN, Util.getBytes(videojuego.getImagen()));
        db.insertOrThrow(TABLA_VIDEOJUEGOS, null, values);
        db.close();
    }

    public ArrayList<Videojuego> getLista(Cursor cursor){
        ArrayList<Videojuego> videojuegos = new ArrayList<>();

        while(cursor.moveToNext()){
            Videojuego videojuego = new Videojuego(cursor.getFloat(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3),  cursor.getString(4), cursor.getInt(5)>=1,cursor.getInt(6)>=1,
                    cursor.getInt(7)>=1, cursor.getInt(8)>=1, cursor.getFloat(9), cursor.getString(10),
                    cursor.getInt(11)>=1);

            videojuego.setImagen(Util.getBitmap((cursor.getBlob(12))));
            videojuegos.add(videojuego);
        }

        return videojuegos;
    }

    public ArrayList<Videojuego> getVideojuegos(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLA_VIDEOJUEGOS, SELECT, null, null, null, null, NOMBRE);
        return getLista(cursor);
    }

    public ArrayList<Videojuego> getVideojuegosTienda(String tienda){
        SQLiteDatabase db = getReadableDatabase();
        String[] tiendas = new String[]{tienda};
        Cursor cursor =  db.query(TABLA_VIDEOJUEGOS, SELECT, "tienda = ? ", tiendas, null, null, NOMBRE);
        return getLista(cursor);
    }







}
