package model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qualidade.lg.ffr_anlyze.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manoel on 17/02/2018.
 */

public class ItemAdapter extends RecyclerView.Adapter {
    public ArrayList<ItemTeste> lista;
    Context context;
    Data dataApp;
    String nomeProduto;
    String nomeEstado;
    public ItemAdapter(ArrayList<ItemTeste> lista,Context context,Data data,String produto, String estado){
        this.lista = lista;
        this.context = context;
        dataApp =  data;
        nomeProduto = produto;
        nomeEstado = estado;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_linha, parent, false);
        ItemViewHolder holder = new ItemViewHolder(context,view, (ArrayList<ItemTeste>) lista,dataApp,nomeProduto,nomeEstado);

        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ItemViewHolder holder = (ItemViewHolder) viewHolder;
        DecimalFormat df = new DecimalFormat("0.000");
        DecimalFormat df2 = new DecimalFormat("0.0");
        ItemTeste item  = lista.get(position) ;
        holder.itemnome.setText(item.getNome());
        holder.anoAnterior.setText("'" + item.getAnoAnterior().substring(2,4));
        holder.anoAtual.setText("'" + item.getAnoAtual().substring(2,4));
        holder.valorAnterior.setText("  " + df.format(Float.parseFloat(item.getValorAnterior())));
        holder.valorAtual.setText("  " + df.format(Float.parseFloat(item.getValorAtual())));
        holder.valorAnteriorS.setText("  " + item.getValorAnteriorS());
        holder.valorAtualS.setText("  " + item.getValorAtualS());
        holder.posicaoRanking.setText(Integer.toString(position + 1));
        float aux = Float.parseFloat(item.getImproved());
        if(aux < 0){
            holder.valorImproved.setText(df2.format(Float.parseFloat(item.getImproved()) * -1) + "%");
            holder.valorImproved.setTextColor(context.getResources().getColor(R.color.red));
            holder.imageIcon.setImageResource(R.drawable.upward);
            holder.iconeCor.setBackground(context.getResources().getDrawable(R.drawable.botaoestadosred));
        }else {
            if (aux > 0 && aux < 10) {
                holder.imageIcon.setImageResource(R.drawable.downward2);
                holder.valorImproved.setTextColor(context.getResources().getColor(R.color.amarelo));
                holder.iconeCor.setBackground(context.getResources().getDrawable(R.drawable.botaoestadoamarelo));
            } else {
                holder.imageIcon.setImageResource(R.drawable.downward);
                holder.valorImproved.setTextColor(context.getResources().getColor(R.color.green));
                holder.iconeCor.setBackground(context.getResources().getDrawable(R.drawable.botaoestadosgreen));
            }
            holder.valorImproved.setText( df2.format(Float.parseFloat(item.getImproved())) + "%");
        }

        float aux2 = Float.parseFloat(item.getImprovedS());
        if(aux2 < 0){
            holder.valorImprovedS.setText(df2.format(Float.parseFloat(item.getImprovedS()) * -1) + "%");
            holder.valorImprovedS.setTextColor(context.getResources().getColor(R.color.red));
            holder.imageIconS.setImageResource(R.drawable.upward);
            holder.iconeCorS.setBackground(context.getResources().getDrawable(R.drawable.botaoestadosred));
        }else {
            if (aux > 0 && aux < 10) {
                holder.imageIconS.setImageResource(R.drawable.downward2);
                holder.valorImprovedS.setTextColor(context.getResources().getColor(R.color.amarelo));
                holder.iconeCorS.setBackground(context.getResources().getDrawable(R.drawable.botaoestadoamarelo));
            } else {
                holder.imageIconS.setImageResource(R.drawable.downward);
                holder.valorImprovedS.setTextColor(context.getResources().getColor(R.color.green));
                holder.iconeCorS.setBackground(context.getResources().getDrawable(R.drawable.botaoestadosgreen));
            }
            holder.valorImprovedS.setText( df2.format(Float.parseFloat(item.getImprovedS())) + "%");
        }
    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public void updateList(int size){
        atualizarAdapter(size);
    }

    private void atualizarAdapter(int size){
        notifyItemRangeInserted(getItemCount()-1,size-1);
    }
}
