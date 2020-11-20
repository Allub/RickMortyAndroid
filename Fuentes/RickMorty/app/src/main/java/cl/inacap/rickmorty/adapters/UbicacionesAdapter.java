package cl.inacap.rickmorty.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

import cl.inacap.rickmorty.R;
import cl.inacap.rickmorty.dto.Ubicacion;

public class UbicacionesAdapter extends ArrayAdapter<Ubicacion> {
    private List<Ubicacion>ubicaciones;
    private Activity activity;
    public UbicacionesAdapter(@NonNull Activity context, int resource, @NonNull List<Ubicacion> objects) {
        super(context, resource, objects);
        this.ubicaciones = objects;
        this.activity = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_ubicaciones,null,true);
        TextView nombreTxt = rowView.findViewById(R.id.nombre_txt_ubi);
        TextView tipoTxt = rowView.findViewById(R.id.tipo_txt_ubi);
        TextView dimenTxt = rowView.findViewById(R.id.dimensiones_txt_ubi);
        nombreTxt.setText(ubicaciones.get(position).getName());
        tipoTxt.setText(ubicaciones.get(position).getType());
        dimenTxt.setText(ubicaciones.get(position).getDimension());
        return rowView;
    }
}
