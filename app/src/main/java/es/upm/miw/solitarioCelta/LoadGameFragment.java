package es.upm.miw.solitarioCelta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;

public class LoadGameFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(main);
        builder
                .setTitle(R.string.txtDialogoRecuperarPartidaTitulo)
                .setMessage(R.string.txtDialogoRecuperarPartidaPregunta)
                .setPositiveButton(
                        getString(R.string.txtDialogoAfirmativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                main.miJuego.deserializaTablero(main.recuperarPartidaFichero());
                                main.mostrarTablero();
                                Snackbar.make(
                                        getActivity().findViewById(android.R.id.content),
                                        getString(R.string.txtPartidaRecuperada),
                                        Snackbar.LENGTH_LONG
                                ).show();
                            }
                        }
                )
                .setNegativeButton(
                        getString(R.string.txtDialogoNegativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //empty. Not operation
                            }
                        }
                ).setIcon(android.R.drawable.ic_menu_revert);

        return builder.create();
    }
}
