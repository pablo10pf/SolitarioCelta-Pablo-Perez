package es.upm.miw.solitarioCelta.views;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import es.upm.miw.solitarioCelta.databinding.ItemResultadoBinding;
import es.upm.miw.solitarioCelta.models.Resultado;

public class ResultadoAdapter extends ArrayAdapter {

    private Context context;
    private int idLayout;
    private List<Resultado> resultados;
    private static LayoutInflater layoutInflater = null;


    public ResultadoAdapter(@NonNull Context context, int resource, @NonNull List<Resultado> resultados) {
        super(context, resource,resultados);
        this.context=context;
        this.idLayout=resource;
        this.resultados= resultados;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent){
        if(layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }
        ItemResultadoBinding itemResultadoBinding = DataBindingUtil.getBinding(view);
        if(itemResultadoBinding == null){
            itemResultadoBinding = DataBindingUtil.inflate(layoutInflater,idLayout,parent,false);
        }
        itemResultadoBinding.setResultado(resultados.get(position));
        itemResultadoBinding.executePendingBindings();

        return itemResultadoBinding.getRoot();
    }
}