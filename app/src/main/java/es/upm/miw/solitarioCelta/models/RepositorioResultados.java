package es.upm.miw.solitarioCelta.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import es.upm.miw.solitarioCelta.models.ResultadoContract.tablaResultado;
public class RepositorioResultados extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = tablaResultado.TABLE_NAME + ".db";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + tablaResultado.TABLE_NAME + " (" +
                    tablaResultado.COL_NAME_ID + " INTEGER PRIMARY KEY, " +
                    tablaResultado.COL_NAME_JUGADOR + " TEXT, " +
                    tablaResultado.COL_NAME_FICHAS + " INT, " +
                    tablaResultado.COL_NAME_DURACION + " TEXT, "+
                    tablaResultado.COL_NAME_FECHA + " TEXT )" ;


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + tablaResultado.TABLE_NAME;

    public RepositorioResultados(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public long count() {
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, tablaResultado.TABLE_NAME);
    }

    public long add(Resultado resultado) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(tablaResultado.COL_NAME_JUGADOR, resultado.getJugador());
        values.put(tablaResultado.COL_NAME_FICHAS, resultado.getFichasRestantes());
        values.put(tablaResultado.COL_NAME_DURACION, resultado.getDuracionPartida());
        values.put(tablaResultado.COL_NAME_FECHA, resultado.getFecha());
        return db.insert(tablaResultado.TABLE_NAME, null, values);
    }

    public List<Resultado> getAll(){
        List<Resultado> listaResultados = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(tablaResultado.TABLE_NAME,null,null,null,null,
                null,tablaResultado.COL_NAME_FICHAS + "," + tablaResultado.COL_NAME_DURACION);
        while (c.moveToNext()){
            listaResultados.add(cursor2Resultado(c));
        }
        c.close();
        db.close();
        return listaResultados;
    }

    public Resultado cursor2Resultado(@NonNull Cursor c){
        return new Resultado(
                c.getInt(c.getColumnIndex(tablaResultado.COL_NAME_ID)),
                c.getString(c.getColumnIndex(tablaResultado.COL_NAME_JUGADOR)),
                c.getInt(c.getColumnIndex(tablaResultado.COL_NAME_FICHAS)),
                c.getString(c.getColumnIndex(tablaResultado.COL_NAME_DURACION)),
                c.getString(c.getColumnIndex(tablaResultado.COL_NAME_FECHA))
        );
    }

    public void deleteResults(){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
