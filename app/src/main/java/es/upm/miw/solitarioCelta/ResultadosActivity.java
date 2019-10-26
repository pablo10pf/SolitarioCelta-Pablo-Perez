package es.upm.miw.solitarioCelta;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import es.upm.miw.solitarioCelta.models.RepositorioResultados;
import es.upm.miw.solitarioCelta.views.ResultadoAdapter;

public class ResultadosActivity extends AppCompatActivity {

    RepositorioResultados repositorioResultados;
    ListView lvResultados;
    ResultadoAdapter resultadoAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_layout);
        repositorioResultados = new RepositorioResultados(this);
        lvResultados = (ListView) findViewById(R.id.lvResultados);
        resultadoAdapter= new ResultadoAdapter(
                getApplicationContext(),R.layout.item_resultado,repositorioResultados.getAll());
        lvResultados.setAdapter(resultadoAdapter);
        FloatingActionButton fab = findViewById(R.id.fabDeleteResults);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteResultsFragment().show(getSupportFragmentManager(),"DELETE_RESULTS_DIALOG");
            }
        });
    }

}