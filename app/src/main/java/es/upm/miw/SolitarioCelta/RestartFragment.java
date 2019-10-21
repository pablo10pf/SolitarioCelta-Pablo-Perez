package es.upm.miw.SolitarioCelta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class RestartFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MainActivity main = (MainActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(main);
        builder
                .setTitle(R.string.txtDialogoRestartTitulo)
                .setMessage(R.string.txtDialogoRestartPregunta)
                .setPositiveButton(
                        getString(R.string.txtDialogoRestartAfirmativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                main.miJuego.reiniciar();
                                main.mostrarTablero();
                            }
                        }
                )
                .setNegativeButton(
                        getString(R.string.txtDialogoRestartNegativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //empty. Not operation
                            }
                        }
                ).setIcon(R.drawable.restart);

        return builder.create();
    }
}
