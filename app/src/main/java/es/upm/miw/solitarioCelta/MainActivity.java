package es.upm.miw.solitarioCelta;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import es.upm.miw.solitarioCelta.models.RepositorioResultados;
import es.upm.miw.solitarioCelta.models.Resultado;

public class MainActivity extends AppCompatActivity {


    SCeltaViewModel miJuego;
    public final String LOG_KEY = "MiW";
    private TextView fichasRestantes;
    RepositorioResultados repositorioResultados;
    SharedPreferences sharedPrefs;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repositorioResultados = new RepositorioResultados(this);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        fichasRestantes = (TextView) findViewById(R.id.txtFichasValor);
        miJuego = ViewModelProviders.of(this).get(SCeltaViewModel.class);
        mostrarTablero();
    }

    /**
     * Se ejecuta al pulsar una ficha
     * Las coordenadas (i, j) se obtienen a partir del nombre del recurso, ya que el botón
     * tiene un identificador en formato pXY, donde X es la fila e Y la columna
     *
     * @param v Vista de la ficha pulsada
     */
    public void fichaPulsada(@NotNull View v) {
        String resourceName = getResources().getResourceEntryName(v.getId());
        int i = resourceName.charAt(1) - '0';   // fila
        int j = resourceName.charAt(2) - '0';   // columna

        Log.i(LOG_KEY, "fichaPulsada(" + i + ", " + j + ") - " + resourceName);
        miJuego.jugar(i, j);
        Log.i(LOG_KEY, "#fichas=" + miJuego.numeroFichas());

        mostrarTablero();
        if (miJuego.juegoTerminado()) {
            // TODO guardar puntuación
            repositorioResultados.add(crearPartidaGuardar());
            new AlertDialogFragment().show(getFragmentManager(), "ALERT_DIALOG");
        }
    }

    /**
     * Visualiza el tablero
     */
    public void mostrarTablero() {
        RadioButton button;
        String strRId;
        String prefijoIdentificador = getPackageName() + ":id/p"; // formato: package:type/entry
        int idBoton;

        for (int i = 0; i < JuegoCelta.TAMANIO; i++)
            for (int j = 0; j < JuegoCelta.TAMANIO; j++) {
                strRId = prefijoIdentificador + i + j;
                idBoton = getResources().getIdentifier(strRId, null, null);
                if (idBoton != 0) { // existe el recurso identificador del botón
                    button = findViewById(idBoton);
                    button.setChecked(miJuego.obtenerFicha(i, j) == JuegoCelta.FICHA);
                }
            }
        actualizarContadorFichas();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.opciones_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcAjustes:
                startActivity(new Intent(this, SCeltaPrefs.class));
                return true;
            case R.id.opcAcercaDe:
                startActivity(new Intent(this, AcercaDe.class));
                return true;
            case R.id.opcReiniciarPartida:
                new RestartFragment().show(getSupportFragmentManager(), "RESTART_DIALOG");
                return true;
            case R.id.opcGuardarPartida:
                new SaveGameFragment().show(getSupportFragmentManager(),"SAVE_GAME_DIALOG");
                return true;
            case R.id.opcRecuperarPartida:
                new LoadGameFragment().show(getSupportFragmentManager(),"LOAD_GAME_DIALOG");
                return true;
            case R.id.opcMejoresResultados:
                Intent intent = new Intent(getApplicationContext(),ResultadosActivity.class);
                startActivity(intent);
                return true;
            // TODO!!! resto opciones

            default:
                Snackbar.make(
                        findViewById(android.R.id.content),
                        getString(R.string.txtSinImplementar),
                        Snackbar.LENGTH_LONG
                ).show();
        }
        return true;
    }

    public void guardarPartidaFichero() {
        FileOutputStream fos =
                null;

        try {
            fos = openFileOutput("partida_guardada.txt", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            fos.write(miJuego.serializaTablero().getBytes());
            fos.write('\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String recuperarPartidaFichero() {
        StringBuffer sb = new StringBuffer();
        BufferedReader fin = null;
        try {
            fin = new BufferedReader(
                    new InputStreamReader(openFileInput("partida_guardada.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String linea;
        try {
            linea = fin.readLine();
            while (linea != null) {
                sb.append(linea + '\n');
                linea = fin.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void actualizarContadorFichas(){
        fichasRestantes.setText(String.valueOf(miJuego.numeroFichas()));
    }

    public Resultado crearPartidaGuardar(){
        Resultado resultado = new Resultado();
        resultado.setJugador(sharedPrefs.getString("nombreJugador","Pablo"));
        resultado.setFichasRestantes(miJuego.numeroFichas());
        resultado.setDuracionPartida("03:15");
        resultado.setFecha(obtenerStringFechaPartida());
        return resultado;
    }
    public String obtenerStringFechaPartida(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return dateFormat.format(date);
    }
}
