package es.upm.miw.solitarioCelta.models;

import android.provider.BaseColumns;

public class ResultadoContract {
    private ResultadoContract(){
    }

    public static abstract class tablaResultado implements BaseColumns {
        static final String TABLE_NAME="resultados";

        static final String COL_NAME_ID = _ID;
        static final String COL_NAME_JUGADOR = "jugador";
        static final String COL_NAME_FICHAS ="fichas_restantes";
        static final String COL_NAME_DURACION="duracion";
        static final String COL_NAME_FECHA="fecha";

    }
}
