package com.qualidade.lg.ffr_anlyze;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import model.Data;
import model.FFR;
import model.Item;
import model.LinhaProduto;
import model.NomeProduto;

public class ItensActivity extends AppCompatActivity {
    private TextView ano_Anterior;
    private TextView ano_Atual;
    private TextView ano_AnteriorFRR;
    private TextView ano_AtualFRR;
    private TextView improvedFFR;
    private TextView item;

    private Data dataApp;
    private String produto;
    private String estado;
    private String nome;
    private String ip_server = "";

    IPHelper ipHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ipHelper = new IPHelper(getBaseContext());

        Cursor cursor = ipHelper.getWritableDatabase().rawQuery("select valor from ips where id = 0",null);
        if(cursor.moveToNext()){
            this.ip_server = cursor.getString(0);
        }
        setContentView(R.layout.activity_itens);
        ano_Anterior = findViewById(R.id.txtAnoAnterior);
        ano_Atual = findViewById(R.id.txtAnoAtual);
        item = findViewById(R.id.txtItem);
        ano_AnteriorFRR = findViewById(R.id.txtFFR_Anterior);
        ano_AtualFRR = findViewById(R.id.txtFFRAtual);
        improvedFFR = findViewById(R.id.txtFFR_Improved);

        produto = getIntent().getStringExtra("produto");
        estado = getIntent().getStringExtra("estado");
        nome = getIntent().getStringExtra("nome");
        dataApp = (Data) getIntent().getSerializableExtra("dataApp");
        item.setText(nome);
        ano_Atual.setText(dataApp.getNo());
        ano_Anterior.setText(Integer.toString(Integer.parseInt(dataApp.getNo()) - 1));

        AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://" + this.ip_server + ":85/api/item/getFFRItem.php?dia=" + dataApp.getDia()+ "&mes=" + dataApp.getMes()+ "&ano="+ dataApp.getNo()
                + "&produto="+ produto + "&estado=" + estado + "&item=" + nome,null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                ArrayList<FFR> lists = new ArrayList<FFR>();
                try {
                    JSONArray ffrs = response.getJSONArray("records");
                    Log.d("Tesste",Integer.toString(ffrs.length()));
                    for (int i = 0; i < ffrs.length(); i++) {
                        JSONObject object = ffrs.getJSONObject(i);
                        Gson gson = new Gson();
                        FFR ffr = gson.fromJson(object.toString(), FFR.class);

                        lists.add(ffr);
                    }
                    DecimalFormat df = new DecimalFormat("0.0000");
                    DecimalFormat df2 = new DecimalFormat("0.00");
                    ano_AtualFRR.setText(df.format(Float.parseFloat(lists.get(0).getAtual())));
                    ano_AnteriorFRR.setText(df.format(Float.parseFloat(lists.get(0).getAnterior())));
                    float aux = Float.parseFloat(lists.get(0).getImproved());
                    if(aux < 0){
                        improvedFFR.setTextColor(getResources().getColor(R.color.red));
                        aux = aux * -1;
                    }else
                        if(aux > 0 && aux < 10){
                            improvedFFR.setTextColor(getResources().getColor(R.color.amarelo));
                        }else
                            if(aux == 0 || aux >= 10){
                              improvedFFR.setTextColor(getResources().getColor(R.color.green));
                            }
                    improvedFFR.setText(df2.format(aux) + "%");

                }  catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void listarCidades(View view){
        Intent  intent = new Intent(this,ListaCidadesActivity.class);
        intent.putExtra("dataApp", dataApp);
        intent.putExtra("produto", produto);
        intent.putExtra("estado", estado);
        intent.putExtra("nome", nome);
        startActivity(intent);
    }
}
