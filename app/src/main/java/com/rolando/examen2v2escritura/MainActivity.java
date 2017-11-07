package com.rolando.examen2v2escritura;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText dato1,dato2,dato3,dato4,dato5,ejem;
    Button guardar,hola;
    String cargar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dato1 = (EditText) findViewById(R.id.editText);
        dato2 = (EditText) findViewById(R.id.editText2);
        dato3 = (EditText) findViewById(R.id.editText3);
        dato4 = (EditText) findViewById(R.id.editText4);
        dato5 = (EditText) findViewById(R.id.editText5);
        guardar = (Button) findViewById(R.id.button);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargar = "Nombre: "+dato5.getText()+" Producto: "+dato1.getText()+" Proveedor: "+dato2.getText()+" Producto Existente: "+dato3.getText()+" Produto Pedido: "+dato4.getText()+" ";
                Toast.makeText(MainActivity.this, "Se Guardo Pedido", Toast.LENGTH_SHORT).show();
                guardarArchivo();

            }
        });

    }


    public void guardarArchivo() {
        try {
            File archivo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"archivo.txt");
            if(hasExternalStorage()) {
                //no sabia si se tenia que sobre escribir asi que le puse que fuera false para que se sobre escribiera
                //si se tiene que sobre escribir solo se cambia a true  esto
                FileOutputStream stream = new FileOutputStream(archivo, false);
                OutputStreamWriter escritor = new OutputStreamWriter(stream);
                escritor.write(cargar);
                escritor.close();
            }
        }catch (Exception e){

        }

    }

    public void cargarDesdeSD(){
        try {

            File archivo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),"archivo.txt");
            if(archivo.exists()){
                Toast.makeText(MainActivity.this, "si encontre el archivo", Toast.LENGTH_SHORT).show();
            }
            if(hasExternalStorage()&&archivo.exists()) {

                FileInputStream stream = new FileInputStream(archivo);
                InputStreamReader lector = new InputStreamReader(stream);
                BufferedReader contenido = new BufferedReader(lector);
                String linea, mensaje= "";
                while ((linea = contenido.readLine()) != null){
                    mensaje += linea;

                }
                lector.close();
                dato1.setText(mensaje);
            }
        }catch (Exception e){

        }
    }

    public boolean hasExternalStorage(){
        String status= Environment.getExternalStorageState();
        if(status.equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(MainActivity.this, "Si tienes sd", Toast.LENGTH_SHORT).show();
            return true;

        }
        return false;
    }
}
