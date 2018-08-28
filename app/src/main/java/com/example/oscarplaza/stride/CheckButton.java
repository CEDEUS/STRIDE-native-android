package com.example.oscarplaza.stride;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.oscarplaza.stride.Utils.Utils;
import com.google.gson.Gson;

public class CheckButton extends AppCompatActivity {
    private String genero;
    private int edad;
    private String  ability;

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Crear un Observado");
        process();

        findViewById(R.id.sucsessess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process();
                //Toast.makeText(getApplicationContext(),getAbility()+" "+getEdad()+" "+getGenero(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Semaforo.class);
                intent.putExtra("sex", getGenero());
                intent.putExtra("etario",getEdad());
                intent.putExtra("ability",getAbility());

                startActivity(intent);
            }
        });



    }

    private void process() {
        RadioGroup rgenero = (RadioGroup)findViewById(R.id.radiog);
        RadioGroup retario = (RadioGroup)findViewById(R.id.radioge);
        RadioGroup ability = (RadioGroup)findViewById(R.id.radiops);
        RadioButton rb=(RadioButton)findViewById(rgenero.getCheckedRadioButtonId());
        Utils u = new Utils();

        setGenero(u.GetIdGeneto(rb.getText().toString(),this));
        rb=(RadioButton)findViewById(retario.getCheckedRadioButtonId());
        setEdad(u.getIdEdad(rb.getText().toString(),this));
        rb=(RadioButton)findViewById(ability.getCheckedRadioButtonId());
        setAbility(u.getIdAbility(rb.getText().toString(),this).toString());
    }

}
