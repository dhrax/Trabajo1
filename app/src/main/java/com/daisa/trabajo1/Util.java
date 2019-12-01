package com.daisa.trabajo1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class Util {
    public static final int FECHA_PRIMER_VIDEOJUEO=1958;

    /**
     * Convierte un Bitmap en un array de bytes
     * @param bitmap
     * @return
     */
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return bos.toByteArray();
    }

    /**
     * Convierte un array de bytes en un objeto Bitmap
     * @param bytes
     * @return
     */
    public static Bitmap getBitmap(byte[] bytes) {

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    /**
     *Comprueba si la fecha indicada es un número entero
     * @param fecha
     * @return true si es un integer, false si no lo es
     */
    public static boolean isNumeric(String fecha){
        for (char c : fecha.toCharArray())
            if (!Character.isDigit(c))
                return false;
        return true;
    }

    /**
     * Comprueba que la fecha de salida sea posterior a la del primer videojuego
     * @param fecha
     * @return true si es posterior al primer videojuego, false si no lo es
     */
    public static boolean fechaValida(String fecha){
        int fec = Integer.parseInt(fecha);

        if (fec<FECHA_PRIMER_VIDEOJUEO)
            return false;
        return true;
    }
}
