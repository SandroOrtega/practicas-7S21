package mx.edu.tesoem.isc.p2.sog.m0076;

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

    String enviar = "Selecciona una persona";
    String index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        matricula = findViewById(R.id.txt_matricula);
        nombre = findViewById(R.id.txt_nombre);
        edad = findViewById(R.id.txt_edad);
        semestre = findViewById(R.id.txt_semestre);
        promedio = findViewById(R.id.txt_promedio);
        estado = findViewById(R.id.txt_estado);
        gvDatos = findViewById(R.id.gridDatos);

        Archivos archivos = new Archivos();

        if (archivos.VerificaArchivo(this)){
            if (archivos.Leer_info(this)){
                informacion = archivos.Reg_informacion();

                convierteArreglo(informacion);

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

    public void btn_agregar(View view){
        Intent agregar = new Intent(this, mx.edu.tesoem.isc.p2.sog.m0076.agregar.class);
        startActivity(agregar);
    }
    public void btn_modificar(View view){
        if (enviar != "Selecciona una persona"){
            Intent actualizar = new Intent(this, mx.edu.tesoem.isc.p2.sog.m0076.actualizar.class);
            actualizar.putExtra("seleccion", enviar);
            actualizar.putExtra("index", index);
            startActivity(actualizar);
        } else {
            Toast.makeText(this,enviar,Toast.LENGTH_LONG).show();
        }
    }
    public void btn_eliminar(View view){
        if (enviar != "Selecciona una persona"){
            Intent eliminar = new Intent(this, mx.edu.tesoem.isc.p2.sog.m0076.eliminar.class);
            eliminar.putExtra("seleccion", enviar);
            eliminar.putExtra("index", index);
            startActivity(eliminar);
        } else {
            Toast.makeText(this,enviar,Toast.LENGTH_LONG).show();
        }
    }

    private void convierteArreglo(List<String> contenido){
        int id = 1;
        String estado;

        contenidoGridView.add("Id");
        contenidoGridView.add("Matricula");
        contenidoGridView.add("Nombre");
        contenidoGridView.add("Edad");
        contenidoGridView.add("Semestre");
        contenidoGridView.add("Promedio");
        contenidoGridView.add("Estado");

        for (String elemento : contenido){
            Gson gson = new Gson();
            Datos dato = gson.fromJson(elemento, Datos.class);

            contenidoGridView.add(String.valueOf(id));
            contenidoGridView.add(dato.getMatricula());
            contenidoGridView.add(dato.getNombre());
            contenidoGridView.add(String.valueOf(dato.getEdad()));
            contenidoGridView.add(dato.getSemestre());
            contenidoGridView.add(String.valueOf(dato.getPromedio()));

            if (dato.getPromedio() >= 70){
                estado = "Acreditado";
            }else{
                estado = "No acreditado";
            }
            contenidoGridView.add(estado);

            id = id+1;
        }

    }

}