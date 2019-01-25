package com.qualidade.lg.ffr_anlyze;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.support.v7.app.AlertDialog;
import android. support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.SwitchCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Data;
import model.FFR;
import model.FFR_Estado;

import static com.qualidade.lg.ffr_anlyze.R.drawable.botaoestadoamarelo;

public class MainActivity extends AppCompatActivity {
    List<String> listaAno;
    Spinner spn_lista_Ano;
    List<String> listaMes;
    Spinner spn_lista_Mes;
    List<String> listaDia;
    Spinner spnDia;
    Button btnAM;
    Button btnRN;
    Button btnPA;
    Button btnRR;
    Button btnRO;
    Button btnAP;
    Button btnTO;
    Button btnCE;
    Button btnPB;
    Button btnPE;
    Button btnAL;
    Button btnSE;
    Button btnPI;
    Button btnMA;
    Button btnAC;
    Button btnMT;
    Button btnMG;
    Button btnES;
    Button btnRJ;
    Button btnSP;
    Button btnMS;
    Button btnPR;
    Button btnSC;
    Button btnRS;
    Button btnBA;
    Button btnGO;
    Button btnDF;
    SwitchCompat swtOn;
    WebView wvMapa;

    private String menu;
    private TextView txtMenu;
    private Data dataApp;
    private ArrayList<FFR_Estado> ffrs;
    private ImageButton imgFFRBrasil;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        txtMenu = findViewById(R.id.txtMenu);
        swtOn = findViewById(R.id.swtON);
        imgFFRBrasil = findViewById(R.id.imageBrasilFFR);
        wvMapa = findViewById(R.id.wv);
        dataApp = new Data();
        listaAno = new ArrayList<String>();
        this.spn_lista_Ano = (Spinner) findViewById(R.id.spnAno);
        listaAno.add("2018");
        listaAno.add("2017");
        listaAno.add("2016");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<String>(this,R.layout.layoutspinner,listaAno);


        spn_lista_Ano.setAdapter(dataAdapter);
        spn_lista_Ano.setSelection(0);
        dataApp.setNo(spn_lista_Ano.getItemAtPosition(spn_lista_Ano.getSelectedItemPosition()).toString());
        spn_lista_Ano.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dataApp.setNo(parent.getItemAtPosition(position).toString());
                if(swtOn.isChecked()){
                    swtOn.setChecked(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listaMes = new ArrayList<String>();
        this.spn_lista_Mes = (Spinner) findViewById(R.id.spnMes);
        listaMes.add("January");
        listaMes.add("February");
        listaMes.add("March");
        listaMes.add("April");
        listaMes.add("May");
        listaMes.add("June");
        listaMes.add("July");
        listaMes.add("August");
        listaMes.add("September");
        listaMes.add("October");
        listaMes.add("November");
        listaMes.add("December");

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, R.layout.layoutspinner,listaMes);

        spn_lista_Mes.setAdapter(dataAdapter2);
        spn_lista_Mes.setSelection(0);
        dataApp.setMes(spn_lista_Mes.getItemAtPosition(spn_lista_Mes.getSelectedItemPosition()).toString());
        spn_lista_Mes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dataApp.setMes(parent.getItemAtPosition(position).toString());
                if(swtOn.isChecked()){
                    swtOn.setChecked(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listaDia = new ArrayList<String>();
        this.spnDia = (Spinner) findViewById(R.id.spnDia);
        listaDia.add("1");
        listaDia.add("2");
        listaDia.add("3");
        listaDia.add("4");
        listaDia.add("5");
        listaDia.add("6");
        listaDia.add("7");
        listaDia.add("8");
        listaDia.add("9");
        listaDia.add("10");
        listaDia.add("11");
        listaDia.add("12");
        listaDia.add("13");
        listaDia.add("14");
        listaDia.add("15");
        listaDia.add("16");
        listaDia.add("17");
        listaDia.add("18");
        listaDia.add("19");
        listaDia.add("20");
        listaDia.add("21");
        listaDia.add("22");
        listaDia.add("23");
        listaDia.add("24");
        listaDia.add("25");
        listaDia.add("26");
        listaDia.add("27");
        listaDia.add("28");
        listaDia.add("29");
        listaDia.add("30");
        listaDia.add("31");
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, R.layout.layoutspinner,listaDia);

        spnDia.setAdapter(dataAdapter3);
        spnDia.setSelection(0);
        dataApp.setDia(spnDia.getItemAtPosition(spnDia.getSelectedItemPosition()).toString());
        spnDia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dataApp.setDia(parent.getItemAtPosition(position).toString());
                if(swtOn.isChecked()){
                    swtOn.setChecked(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        swtOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    coresEstados();
                }
            }
        });

        wvMapa.getSettings().setJavaScriptEnabled(true);
        wvMapa.getSettings().setUseWideViewPort(true);
        wvMapa.getSettings().setLoadWithOverviewMode(true);
        wvMapa.addJavascriptInterface(this,"Android");
        wvMapa.loadUrl("file:///android_asset/mapaBrasilSVG.html");
    }

    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    public void coresEstados(){
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        long milliseconds = 100;
        vibrator.vibrate(milliseconds);


        final ArrayList<String> estados = new ArrayList<String>();
        estados.add("acre");
        estados.add("alagoas");
        estados.add("amapa");
        estados.add("amazonas");
        estados.add("bahia");
        estados.add("ceara");
        estados.add("distritofederal");
        estados.add("espiritosanto");
        estados.add("goias");
        estados.add("maranhao");
        estados.add("matogrosso");
        estados.add("matogrossodosul");
        estados.add("minasgerais");
        estados.add("para");
        estados.add("paraiba");
        estados.add("parana");
        estados.add("pernambuco");
        estados.add("piaui");
        estados.add("riodejaneiro");
        estados.add("riograndedonorte");
        estados.add("riograndedosul");
        estados.add("rondonia");
        estados.add("roraima");
        estados.add("santacatarina");
        estados.add("saopaulo");
        estados.add("sergipe");
        estados.add("tocantins");


        dataApp.setNo(spn_lista_Ano.getItemAtPosition(spn_lista_Ano.getSelectedItemPosition()).toString());
        dataApp.setMes(Integer.toString(spn_lista_Mes.getSelectedItemPosition() + 1));
        dataApp.setDia(spnDia.getItemAtPosition(spnDia.getSelectedItemPosition()).toString());

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://" + getResources().getString(R.string.ip) + ":85/api2/estado/getFFREstados.php?dia=" + dataApp.getDia()+ "&mes=" + dataApp.getMes()+ "&ano="+ dataApp.getNo()
                + "&produto="+ menu,null,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                ArrayList<FFR> lists = new ArrayList<FFR>();
                try {
                    JSONArray ffrs = response.getJSONArray("records");
                    for (int i = 0; i < ffrs.length(); i++) {
                        JSONObject object = ffrs.getJSONObject(i);
                        Gson gson = new Gson();
                        FFR ffr = gson.fromJson(object.toString(), FFR.class);

                        float aux = Float.parseFloat(ffr.getImproved());
                        if(aux < 0){
                            definirCor(estados.get(i),2);
                        }
                        if(aux > 0 && aux < 10){
                            definirCor(estados.get(i),3);
                        }
                        if(aux == 0 || aux >= 10){
                            definirCor(estados.get(i),1);
                        }
                    }

                }  catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        });


        AsyncHttpClient client2 = new AsyncHttpClient();
        client.get("http://" + getResources().getString(R.string.ip) + ":85/api2/estado/getFFRBrasil.php?dia=" + dataApp.getDia()+ "&mes=" + dataApp.getMes()+ "&ano="+ dataApp.getNo()
                + "&produto="+ menu,null,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                ArrayList<FFR> lists = new ArrayList<FFR>();
                try {
                    JSONArray ffrs = response.getJSONArray("records");
                    for (int i = 0; i < ffrs.length(); i++) {
                        JSONObject object = ffrs.getJSONObject(i);
                        Gson gson = new Gson();
                        FFR ffr = gson.fromJson(object.toString(), FFR.class);

                        float aux = Float.parseFloat(ffr.getImproved());
                        if(aux < 0){
                            imgFFRBrasil.setBackgroundResource(R.drawable.ic_imagebrasilvermelho);
                        }
                        if(aux > 0 && aux < 10){
                            imgFFRBrasil.setBackgroundResource(R.drawable.ic_imagebrasilamarelo);
                        }
                        if(aux == 0 || aux >= 10){
                            imgFFRBrasil.setBackgroundResource(R.drawable.ic_imagebrasilverde);
                        }
                    }

                }  catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        });
    }


    //cor: 1-verde, 2- vermelho, 3- amarelo;
    private void definirCor(final String estado, final int cor){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                wvMapa.loadUrl("javascript:metodoWebView('"+ estado + "'," + Integer.toString(cor) + ");");
            }
        });
    }

    public void ffrBrasil(View view){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        FireMissilesDialogFragment dialog = new FireMissilesDialogFragment();
        dialog.anoAnterior = Integer.toString(Integer.parseInt(spn_lista_Ano.getSelectedItem().toString()) - 1);
        dialog.anoAtual = spn_lista_Ano.getSelectedItem().toString();
        dialog.estado = "Brasil";
        Intent intent = new Intent(MainActivity.this, TesteListaActivity.class);
        dataApp.setNo(spn_lista_Ano.getItemAtPosition(spn_lista_Ano.getSelectedItemPosition()).toString());
        dataApp.setMes(Integer.toString(spn_lista_Mes.getSelectedItemPosition() + 1));
        dataApp.setDia(spnDia.getItemAtPosition(spnDia.getSelectedItemPosition()).toString());
        dialog.dataApp = dataApp;
        dialog.produto = menu;
        intent.putExtra("dataApp",dataApp);
        intent.putExtra("produto",menu);
        intent.putExtra("estado","Brasil");


        dialog.intent = intent;
        dialog.show(ft,"Dialog");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        this.menu = "HEA";
        //this.coresEstados();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.isChecked())
            item.setChecked(false);
        else
            item.setChecked(true);

        switch (item.getItemId()){
            case R.id.rac:
                this.menu = "SRAC";
                this.txtMenu.setText("RAC");
                break;
            case R.id.onoffItem:
                this.menu = "ONOFF";
                this.txtMenu.setText("RAC | ON/OFF");
                break;
            case R.id.hEaItem:
                this.menu = "HEA";
                this.txtMenu.setText(getResources().getString(R.string.h_a));
                break;
            case R.id.inverterItem:
                this.menu = "INVERTER";
                this.txtMenu.setText("RAC | INVERTER");
                break;
            case R.id.MWO:
                this.menu = "MWO";
                this.txtMenu.setText("MWO");
                break;
            case R.id.solidItem:
                this.menu = "SOLO";
                this.txtMenu.setText("MWO | SOLO");
                break;

            case R.id.grillItem:
                this.menu = "GRILL";
                this.txtMenu.setText("MWO | GRILL");
                break;
            case R.id.scitem:
                this.menu = "SC";
                this.txtMenu.setText("SC");
                break;
            case R.id.shitem:
                this.menu = "SH";
                this.txtMenu.setText("SH");
                break;
            case R.id.switem:
                this.menu = "SW";
                this.txtMenu.setText("SW");
                break;
            case R.id.l23item:
                this.menu = "23";
                this.txtMenu.setText("23L");
                break;
            case R.id.l30item:
                this.menu = "30";
                this.txtMenu.setText("30L");
                break;
        }
        if(swtOn.isChecked()){
            swtOn.setChecked(false);
        }
        return true;
    }

    @JavascriptInterface
    public void metodoPonte(String estado){
        final String vari = "1";
        final String teste = "amazonas";
        /*runOnUiThread(new Runnable() {
            @Override
            public void run() {
                wvMapa.loadUrl("javascript:metodoWebView('"+ teste + "'," + vari + ");");
            }
        });*/

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        FireMissilesDialogFragment dialog = new FireMissilesDialogFragment();
        dialog.anoAnterior = Integer.toString(Integer.parseInt(spn_lista_Ano.getSelectedItem().toString()) - 1);
        dialog.anoAtual = spn_lista_Ano.getSelectedItem().toString();
        dialog.estado = estado;
        Intent intent = new Intent(MainActivity.this, TesteListaActivity.class);
        dataApp.setNo(spn_lista_Ano.getItemAtPosition(spn_lista_Ano.getSelectedItemPosition()).toString());
        dataApp.setMes(Integer.toString(spn_lista_Mes.getSelectedItemPosition() + 1));
        dataApp.setDia(spnDia.getItemAtPosition(spnDia.getSelectedItemPosition()).toString());
        dialog.dataApp = dataApp;
        dialog.produto = menu;
        intent.putExtra("dataApp",dataApp);
        intent.putExtra("produto",menu);
        intent.putExtra("estado",estado);


        dialog.intent = intent;
        dialog.show(ft,"Dialog");


    }
}
