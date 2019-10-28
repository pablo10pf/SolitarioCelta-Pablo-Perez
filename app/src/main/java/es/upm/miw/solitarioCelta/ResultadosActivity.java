package es.upm.miw.solitarioCelta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import es.upm.miw.solitarioCelta.models.RepositorioResultados;
import es.upm.miw.solitarioCelta.models.Resultado;
import es.upm.miw.solitarioCelta.views.ResultadoAdapter;

public class ResultadosActivity extends AppCompatActivity {

    RepositorioResultados repositorioResultados;
    ListView lvResultados;
    ResultadoAdapter resultadoAdapter;
    EditText txtResultadosJugador;
    Button btResultadosJugador;
    Button btTodosResultados;
    ImageButton btBorrarResultados;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultados_layout);
        repositorioResultados = new RepositorioResultados(this);
        txtResultadosJugador = (EditText) findViewById(R.id.txtResultadosJugador);
        btResultadosJugador = (Button) findViewById(R.id.btResultadosJugador);
        btTodosResultados = (Button) findViewById(R.id.btResultadosAll);
        btBorrarResultados = (ImageButton) findViewById(R.id.btBorrarResultados);
        lvResultados = (ListView) findViewById(R.id.lvResultados);

        resultadoAdapter = new ResultadoAdapter(
                getApplicationContext(), R.layout.item_resultado, repositorioResultados.getAll());
        lvResultados.setAdapter(resultadoAdapter);

        btResultadosJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Resultado> listaResultados = repositorioResultados.getAllJugador(txtResultadosJugador.getText().toString());
                if (listaResultados.isEmpty()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.noResultsForPlayer), Toast.LENGTH_SHORT).show();
                } else {
                    resultadoAdapter.clear();
                    resultadoAdapter.addAll(listaResultados);
                    txtResultadosJugador.getText().clear();
                }
            }
        });


        btTodosResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Resultado> listaResultados = repositorioResultados.getAll();
                if (listaResultados.isEmpty()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.noResults), Toast.LENGTH_SHORT).show();
                } else {
                    resultadoAdapter.clear();
                    resultadoAdapter.addAll(listaResultados);
                    txtResultadosJugador.getText().clear();
                }
            }
        });


        btBorrarResultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DeleteResultsFragment().show(getSupportFragmentManager(), "DELETE_RESULTS_DIALOG");
            }
        });
    }

}