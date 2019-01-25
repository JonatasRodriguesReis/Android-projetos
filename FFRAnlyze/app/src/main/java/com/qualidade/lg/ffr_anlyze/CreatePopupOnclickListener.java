package com.qualidade.lg.ffr_anlyze;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by francisco.pereira on 24/01/2018.
 */

public class CreatePopupOnclickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        Context context = v.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View formElementsView = inflater.inflate(R.layout.popup_estados,null,false);
        TextView ano_Anteriorpop = (TextView) formElementsView.findViewById(R.id.txtAnoAnteriorpop);
        TextView ano_Atualpop = (TextView) formElementsView.findViewById(R.id.txtAnoAtualpop);
        TextView improved = (TextView) formElementsView.findViewById(R.id.txtImproved);

        new AlertDialog.Builder(context).setView(formElementsView).setTitle("FFR" + "Rio Grande do Norte").setPositiveButton("To View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }
}
