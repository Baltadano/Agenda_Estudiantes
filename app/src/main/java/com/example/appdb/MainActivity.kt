package com.example.appdb

import android.content.ContentValues
import android.os.Bundle
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
//import android.view.Menu
//import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setSupportActionBar(findViewById(R.id.toolbar))

        val control = findViewById<EditText>(R.id.txtControl)
        val nombre = findViewById<EditText>(R.id.txtNombre)
        val carrera = findViewById<EditText>(R.id.txtCarrera)
        val semestre = findViewById<EditText>(R.id.txtSemestre)
        val grupo = findViewById<EditText>(R.id.txtGrupo)

        //findViewById<EditText>(R.id.txtCarrera)////////linea incomprendida

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnEliminar = findViewById<Button>(R.id.btnEliminar)
        val btnModificar = findViewById<Button>(R.id.btnModificar)
        val btnConsultar = findViewById<Button>(R.id.btnConsultar)

        btnRegistrar.setOnClickListener{
            //abrir la base de datos para escritura y lectura
            val objDBHelper = DBHelper(this, "agenda",null,1)
            val bd = objDBHelper.writableDatabase
            //abrir un obj para llenarlo de datos
            val registro = ContentValues()

            registro.put("control",Integer.parseInt(control.text.toString()))
            registro.put("nombre",nombre.text.toString())
            registro.put("carrera",carrera.text.toString())
            registro.put("semestre",semestre.text.toString())//cambio 1
            registro.put("grupo",grupo.text.toString())//cambio 2
            //registrar los datos
            bd.insert("estudiantes", null, registro)
            //cerrar la bd
            bd.close()
            //
            //limpiar los campos
            control.setText("")
            nombre.setText("")
            carrera.setText("")
            semestre.setText("");
            grupo.setText("");

            Toast.makeText(this, "Registardo!", Toast.LENGTH_LONG).show()
        //15152828 segun si registro
            //12125454
            //14149898
            //65658484

            //12128989
            // 32326565

        }
        btnConsultar.setOnClickListener(){

            val objDBHelper = DBHelper(this, "agenda", null, 1)
            //Abrir la base de datos para escritura y lectura
            val bd = objDBHelper.writableDatabase
            // reg es un objeto tipo arreglo que me permite almacenar los datos de la consulta
            val reg = bd.rawQuery("SELECT nombre, carrera, semestre, grupo from estudiantes where control=${Integer.parseInt(control.text.toString())}",null)//cambio 3
            if (reg.moveToFirst()){
                nombre.setText(reg.getString(0))
                carrera.setText(reg.getString(1))
                semestre.setText(reg.getString(2))//cambio 3.5
                grupo.setText(reg.getString(3))             //cambio 4

            }
            else
            {
                Toast.makeText(this, "El registro no existe", Toast.LENGTH_LONG).show()
            }
            bd.close();

        }
        btnEliminar.setOnClickListener(){

            val objDBHelper = DBHelper(this, "agenda", null, 1)
            //Abrir la base de datos para escritura y lectura
            val bd = objDBHelper.writableDatabase
            // reg es un objeto tipo arreglo que me permite almacenar los datos de la consulta
            val reg = bd.rawQuery("DELETE from estudiantes where control=${Integer.parseInt(control.text.toString())}",null)
            if (reg.moveToFirst()){
                nombre.setText(reg.getString(0))
                carrera.setText(reg.getString(1))
                semestre.setText(reg.getString(2))//cambio 5
                grupo.setText(reg.getString(3))//cambio 6
            }
            else
            {
                Toast.makeText(this, "El registro no existe", Toast.LENGTH_LONG).show()
            }
            bd.close();
        }

        btnModificar.setOnClickListener(){

            try {
                var cont =Integer.parseInt(control.text.toString())
                var nom=nombre.text.toString()

                var sem= semestre.text.toString()//cambio
                var gru=grupo .text.toString()//cambio

                if(nom.length==0 || carrera.length()==0 || sem.length==0 || gru.length==0){
                    Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_LONG).show()
                }else{

                    val objDBHelper = DBHelper(this, "agenda", null, 1)

                    //Abrir la base de datos para escritura y lectura
                    val bd = objDBHelper.writableDatabase

                    //crear un objeto para llenarlo de datos (Campos)
                    val registro = ContentValues()
                    registro.put("control", cont)
                    registro.put("nombre", nombre.text.toString())
                    registro.put("carrera", carrera.text.toString())
                    registro.put("semestre",semestre.text.toString())//cambio
                    registro.put("grupo",grupo.text.toString())//cambio


                    try {
                        //Registrar los datos (registro)
                        bd.update("estudiantes",registro,"control='$cont'",null)
                        Toast.makeText(this, "Registro Modificado", Toast.LENGTH_LONG).show()
                    }catch (e:Exception){
                        Toast.makeText(this, "No se pudo modificar el registro", Toast.LENGTH_LONG).show()
                    }

                    //Cerrar la base de datos
                    bd.close()

                    //limpiar cajas de texto
                    control.setText("")
                    nombre.setText("")
                    carrera.setText("")
                    semestre.setText("")//cambio
                    grupo.setText("")//cambio

                    control.requestFocus()
                }
            }catch (e:Exception){
                Toast.makeText(this, "Ingresa el numero de control", Toast.LENGTH_LONG).show()
                control.requestFocus();
            }
        }
      }
    }
