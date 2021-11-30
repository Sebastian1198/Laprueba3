package com.csto.laprueba3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView tBlanco,tNulo,tBoric,tKast;
    Button bVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tBlanco= (TextView) findViewById(R.id.txtBlancos) ;
        tNulo= (TextView) findViewById(R.id.txtNulos) ;
        tBoric= (TextView) findViewById(R.id.txtBoric) ;
        tKast= (TextView) findViewById(R.id.txtKast) ;
        bVolver=(Button) findViewById(R.id.btnVolver2);
        Integer Blanco=0,Nulo=0,Boric=0,Kast=0;

        SQLiteDatabase db;
        DbHelper conn = new DbHelper(getApplicationContext());
        db= conn.getReadableDatabase();
        Cursor C =db.query("votos",null,null,null,null,null,null);
        if(C!=null)
        {
            if(C.moveToFirst())
            {
                do{
                    if(C.getString(2).equals("H"))
                    {
                        Nulo++; //incrementar el contador total
                    }
                    if(C.getString(2).equals("H"))
                    {
                        Blanco++; //incrementar el contador total
                    }
                    if(C.getString(2).equals("H"))
                    {
                        Boric++; //incrementar el contador total
                    }
                    else
                    {
                        Kast++; //incrementar el contador total

                    }
                }
                while(C.moveToNext());
            }

        }
        tBlanco.setText(""+Blanco);
        tNulo.setText(""+Nulo);
        tBoric.setText(""+Boric);
        tKast.setText(""+Kast);

        bVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent I = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(I);

            }
        });
    }
}