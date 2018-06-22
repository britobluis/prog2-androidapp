package prog2final.mvcbrito.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import prog2final.mvcbrito.Modelo.Automovil;

import java.util.ArrayList;

public class AutomovilDB {
    private Context contexto;

    public AutomovilDB(Context contexto) {
        this.contexto = contexto;
    }

    public long nuevoAutomovil(Automovil automovil) {
        SQLQuery query = new SQLQuery(contexto, "Automoviles", null, 1);
        SQLiteDatabase bd = query.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("auto_placa", automovil.getPlacaAutomovil());
        valores.put("auto_marca", automovil.getmarca());
        valores.put("auto_modelo", automovil.getmodelo());
        long insercion=bd.insert("Automoviles", null, valores);
        bd.close();
        return  insercion;
    }

    public int eliminarAutomovil(String code) {
        SQLQuery query = new SQLQuery(contexto, "Automoviles", null, 1);
        SQLiteDatabase bd = query.getWritableDatabase();
        int eliminar=bd.delete("Automoviles", "auto_placa=" + code, null);
        bd.close();
        return eliminar;
    }

    public int modificarAutomovil(Automovil automovil) {
        SQLQuery query = new SQLQuery(contexto, "Automoviles", null, 1);
        SQLiteDatabase bd = query.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("auto_placa", automovil.getPlacaAutomovil());
        valores.put("auto_marca", automovil.getmarca());
        valores.put("auto_modelo", automovil.getmodelo());
        int modificar=bd.update("Automoviles", valores, "auto_placa=" + automovil.getPlacaAutomovil(), null);
        return modificar;
    }

    public ArrayList<Automovil> MostrarAutomoviles() {
        ArrayList<Automovil> automoviles=new ArrayList<>();
        SQLQuery query = new SQLQuery(contexto, "Automoviles", null, 1);
        SQLiteDatabase bd = query.getWritableDatabase();
        Cursor cursor = bd.rawQuery("Select auto_placa,auto_marca,auto_modelo from Automoviles",null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            do {
                automoviles.add(new Automovil(cursor.getInt(cursor.getColumnIndex("auto_placa")),
                        cursor.getString(cursor.getColumnIndex("auto_marca")),
                        cursor.getString(cursor.getColumnIndex("auto_modelo"))));
            } while (cursor.moveToNext());
        }
        cursor.close();
        bd.close();
        return automoviles;
    }
}
