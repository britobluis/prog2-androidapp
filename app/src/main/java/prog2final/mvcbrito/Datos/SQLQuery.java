package prog2final.mvcbrito.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLQuery extends SQLiteOpenHelper{
    private String nombre;

    public SQLQuery(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        nombre=name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+nombre+"(auto_placa integer primary key,auto_marca text,auto_modelo text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
