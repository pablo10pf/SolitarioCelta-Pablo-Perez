package es.upm.miw.solitarioCelta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;

public class DeleteResultsFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final ResultadosActivity activity = (ResultadosActivity) getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder
                .setTitle(R.string.txtDialogoBorrarResultadosTitulo)
                .setMessage(R.string.txtDialogoBorrarResultadosPregunta)
                .setPositiveButton(
                        getString(R.string.txtDialogoAfirmativo),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                activity.repositorioResultados.deleteResults();
                                activity.resultadoAdapter.clear();
                                Snackbar.make(
                                        getActivity().findViewById(android.R.id.content),
                                        getString(R.string.txtDeleteResultados),
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
                ).setIcon(android.R.drawable.ic_menu_delete);

        return builder.create();
    }
}