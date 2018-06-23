package prog2final.mvcbrito.Controlador;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import prog2final.mvcbrito.Modelo.Automovil;
import prog2final.mvcbrito.R;

import java.util.ArrayList;

public class AutomovilAdaptador extends BaseAdapter{
    /*En esta clase,se crea un adaptador para cada item de la lista,
     * utilizando el layout item_lista.xml*/

    private Activity activity;
    private ArrayList<Automovil> automoviles;

    public AutomovilAdaptador(Activity activity, ArrayList<Automovil> automoviles) {
        this.activity = activity;
        this.automoviles = automoviles;
    }

    @Override
    public int getCount() {
        return automoviles.size();
    }

    @Override
    public Automovil getItem(int position) {
        return automoviles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista=convertView;
        if (convertView == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista=inf.inflate(R.layout.item_lista,null);
        }
        Automovil auto=automoviles.get(position);
        TextView placa=vista.findViewById(R.id.txtNombre);
        TextView marca=vista.findViewById(R.id.txtCodigo);
        TextView modelo=vista.findViewById(R.id.txtPrecio);

        placa.setText("Placa: "+String.valueOf(auto.getPlacaAutomovil()));
        marca.setText("Marca: "+String.valueOf(auto.getmarca()));
        modelo.setText("Modelo: "+String.valueOf(auto.getmodelo()) );
        return vista;
}
}
