package com.csto.laprueba3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rVotos;
    RadioButton Nulo,Boric,Kast;
    Button bVotar,bVer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bVotar=(Button) findViewById(R.id.btnVotar);
        bVer=(Button) findViewById(R.id.btnResultados);
        rVotos=(RadioGroup) findViewById(R.id.rgVotos);
        Nulo=(RadioButton) findViewById(R.id.rbNulo);
        Boric=(RadioButton) findViewById(R.id.rbBoric);
        Kast=(RadioButton) findViewById(R.id.rbKast);
        ContentValues bd = new ContentValues();
        bVotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Nulo.isChecked()==false || Boric.isChecked()==false || Kast.isChecked()==false){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Boto Blanco?")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    SQLiteDatabase db;
                                    DbHelper conn = new DbHelper(getApplicationContext());
                                    db = conn.getReadableDatabase();
                                    db.insert("votos",null,bd);
                                    Intent I = new Intent(getApplicationContext(),MainActivity2.class);
                                    startActivity(I);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    builder.create();
                    builder.show();
                }
                SQLiteDatabase db;
                DbHelper conn = new DbHelper(getApplicationContext());
                db= conn.getWritableDatabase();
                if(Nulo.isChecked()==true){
                    bd.put("rb_nulo",Nulo.getText().toString());
                    db.insert("voto",null,bd);
                    Toast.makeText(getApplicationContext(),"Guardado", Toast.LENGTH_LONG).show();
                    Intent I = new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(I);
                }
                if(Boric.isChecked()==true){

                    bd.put("rb_boric",Boric.getText().toString());
                    db.insert("voto",null,bd);
                    Toast.makeText(getApplicationContext(),"Guardado", Toast.LENGTH_LONG).show();
                    Intent I = new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(I);
                }
                if(Kast.isChecked()==true){
                    bd.put("rb_kast",Kast.getText().toString());
                    db.insert("voto",null,bd);
                    Toast.makeText(getApplicationContext(),"Guardado", Toast.LENGTH_LONG).show();
                    Intent I = new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(I);
                }
            }
        });
        bVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(getApplicationContext(),MainActivity2.class);
                I.putExtra("Blanco","0");
                I.putExtra("Nulo","0");
                I.putExtra("Boric","0");
                I.putExtra("Kast","0");
                startActivity(I);
            }
        });
    }
}