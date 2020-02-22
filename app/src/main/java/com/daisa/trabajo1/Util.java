package com.daisa.trabajo1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class Util {
    public static final int FECHA_PRIMER_VIDEOJUEO=1958;
    public static int intentosUsuarioFallido=0;
    public static int intentosContraseñaFallido=0;

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


    /**
     *
     * @param contraseña
     * @return True, si la contraseña cumple los requerimentos
     *          False si no los cumple
     */
    public static boolean contraseñaPermitida(String contraseña){

        if(!contraseña.equals(""))
            if(!contraseña.contains("@"))
                return true;

        return false;
    }


    /**
     *
     * @param contraseña
     * @param boton
     *
     * Toggle que permite mostrar o no la contraseña en el EditText pasado por parámetro
     */
    public static void toggleVerOcultarCntraseña(EditText contraseña, TextView boton){

        String valor = boton.getText().toString();
        if(valor.equals("MOSTRAR")){
            contraseña.setTransformationMethod(null);
            boton.setText("OCULTAR");
        }else{
            contraseña.setTransformationMethod(new PasswordTransformationMethod());
            boton.setText("MOSTRAR");
        }
    }


    public static boolean comprobarContraseña(Usuario usuario, String contraseña) {

        return usuario.getContraseña().equals(contraseña);
    }
}
