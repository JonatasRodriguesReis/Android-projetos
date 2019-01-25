package com.qualidade.lg.ffr_anlyze;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

import model.Data;
import model.Estado;
import model.Item;
import model.LinhaProduto;
import model.NomeProduto;

public class ListaItemsActivity extends AppCompatActivity {
    private ListView listview;
    private ArrayAdapter listAdapter;
    Data dataApp;
    String produto = "";
    String estado = "";
    private ArrayList<Item> itemsLista;
    private ArrayList<String> arrayItems;
    private String ip_server = "";

    IPHelper ipHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_items);
        ipHelper = new IPHelper(getBaseContext());

        Cursor cursor = ipHelper.getWritableDatabase().rawQuery("select valor from ips where id = 0",null);
        if(cursor.moveToNext()){
            this.ip_server = cursor.getString(0);
        }

        dataApp = (Data) getIntent().getSerializableExtra("dataApp");
        produto = getIntent().getStringExtra("produto");
        estado = getIntent().getStringExtra("estado");
        arrayItems = new ArrayList<String>();

        this.listview = (ListView) findViewById(R.id.lsvItems);

        listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayItems);
        listview.setAdapter(listAdapter);

        AsyncHttpClient client = new AsyncHttpClient();
        client.setResponseTimeout(100000000);
        client.setTimeout(1000000000);
        client.get("http://" + this.ip_server + ":85/api/item/getItems.php?dia=" + dataApp.getDia()+ "&mes=" + dataApp.getMes()+ "&ano="+ dataApp.getNo()
                + "&produto="+ produto + "&estado=" + estado ,null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                ArrayList<Item> lists = new ArrayList<Item>();
                try {
                    JSONArray items = response.getJSONArray("records");
                    Log.d("Tesste",Integer.toString(items.length()));
                    for (int i = 0; i < items.length(); i++) {
                        JSONObject object = items.getJSONObject(i);
                        Gson gson = new Gson();
                        Item item = gson.fromJson(object.toString(), Item.class);

                        lists.add(item);
                        Log.d("Teste",item.getNome());
                    }
                    Log.d("Teste",Integer.toString(lists.size()));
                    for (Item i: lists) {
                        arrayItems.add(i.getNome());
                    }


                    listview.setAdapter(listAdapter);


                }  catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ItensActivity.class);
                intent.putExtra("dataApp", dataApp);
                intent.putExtra("produto", produto);
                intent.putExtra("estado", estado);
                intent.putExtra("nome", arrayItems.get(position));
                startActivity(intent);
            }
        });

    }
}
