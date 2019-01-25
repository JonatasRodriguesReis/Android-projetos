package com.qualidade.lg.ffr_anlyze;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

/**
 * Created by francisco.pereira on 24/01/2018.
 */

public class FireMissilesDialogFragment extends DialogFragment {
    public String anoAtual = "";
    public String anoAnterior = "";
    public String anoAtualpop = "";
    public String anoAnteriorpop = "";
    public String estado = "";
    public Data dataApp;
    public String produto;
    public TextView txt1;
    public TextView txt2;
    public TextView txtAnterior;
    public TextView txtAtual;
    public TextView txtImproved;
    public Intent intent = null;
    private Button btn = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_estados, container, false);
        txt1 = view.findViewById(R.id.txt1pop);
        txt1.setText(anoAnterior);
        txt2 = view.findViewById(R.id.txt2pop);
        txt2.setText(anoAtual);
        txtAnterior = view.findViewById(R.id.txtAnoAnteriorpop);
        txtAtual = view.findViewById(R.id.txtAnoAtualpop);
        txtImproved = view.findViewById(R.id.txtImprovedpop);
        btn = (Button) view.findViewById(R.id.btnToView);
        btn.setOnClickListener(new ToViewClickListener());
        getDialog().setTitle(this.estado);

        if(estado.equals("Brasil")) {

            AsyncHttpClient client = new AsyncHttpClient();
            client.get("http://" + getResources().getString(R.string.ip) + ":85/api2/estado/getFFRBrasil.php?dia=" + dataApp.getDia() + "&mes=" + dataApp.getMes() + "&ano=" + dataApp.getNo()
                    + "&produto=" + produto, null, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                    ArrayList<FFR> lists = new ArrayList<FFR>();
                    try {
                        JSONArray ffrs = response.getJSONArray("records");
                        for (int i = 0; i < ffrs.length(); i++) {
                            JSONObject object = ffrs.getJSONObject(i);
                            Gson gson = new Gson();
                            FFR ffr = gson.fromJson(object.toString(), FFR.class);

                            lists.add(ffr);
                        }
                        DecimalFormat df = new DecimalFormat("0.00");
                        DecimalFormat df2 = new DecimalFormat("0.0");
                        txtAtual.setText(df.format(Float.parseFloat(lists.get(0).getAtual())));
                        txtAnterior.setText(df.format(Float.parseFloat(lists.get(0).getAnterior())));
                        float aux = Float.parseFloat(lists.get(0).getImproved());
                        if (aux < 0) {
                            txtImproved.setTextColor(getResources().getColor(R.color.red));
                            aux = aux * -1;
                        } else if (aux >= 0 && aux < 10) {
                            txtImproved.setTextColor(getResources().getColor(R.color.amarelo));
                        } else if (aux == 0 || aux >= 10) {
                            txtImproved.setTextColor(getResources().getColor(R.color.green));
                        }
                        txtImproved.setText(df2.format(aux) + "%");

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                }
            });
        }
        else{
            AsyncHttpClient client = new AsyncHttpClient();
            client.get("http://" + getResources().getString(R.string.ip) + ":85/api2/estado/getFFREstado.php?dia=" + dataApp.getDia() + "&mes=" + dataApp.getMes() + "&ano=" + dataApp.getNo()
                    + "&produto=" + produto + "&estado=" + estado, null, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                    ArrayList<FFR> lists = new ArrayList<FFR>();
                    try {
                        JSONArray ffrs = response.getJSONArray("records");
                        for (int i = 0; i < ffrs.length(); i++) {
                            JSONObject object = ffrs.getJSONObject(i);
                            Gson gson = new Gson();
                            FFR ffr = gson.fromJson(object.toString(), FFR.class);

                            lists.add(ffr);
                        }
                        DecimalFormat df = new DecimalFormat("0.00");
                        DecimalFormat df2 = new DecimalFormat("0.0");
                        txtAtual.setText(df.format(Float.parseFloat(lists.get(0).getAtual())));
                        txtAnterior.setText(df.format(Float.parseFloat(lists.get(0).getAnterior())));
                        float aux = Float.parseFloat(lists.get(0).getImproved());
                        if (aux < 0) {
                            txtImproved.setTextColor(getResources().getColor(R.color.red));
                            aux = aux * -1;
                        } else if (aux >= 0 && aux < 10) {
                            txtImproved.setTextColor(getResources().getColor(R.color.amarelo));
                        } else if (aux == 0 || aux >= 10) {
                            txtImproved.setTextColor(getResources().getColor(R.color.green));
                        }
                        txtImproved.setText(df2.format(aux) + "%");

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }

                }
            });
        }

        return view;
    }


    private class ToViewClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            startActivity(intent);
        }
    }
}
