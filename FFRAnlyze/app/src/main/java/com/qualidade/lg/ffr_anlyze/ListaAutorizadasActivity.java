package com.qualidade.lg.ffr_anlyze;

import android.app.ActionBar;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import model.Autorizada;
import model.Cidade;
import model.Data;
import model.Estado;
import model.Item;
import model.LinhaProduto;
import model.NomeProduto;

public class ListaAutorizadasActivity extends AppCompatActivity {
    private ListView listview;
    private ArrayAdapter listAdapter;
    private Data dataApp;
    private String produto;
    private String estado;
    private String nomeItem;
    private String cidadeNome;
    private ArrayList<Autorizada> autorizadas;
    private String ip_server = "";

    IPHelper ipHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_autorizadas);
        ipHelper = new IPHelper(getBaseContext());

        Cursor cursor = ipHelper.getWritableDatabase().rawQuery("select valor from ips where id = 0",null);
        if(cursor.moveToNext()){
            this.ip_server = cursor.getString(0);
        }
        nomeItem = getIntent().getStringExtra("nome");
        estado = getIntent().getStringExtra("estado");
        produto = getIntent().getStringExtra("produto");
        dataApp = (Data) getIntent().getSerializableExtra("dataApp");
        cidadeNome = getIntent().getStringExtra("cidade");
        Log.d("Teste2",cidadeNome);
        final ArrayList<String> arrayAutorizadas = new ArrayList<String>();

        this.listview = (ListView) findViewById(R.id.lsvAutorizadas);

        listAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayAutorizadas);
        listview.setAdapter(listAdapter);

        if(estado.equals("Brasil")){
            AsyncHttpClient client = new AsyncHttpClient();
            client.get("http://" + this.ip_server + ":85/api2/autorizada/getAutorizadasBrasil.php?dia=" + dataApp.getDia()+ "&mes=" + dataApp.getMes()+ "&ano="+ dataApp.getNo()
                    + "&produto="+ produto + "&item=" + nomeItem + "&cidade=" + cidadeNome,null,new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                    ArrayList<Autorizada> lists = new ArrayList<Autorizada>();
                    try {
                        JSONArray autorizadas = response.getJSONArray("records");
                        for (int i = 0; i < autorizadas.length(); i++) {
                            JSONObject object = autorizadas.getJSONObject(i);
                            Gson gson = new Gson();
                            Autorizada autorizada = gson.fromJson(object.toString(), Autorizada.class);

                            lists.add(autorizada);
                        }
                        for (Autorizada i: lists) {
                            arrayAutorizadas.add(i.getNome());
                        }


                        listview.setAdapter(listAdapter);


                    }  catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }else{
            AsyncHttpClient client = new AsyncHttpClient();
            client.get("http://" + this.ip_server + ":85/api2/autorizada/getAutorizadas.php?dia=" + dataApp.getDia()+ "&mes=" + dataApp.getMes()+ "&ano="+ dataApp.getNo()
                    + "&produto="+ produto + "&estado=" + estado + "&item=" + nomeItem + "&cidade=" + cidadeNome,null,new JsonHttpResponseHandler(){
                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                    ArrayList<Autorizada> lists = new ArrayList<Autorizada>();
                    try {
                        JSONArray autorizadas = response.getJSONArray("records");
                        for (int i = 0; i < autorizadas.length(); i++) {
                            JSONObject object = autorizadas.getJSONObject(i);
                            Gson gson = new Gson();
                            Autorizada autorizada = gson.fromJson(object.toString(), Autorizada.class);

                            lists.add(autorizada);
                        }
                        for (Autorizada i: lists) {
                            arrayAutorizadas.add(i.getNome());
                        }


                        listview.setAdapter(listAdapter);


                    }  catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }
        });
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
}
