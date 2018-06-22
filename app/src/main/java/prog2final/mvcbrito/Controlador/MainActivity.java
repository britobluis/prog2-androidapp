package prog2final.mvcbrito.Controlador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import prog2final.mvcbrito.Modelo.Automovil;
import prog2final.mvcbrito.R;
import prog2final.mvcbrito.Datos.AutomovilDB;

public class MainActivity extends AppCompatActivity {
    private ListView lista;
    private TextView listaVacia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se asignan los elementos de la vista
        listaVacia=findViewById(R.id.listaVacia);
        listaVacia.setVisibility(View.INVISIBLE);
        lista=findViewById(R.id.lvAutomoviles);

        //Se invoca el metodo para poblar la lista
        llenarLista(lista,listaVacia);

        final AutomovilDB automovilDB =new AutomovilDB(this);
        //Guardar inicial
        final EditText editPlaca= findViewById(R.id.txtPlaca);
        final EditText editMarca=findViewById(R.id.txtMarca);
        final EditText editModelo =findViewById(R.id.txtModelo);
        final Button guardarBtn=findViewById(R.id.btnGuardar);

        //Se asignan los elementos de la vista
        final EditText etPlaca=findViewById(R.id.txtPlaca);
        final EditText etMarca=findViewById(R.id.txtMarca);
        final EditText etModelo=findViewById(R.id.txtModelo);
        Button btEliminar=findViewById(R.id.btnEliminar);
        Button btActualizar=findViewById(R.id.btnActualizar);
        //etPlaca.setEnabled(false);

        //Se toman los mensajes enviados en el intent
        final int placa=getIntent().getIntExtra("Placa",0);
        String nombre=getIntent().getStringExtra("Marca");
        String modelo=getIntent().getStringExtra("Modelo");

        //Se crea un onclicklistener en comun para los dos botones
        View.OnClickListener buttonListener= new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutomovilDB aDB3 = new AutomovilDB(getApplicationContext());
                switch (v.getId()){
                    case R.id.btnEliminar:
                        int eliminar= aDB3.eliminarAutomovil(String.valueOf(placa));
                        if(eliminar != -1){
                            Toast.makeText(MainActivity.this, "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show();
                          //  onBackPressed();
                        }
                        break;
                    case R.id.btnActualizar:
                        int cod=Integer.parseInt(etPlaca.getText().toString());
                        String nomb=etMarca.getText().toString();
                        String prec;
                        if(etModelo.getText().toString().equals("")){
                            prec="0";
                        }else {
                            prec=etModelo.getText().toString();
                        }

                        int modificar= aDB3.modificarAutomovil(new Automovil(cod,nomb,prec));
                        if (modificar != -1){

                            Toast.makeText(MainActivity.this, "Producto modificado exitosamente", Toast.LENGTH_SHORT).show();
                          //  onBackPressed();
                        }else{
                            Toast.makeText(MainActivity.this, "No se guardaron los cambios", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.btnGuardar:
                        int placaAutomovil= Integer.parseInt(editPlaca.getText().toString());
                        String marca=editMarca.getText().toString();
                        String modelo=editModelo.getText().toString();

                        long verificador= automovilDB.nuevoAutomovil(new Automovil(placaAutomovil,marca,modelo));
                        llenarLista(lista,listaVacia);
                        break;
                }

            }
        };

        //Se le asigna el onclicklistener creado arriba a los dos botones
        btEliminar.setOnClickListener(buttonListener);
        btActualizar.setOnClickListener(buttonListener);
        guardarBtn.setOnClickListener(buttonListener);//Se asigna click listener al boton Guardar

        //Se asignan los datos obtenidos desde el activity anterior a los editTexts
        etPlaca.setText(String.valueOf(placa));
        etMarca.setText(nombre);
        //etModelo.setText(String.valueOf(modelo));




    }
    public void llenarLista(ListView lista, TextView textView){
        AutomovilDB aDB2 =new AutomovilDB(this);
        ArrayList<Automovil> automoviles= aDB2.MostrarAutomoviles();
        System.out.println(automoviles.get(0).getmodelo());

        if (! automoviles.isEmpty()){
            textView.setVisibility(View.INVISIBLE);//Si hay producto en la bd,hace que el textView que indica lo contrario,no se muestre
        }else{
            textView.setVisibility(View.VISIBLE);//Si no hay productos cargados,muestra el textView
        }


        final AutomovilAdaptador adaptador =new AutomovilAdaptador(this,automoviles);

        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {/*Seteo de un listener para cuando se clickea
        en cada item de la lista*/
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              /*  Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                Automovil a1 = adaptador.getItem(position);//Se crea un objeto a partir del item seleccionado
                //Y se envian los datos a la activity Modificar
                intent.putExtra("Placa",a1.getPlacaAutomovil());
                intent.putExtra("Marca",a1.getmarca());
                intent.putExtra("Modelo",a1.getmodelo());
                //Inicia la activity modificar
                startActivity(intent);*/
            }
        });
    }
}
