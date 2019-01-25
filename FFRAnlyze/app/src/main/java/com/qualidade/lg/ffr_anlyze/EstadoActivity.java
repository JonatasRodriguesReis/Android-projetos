package com.qualidade.lg.ffr_anlyze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import model.FFR_Estado;

public class EstadoActivity extends AppCompatActivity {

    String anoAtual;
    String anoAnterior;
    String mes;
    String cor;
    FFR_Estado ffr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado);
    }
}
