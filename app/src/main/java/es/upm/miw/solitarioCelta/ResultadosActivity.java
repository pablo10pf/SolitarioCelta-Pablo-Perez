package es.upm.miw.solitarioCelta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import es.upm.miw.solitarioCelta.models.RepositorioResultados;
import es.upm.miw.solitarioCelta.views.ResultadoAdapter;

public class ResultadosActivity extends AppCompatActivity {

    RepositorioResultados repositorioResultados;
    ListView lvResultados;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_layout);
        repositorioResultados = new RepositorioResultados(this);
        lvResultados = (ListView) findViewById(R.id.lvResultados);
        final ResultadoAdapter resultadoAdapter= new ResultadoAdapter(
                getApplicationContext(),R.layout.item_resultado,repositorioResultados.getAll());
        lvResultados.setAdapter(resultadoAdapter);
    }

}