package mx.edu.tesoem.isc.p2.sog.m0024;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import Almacen.Archivos;
import Informacion.Datos;

public class principal extends AppCompatActivity {

    EditText matricula, nombre, edad, semestre, promedio, estado;
    List<String> informacion = new ArrayList<String>();
    ArrayList<String> contenidoGridView = new ArrayList<String>();
    GridView gvDatos;

    String enviar = "Seleccionar persona";
    String index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        matricula = findViewById(R.id.matricula);
        nombre = findViewById(R.id.nombre);
        edad = findViewById(R.id.edad);
        semestre = findViewById(R.id.semestre);
        promedio = findViewById(R.id.promedio);
        estado = findViewById(R.id.estado);
        gvDatos = findViewById(R.id.gridDatos);

        Archivos archivos = new Archivos();

        if (archivos.VerificaArchivo(this)){
            if (archivos.Leer_info(this)){
                informacion = archivos.Reg_informacion();

                convierteArreglo(informacion);
                //Grid view necesita un ListAdapter, asi que convertimos el contenido a un ArrayAdapter del tipo String
                ArrayAdapter<String> arreglo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,contenidoGridView);
                gvDatos.setAdapter(arreglo);

            }else{
                Toast.makeText(this,"No existe informacion a cargar", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"No existe informacion a cargar", Toast.LENGTH_LONG).show();
        }
        gvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {

                interpreta todo como un array de corrido */
                if ( (posicion>0) && ((posicion%7)== 0) ){
                    int linea = posicion/7;

                    Log.i("Fila", "Valor lista: " + informacion.get(linea-1));
                    Toast.makeText(principal.this,"linea "+linea, Toast.LENGTH_LONG).show();


                    Gson gson = new Gson();
                    Datos datos = gson.fromJson(informacion.get(linea-1), Datos.class);


                    enviar = informacion.get(linea-1);
                    index = String.valueOf(linea-1);

                    matricula.setText(datos.getMatricula().toString());
                    nombre.setText(datos.getNombre().toString());
                    edad.setText(String.valueOf(datos.getEdad()));
                    semestre.setText(datos.getSemestre().toString());
                    promedio.setText(String.valueOf(datos.getPromedio()));
                    estado.setText(datos.getEstado().toString());
                }
            }
        });

    }